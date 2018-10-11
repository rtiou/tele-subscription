/**
 * 
 */
package com.ronaldotiou.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ronaldotiou.domain.Subscription;
import com.ronaldotiou.exception.ResourceNotFoundException;
import com.ronaldotiou.helper.SubscriptionHelperTest;
import com.ronaldotiou.jpa.repository.SubscriptionRepository;

/**
 * @author Ronaldo Lien Tiou
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class SubscriptionServiceImplTest {

	@InjectMocks
	SubscriptionServiceImpl subscriptionServiceImpl;

	@Mock
	SubscriptionRepository repository;

	/**
	 * Test method for
	 * {@link com.ronaldotiou.service.impl.SubscriptionServiceImpl#getSubscriptions()}.
	 */
	@Test
	public void testGetSubscriptions() {
		when(subscriptionServiceImpl.getSubscriptions()).thenReturn(SubscriptionHelperTest.getSubscriptionList());

		List<Subscription> result = subscriptionServiceImpl.getSubscriptions();
		Subscription subscription = result.get(0);

		assertEquals(123, subscription.getId());
		assertEquals("name 123", subscription.getName());
		assertEquals(new BigDecimal("22.55"), subscription.getMonthlyPrice());
	}

	/**
	 * Test method for
	 * {@link com.ronaldotiou.service.impl.SubscriptionServiceImpl#getSubscription(int)}.
	 */
	@Test
	public void testGetSubscription() {
		Optional<Subscription> subscriptionOptional = Optional
				.of((Subscription) SubscriptionHelperTest.getSubscription());

		when(repository.existsById(123)).thenReturn(true);
		when(repository.findById(123)).thenReturn(subscriptionOptional);

		Subscription subscription = subscriptionServiceImpl.getSubscription(123);

		assertEquals(123, subscription.getId());
		assertEquals("name 123", subscription.getName());
		assertEquals(new BigDecimal("22.55"), subscription.getMonthlyPrice());
	}

	@Test(expected = ResourceNotFoundException.class)
	public void testGetSubscriptionNotFound() {
		when(repository.existsById(123)).thenReturn(false);
		subscriptionServiceImpl.getSubscription(123);
	}

	/**
	 * Test method for
	 * {@link com.ronaldotiou.service.impl.SubscriptionServiceImpl#updateSubscription(int, com.ronaldotiou.domain.Subscription)}.
	 */
	@Test
	public void testUpdateSubscription() {
		Subscription newSubscription = SubscriptionHelperTest.getSubscription();
		newSubscription.setMonthlyPrice(new BigDecimal("11.99"));
		Subscription subscription = SubscriptionHelperTest.getSubscription();

		when(repository.existsById(123)).thenReturn(true);
		when(repository.getOne(123)).thenReturn(subscription);
		when(repository.save(any(Subscription.class))).thenReturn(subscription);

		Subscription response = subscriptionServiceImpl.updateSubscription(123, newSubscription);

		assertEquals(newSubscription.getId(), response.getId());
		assertEquals(newSubscription.getLastUpdate(), response.getLastUpdate());
		assertEquals(newSubscription.getMonthlyPrice(), response.getMonthlyPrice());
		assertEquals(newSubscription.getName(), response.getName());
	}

	@Test(expected = ResourceNotFoundException.class)
	public void testUpdateNonExistSubscription() {
		Subscription newSubscription = SubscriptionHelperTest.getSubscription();
		newSubscription.setMonthlyPrice(new BigDecimal("11.99"));

		when(repository.existsById(123)).thenReturn(false);
		
		subscriptionServiceImpl.updateSubscription(123, newSubscription);
	}

	/**
	 * Test method for
	 * {@link com.ronaldotiou.service.impl.SubscriptionServiceImpl#createSubscription(com.ronaldotiou.domain.Subscription)}.
	 */
	@Test
	public void testCreateSubscription() {
		Subscription subscription = SubscriptionHelperTest.getSubscription();

		when(repository.existsById(123)).thenReturn(false);
		when(repository.save(any(Subscription.class))).thenReturn(subscription);

		Subscription response = subscriptionServiceImpl.createSubscription(subscription);

		assertEquals(subscription.getId(), response.getId());
		assertEquals(subscription.getLastUpdate(), response.getLastUpdate());
		assertEquals(subscription.getMonthlyPrice(), response.getMonthlyPrice());
		assertEquals(subscription.getName(), response.getName());

	}

	@Test(expected = ResourceNotFoundException.class)
	public void testCreateExistSubscription() {
		Subscription subscription = SubscriptionHelperTest.getSubscription();

		when(repository.existsById(123)).thenReturn(true);

		subscriptionServiceImpl.createSubscription(subscription);
	}

}
