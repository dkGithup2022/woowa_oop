package com.dankimdankim.food.billing.domain;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dankimdankim.food.order.domain.OrderDeliveredEvent;
import com.dankimdankim.food.shop.domain.Shop;
import com.dankimdankim.food.shop.domain.ShopRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BillShopWithOrderDeliveredEventHandler {
	private final ShopRepository shopRepository;
	private final BillingRepository billingRepository;

	@Async
	@EventListener
	@Transactional
	public void handle(OrderDeliveredEvent event) {
		Shop shop = shopRepository.findById(event.getShopId())
			.orElseThrow(IllegalArgumentException::new);

		Billing billing = billingRepository.findByShopId(event.getShopId())
			.orElse(new Billing(event.getShopId()));

		billing.billComissionFee(shop.calculateCommissionFee(event.getTotalPrice()));
	}



}
