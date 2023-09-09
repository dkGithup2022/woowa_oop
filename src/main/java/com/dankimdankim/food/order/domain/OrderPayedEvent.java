package com.dankimdankim.food.order.domain;

public class OrderPayedEvent {
	private Order order;

	public OrderPayedEvent(Order order) {
		this.order = order;
	}

	public Long getOrderId() {
		return order.getId();
	}
}
