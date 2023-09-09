package com.dankimdankim.food.billing.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepository extends JpaRepository<Billing, Long> {
	Optional<Billing> findByShopId(Long shopId);
}
