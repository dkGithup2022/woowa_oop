package com.dankimdankim.food.order.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dankimdankim.food.order.domain.Order;
import com.dankimdankim.food.order.domain.OrderRepository;
import com.dankimdankim.food.order.domain.OrderValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
	private final OrderValidator orderValidator;
	private final OrderMapper orderMapper;

	@Transactional
	public void placeOrder(Cart cart) {
		Order order = orderMapper.mapFrom(cart);
		order.place(orderValidator);
		orderRepository.save(order);
	}

	@Transactional
	public void payOrder(Long orderId) {
		Order order = orderRepository.findById(orderId)
			.orElseThrow(IllegalArgumentException::new);
		order.payed();
		orderRepository.save(order);
	}

	@Transactional
	public void deliverOrder(Long orderId) {
		Order order = orderRepository.findById(orderId).orElseThrow(IllegalArgumentException::new);
		order.delivered();
		orderRepository.save(order);
	}
}
