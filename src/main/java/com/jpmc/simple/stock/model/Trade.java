package com.jpmc.simple.stock.model;

import com.jpmc.simple.stock.constant.TradeIndicator;

/**
 * Trade Bean
 * 
 * @author Suresh
 */

public class Trade {
	
	private TradeIndicator type;
	private Integer quantity;
	private Double price;

	/**
	 * @return
	 */
	public TradeIndicator getType() {
		return type;
	}

	/**
	 * @param type
	 */
	public void setType(TradeIndicator type) {
		this.type = type;
	}

	/**
	 * @return
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @param type
	 * @param quantity
	 * @param price
	 */
	public Trade(TradeIndicator type, Integer quantity, Double price) {
		this.setType(type);
		this.setQuantity(quantity);
		this.setPrice(price);
	}

	@Override
	public String toString() {
		String pattern = "Trade Object [ type: %s, quantity: %7d, price: %8.2f]";
		return String.format(pattern, type,quantity,  price);
	}
}
