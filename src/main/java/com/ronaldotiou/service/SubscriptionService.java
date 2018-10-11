/**
 * 
 */
package com.ronaldotiou.service;

import java.util.List;

import com.ronaldotiou.domain.Subscription;

/**
 * @author Ronaldo Lien Tiou
 *
 */
public interface SubscriptionService {
	List<Subscription> getSubscriptions();

	Subscription getSubscription(int subscriptionId);

	Subscription updateSubscription(int subscriptionId, Subscription subscription);

	Subscription createSubscription(Subscription subscription);
}
