package com.dankimdankim.food.delivery.domain;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dankimdankim.food.order.domain.OrderPayedEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StartDeliveryWithOrderPayedEventHandler {
	private final DeliveryRepository deliveryRepository;

	@Async
	@EventListener
	@Transactional
	public void handle(OrderPayedEvent event) {
		Delivery delivery = Delivery.started(event.getOrderId());
		deliveryRepository.save(delivery);
	}
}
