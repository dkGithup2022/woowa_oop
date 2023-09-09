package com.dankimdankim.food.billing.domain;

import com.dankimdankim.food.generic.money.domain.Money;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "BILLINGS")
@Getter
public class Billing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BILLING_ID")
	private Long id;

	private Long shopId;

	private Money commission = Money.ZERO;

	public Billing(Long shopId) {
		this(null, shopId, Money.ZERO);
	}

	@Builder
	public Billing(Long id, Long shopId, Money commission) {
		this.id = id;
		this.shopId = shopId;
		this.commission = commission;
	}

	public void billComissionFee(Money commission) {
		this.commission = this.commission.plus(commission);
	}
}
