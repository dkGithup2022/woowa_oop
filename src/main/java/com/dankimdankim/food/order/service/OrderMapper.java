package com.dankimdankim.food.order.service;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.dankimdankim.food.order.domain.Order;
import com.dankimdankim.food.order.domain.OrderLineItem;
import com.dankimdankim.food.order.domain.OrderOption;
import com.dankimdankim.food.order.domain.OrderOptionGroup;

@Component
public class OrderMapper {
	public Order mapFrom(Cart cart) {
		return new Order(
			cart.getUserId(),
			cart.getShopId(),
			cart.getCartLineItems()
				.stream().map(this::toOrderLineItem)
				.collect(Collectors.toList())
		);
	}

	private OrderLineItem toOrderLineItem(Cart.CartLineItem cartLineItem) {
		return new OrderLineItem(
			cartLineItem.getMenuId(),
			cartLineItem.getName(),
			cartLineItem.getCount(),
			cartLineItem.getGroups()
				.stream()
				.map(this::toOrderOptionGroup)
				.collect(Collectors.toList())
		);
	}

	private OrderOptionGroup toOrderOptionGroup(Cart.CartOptionGroup cartOptionGroup) {
		return new OrderOptionGroup(
			cartOptionGroup.getName(),
			cartOptionGroup.getOptions()
				.stream()
				.map(this::toOrderOption)
				.collect(Collectors.toList())
		);
	}

	private OrderOption toOrderOption(Cart.CartOption cartOption) {
		return new OrderOption(
			cartOption.getName(),
			cartOption.getPrice()
		);
	}

}
