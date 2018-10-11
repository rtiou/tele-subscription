/**
 * 
 */
package com.ronaldotiou.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ronaldotiou.domain.Subscription;
import com.ronaldotiou.service.SubscriptionService;

import lombok.AllArgsConstructor;

/**
 * @author Ronaldo Lien Tiou
 *
 */
@RestController
@RequestMapping("/api/subscriptions")
@AllArgsConstructor
public class SubscriptionController {
	
	private final SubscriptionService subscriptionService;
	
	/**
     * Endpoint to retrieve all subscriptions.
     *
     * @return return a list of subscriptions.
     */
    @GetMapping
    public List<Subscription> getSubscriptions() {
    	return subscriptionService.getSubscriptions();
    }
    
    /**
     * Endpoint to retrieve a single subscription using the subscriptionId.
     *
     * @param subscriptionId the subscription id.
     * @return subscription
     */
    @GetMapping("/{subscriptionId}")
    public Subscription getSubscription(@PathVariable int subscriptionId) {
    	return subscriptionService.getSubscription(subscriptionId);
    }
    
    /**
     * Endpoint to update an exist subscription.
     *
     * @param subscriptionId the subscription id used to find the subscription to be updated.
     * @param subscription the new subscription.
     * @return the new subscription.
     */
    @PutMapping("/{subscriptionId}")
    public Subscription updateSubscription(@PathVariable(value = "subscriptionId") int subscriptionId,
        @Valid @RequestBody Subscription subscription) {
    	return subscriptionService.updateSubscription(subscriptionId, subscription);
    }
    
    /**
     * Endpoint to create a new subscription.
     *
     * @param new Subscription.
     */
    @PostMapping
    public Subscription createSubscription(@RequestBody Subscription subscription) {
    	return subscriptionService.createSubscription(subscription);
    }
}
