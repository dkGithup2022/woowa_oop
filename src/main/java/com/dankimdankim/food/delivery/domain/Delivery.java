package com.dankimdankim.food.delivery.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "DELIVERIES")
@Getter
public class Delivery {
	public enum DeliveryStatus {DELIVERING, DELIVERED}
	;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DELIVERY_ID")
	private Long id;

	@Column(name = "OREDR_ID")
	private Long orderId;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private DeliveryStatus deliveryStatus;

	public static Delivery started(Long orderId) {
		return new Delivery(orderId, DeliveryStatus.DELIVERING);
	}

	public Delivery(Long orderId, DeliveryStatus deliveryStatus) {
		this(null, orderId, deliveryStatus);
	}

	@Builder
	public Delivery(Long id, Long orderId, DeliveryStatus deliveryStatus) {
		this.id = id;
		this.orderId = orderId;
		this.deliveryStatus = deliveryStatus;
	}

	Delivery() {
	}

	public void complete() {
		this.deliveryStatus = DeliveryStatus.DELIVERED;
	}
}
