package com.dankimdankim.food.order.domain;

import com.dankimdankim.food.generic.money.domain.Money;
import com.dankimdankim.food.shop.domain.Option;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;

@Embeddable
@Getter
public class OrderOption {
	@Column(name = "NAME")
	private String name;

	@Column(name = "PRICE")
	private Money price;

	@Builder
	public OrderOption(String name, Money price) {
		this.name = name;
		this.price = price;
	}

	OrderOption() {
	}

	public Option convertToOption() {
		return new Option(name, price);
	}

}
