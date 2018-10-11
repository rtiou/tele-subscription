/**
 * 
 */
package com.ronaldotiou.helper;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.ronaldotiou.domain.Subscription;

/**
 * @author Ronaldo Lien Tiou
 *
 */
public class SubscriptionHelperTest {
	public static List<Subscription> getSubscriptionList() {
		return Arrays.asList(createSubscription(123), createSubscription(456));
	}
	
	public static Subscription getSubscription() {
		return createSubscription(123);
	}
	
	private static Subscription createSubscription(int subscriptionId) {
		Subscription subscription = new Subscription();
		subscription.setId(subscriptionId);
		subscription.setName("name " + subscriptionId );
		subscription.setMonthlyPrice(new BigDecimal("22.55"));
		
		return subscription;
	}
}
