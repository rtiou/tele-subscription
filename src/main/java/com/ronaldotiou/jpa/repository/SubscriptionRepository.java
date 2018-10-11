/**
 * 
 */
package com.ronaldotiou.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ronaldotiou.domain.Subscription;

/**
 * @author Ronaldo Lien Tiou
 *
 */

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

}
