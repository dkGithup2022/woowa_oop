package com.dankimdankim.food.shop.domain;

import com.dankimdankim.food.generic.money.domain.Money;
import com.dankimdankim.food.generic.money.domain.Ratio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "SHOPS")
@Getter
public class Shop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SHOP_ID")
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "OPEN")
	private boolean open;

	@Column(name = "MIN_ORDER_AMOUNT")
	private Money minOrderAmount;

	@Column(name = "COMMISSION_RATE")
	private Ratio commissionRate;

	public Shop(String name, boolean open, Money minOrderAmount) {
		this(name, open, minOrderAmount, Ratio.valueOf(0));
	}

	public Shop(String name, boolean open, Money minOrderAmount, Ratio commissionRate) {
		this(null, name, open, minOrderAmount, commissionRate);
	}

	@Builder
	public Shop(Long id, String name, boolean open, Money minOrderAmount, Ratio commissionRate) {
		this.id = id;
		this.name = name;
		this.open = open;
		this.minOrderAmount = minOrderAmount;
		this.commissionRate = commissionRate;
	}

	Shop() {
	}

	public void open() {
		this.open = true;
	}

	public void close() {
		this.open = false;
	}

	public void modifyCommissionRate(Ratio commissionRate) {
		this.commissionRate = commissionRate;
	}

	public Money calculateCommissionFee(Money price) {
		return commissionRate.of(price);
	}
}
