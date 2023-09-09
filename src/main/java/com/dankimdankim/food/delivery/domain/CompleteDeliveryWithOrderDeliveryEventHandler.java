package com.dankimdankim.food.delivery.domain;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dankimdankim.food.order.domain.OrderDeliveredEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CompleteDeliveryWithOrderDeliveryEventHandler {

	private final DeliveryRepository deliveryRepository;

	@Async
	@EventListener
	@Transactional
	public void handle(OrderDeliveredEvent event){
		Delivery delivery = deliveryRepository.findById(event.getOrderId())
			.orElseThrow(IllegalArgumentException::new);
		delivery.complete();

	}
}
