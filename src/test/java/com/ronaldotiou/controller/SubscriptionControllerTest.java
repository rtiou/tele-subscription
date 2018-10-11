/**
 * 
 */
package com.ronaldotiou.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ronaldotiou.Application;
import com.ronaldotiou.domain.Subscription;
import com.ronaldotiou.helper.SubscriptionHelperTest;
import com.ronaldotiou.service.SubscriptionService;

/**
 * @author Ronaldo Lien Tiou
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class SubscriptionControllerTest {

	@Mock
	SubscriptionService subscriptionService;

	@InjectMocks
	SubscriptionController subscriptionController;

	/**
	 * Test method for
	 * {@link com.ronaldotiou.controller.SubscriptionController#getSubscriptions()}.
	 */
	@Test
	public void testGetSubscriptions() {
		when(subscriptionService.getSubscriptions()).thenReturn(SubscriptionHelperTest.getSubscriptionList());

		List<Subscription> response = subscriptionController.getSubscriptions();

		assertEquals(2, response.size());

	}

	/**
	 * Test method for
	 * {@link com.ronaldotiou.controller.SubscriptionController#getSubscription(int)}.
	 */
	@Test
	public void testGetSubscription() {
		when(subscriptionService.getSubscription(123)).thenReturn(SubscriptionHelperTest.getSubscription());

		Subscription response = subscriptionController.getSubscription(123);

		assertEquals(123, response.getId());
	}

	/**
	 * Test method for
	 * {@link com.ronaldotiou.controller.SubscriptionController#updateSubscription(int, com.ronaldotiou.domain.Subscription)}.
	 */
	@Test
	public void testUpdateSubscription() {
		Subscription subscription = SubscriptionHelperTest.getSubscription();
		subscription.setMonthlyPrice(new BigDecimal("22.99"));
		
		when(subscriptionService.updateSubscription(123, subscription)).thenReturn(subscription);
		
		Subscription response = subscriptionController.updateSubscription(123, subscription);
		
        assertEquals(subscription.getMonthlyPrice(), response.getMonthlyPrice());
	}

	/**
	 * Test method for
	 * {@link com.ronaldotiou.controller.SubscriptionController#createSubscription(com.ronaldotiou.domain.Subscription)}.
	 */
	@Test
	public void testCreateSubscription() {
		Subscription subscription = SubscriptionHelperTest.getSubscription();
		subscription.setMonthlyPrice(new BigDecimal("22.99"));
		
		when(subscriptionService.createSubscription(subscription)).thenReturn(subscription);
		
		Subscription response = subscriptionController.createSubscription(subscription);
		
        assertEquals(subscription.getId(), response.getId());
        assertEquals(subscription.getName(), response.getName());
        assertEquals(subscription.getMonthlyPrice(), response.getMonthlyPrice());	
	}

}
