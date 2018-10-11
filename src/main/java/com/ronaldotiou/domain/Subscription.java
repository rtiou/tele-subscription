/**
 * 
 */
package com.ronaldotiou.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Ronaldo Lien Tiou
 *
 */
@Getter
@Setter
@Entity
public class Subscription {
	@Id
	private int id;
	private String name;
	private BigDecimal monthlyPrice;
	private Timestamp lastUpdate;
}
