package com.dankimdankim.food.order.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.dankimdankim.food.generic.money.domain.Money;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Cart {
	private Long shopId;
	private Long userId;

	private List<CartLineItem> cartLineItems = new ArrayList<>();

	public Cart(Long shopId, Long userId, CartLineItem... cartLineItems) {
		this.shopId = shopId;
		this.userId = userId;
		this.cartLineItems = Arrays.asList(cartLineItems);
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class CartLineItem {
		private Long menuId;
		private String name;
		private int count;
		private List<CartOptionGroup> groups = new ArrayList<>();

		public CartLineItem(Long menuId, String name, int count, CartOptionGroup... cartOptionGroups) {
			this.menuId = menuId;
			this.name = name;
			this.count = count;
			this.groups = Arrays.asList(cartOptionGroups);
		}
	}

	@Data
	@NoArgsConstructor
	public static class CartOptionGroup {
		private String name;
		private List<CartOption> options = new ArrayList<>();

		public CartOptionGroup(String name, CartOption... options) {
			this.name = name;
			this.options = Arrays.asList(options);
		}
	}

	@Data
	@NoArgsConstructor
	public static class CartOption {
		private String name;
		private Money price;

		public CartOption(String name, Money price) {
			this.name = name;
			this.price = price;
		}
	}
}
