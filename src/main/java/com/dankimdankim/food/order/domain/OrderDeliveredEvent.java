package com.dankimdankim.food.order.domain;

import com.dankimdankim.food.generic.money.domain.Money;

public class OrderDeliveredEvent {
	private Order order;

	public OrderDeliveredEvent(Order order){
		this.order = order;
	}
	public Long getOrderId(){
		return order.getId();
	}

	public Long getShopId(){
		return order.getShopId();
	}

	public Money getTotalPrice(){
		return order.calculateTotalPrice();
	}
}
