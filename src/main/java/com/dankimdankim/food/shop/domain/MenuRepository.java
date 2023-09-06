package com.dankimdankim.food.shop.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
	List<Menu> findByShopId(Long shopId);
}
