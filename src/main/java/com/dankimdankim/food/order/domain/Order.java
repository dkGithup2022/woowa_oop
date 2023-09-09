package com.dankimdankim.food.order.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.AbstractAggregateRoot;

import com.dankimdankim.food.generic.money.domain.Money;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "ORDERS")
@Getter
public class Order extends AbstractAggregateRoot<Order> {
	public enum OrderStatus {ORDERED, PAYED, DELIVERED};

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ORDER_ID")
	private Long id;

	@Column(name = "USER_ID")
	private Long userId;

	@Column(name = "SHOP_ID")
	private Long shopId;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ORDER_ID")
	private List<OrderLineItem> orderLineItems = new ArrayList<>();

	@Column(name = "ORDERED_TIME")
	private LocalDateTime orderedTime;

	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

	public Order(Long userId, Long shopId, List<OrderLineItem> orderLineItems) {
		this(null, userId, shopId, OrderStatus.ORDERED, LocalDateTime.now(), orderLineItems);
	}

	@Builder
	public Order(Long id, Long userId, Long shopId, OrderStatus orderStatus, LocalDateTime orderedTime,
		List<OrderLineItem> orderLineItems) {
		this.id = id;
		this.userId = userId;
		this.shopId = shopId;
		this.orderStatus = orderStatus;
		this.orderedTime = orderedTime;
		this.orderLineItems = orderLineItems;
	}

	Order() {
	}

	public List<Long> getMenuIds() {
		return orderLineItems.stream().map(
			OrderLineItem::getMenuId
		).collect(Collectors.toList());
	}

	public void place(OrderValidator orderValidator) {
		orderValidator.validate(this);
		ordered();
	}

	public void ordered() {
		this.orderStatus = OrderStatus.ORDERED;
	}

	public void payed() {
		this.orderStatus = OrderStatus.PAYED;
		registerEvent(new OrderPayedEvent(this));
	}

	public void delivered() {
		this.orderStatus = OrderStatus.DELIVERED;
		registerEvent(new OrderDeliveredEvent(this));
	}

	public Money calculateTotalPrice() {
		return Money.sum(orderLineItems, OrderLineItem::calculatePrice);
	}
}
