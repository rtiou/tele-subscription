/**
 * 
 */
package com.ronaldotiou.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ronaldotiou.domain.Subscription;
import com.ronaldotiou.exception.ResourceNotFoundException;
import com.ronaldotiou.jpa.repository.SubscriptionRepository;
import com.ronaldotiou.service.SubscriptionService;

import lombok.AllArgsConstructor;

/**
 * @author Ronaldo Lien Tiou
 *
 */
@Service
@AllArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

	private final SubscriptionRepository subscriptionRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ronaldotiou.service.SubscriptionService#getSubscriptions()
	 */
	@Override
	@HystrixCommand(fallbackMethod = "fallbackForGetSubscriptions", ignoreExceptions=ResourceNotFoundException.class)
	public List<Subscription> getSubscriptions() {
		return subscriptionRepository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ronaldotiou.service.SubscriptionService#getSubscription(int)
	 */
	@Override
	@HystrixCommand(fallbackMethod = "fallbackForGetSubscription", ignoreExceptions=ResourceNotFoundException.class)
	public Subscription getSubscription(int subscriptionId) {
		if (subscriptionRepository.existsById(subscriptionId)) {
			return subscriptionRepository.findById(subscriptionId).get();
		}

		throw new ResourceNotFoundException("Subscription with ID: " + subscriptionId + " does not exist.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ronaldotiou.service.SubscriptionService#updateSubscription(int,
	 * com.ronaldotiou.domain.Subscription)
	 */
	@Override
	@HystrixCommand(fallbackMethod = "fallbackForUpdateSubscription", ignoreExceptions=ResourceNotFoundException.class)
	public Subscription updateSubscription(int subscriptionId, Subscription subscription) {
		if (subscriptionRepository.existsById(subscriptionId)) {
			Subscription origin = subscriptionRepository.getOne(subscriptionId);
			origin.setMonthlyPrice(subscription.getMonthlyPrice());
			return subscriptionRepository.save(origin);
		}
		throw new ResourceNotFoundException("Subscription with ID: " + subscriptionId + " does not exist.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ronaldotiou.service.SubscriptionService#createSubscription(com.
	 * ronaldotiou.domain.Subscription)
	 */
	@Override
	@HystrixCommand(fallbackMethod = "fallbackForCreateSubscription", ignoreExceptions=ResourceNotFoundException.class)
	public Subscription createSubscription(Subscription subscription) {
		if (!subscriptionRepository.existsById(subscription.getId())) {
			return subscriptionRepository.save(subscription);
		}

		throw new ResourceNotFoundException("Subscription with ID: " + subscription.getId() + " exist.");
	}

	public List<Subscription> fallbackForGetSubscriptions() {
		return null;
	}

	public Subscription fallbackForGetSubscription(int subscriptionId) {
		return null;
	}

	public Subscription fallbackForUpdateSubscription(int subscriptionId, Subscription subscription) {
		return null;
	}

	public Subscription fallbackForCreateSubscription(Subscription subscription) {
		return null;
	}

}
