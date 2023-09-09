package com.dankimdankim.food.order.domain;

import static java.util.function.Function.*;
import static java.util.stream.Collectors.*;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.dankimdankim.food.shop.domain.Menu;
import com.dankimdankim.food.shop.domain.MenuRepository;
import com.dankimdankim.food.shop.domain.OptionGroupSpecification;
import com.dankimdankim.food.shop.domain.Shop;
import com.dankimdankim.food.shop.domain.ShopRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderValidator {
	private final ShopRepository shopRepository;
	private final MenuRepository menuRepository;

	public void validate(Order order) {
		validate(order, getShop(order), getMenus(order));
	}

	void validate(Order order, Shop shop, Map<Long, Menu> menus) {
		if (!shop.isOpen()) {
			throw new IllegalArgumentException("영업중이 아닌 가게");
		}


		if (order.getOrderLineItems().isEmpty()) {
			throw new IllegalStateException("주문 항목이 비어 있습니다.");
		}

		if (!shop.isValidOrderAmount(order.calculateTotalPrice())) {
			throw new IllegalStateException(String.format("최소 주문 금액 %s 이상을 주문해주세요.", shop.getMinOrderAmount().doubleValue()));
		}


		for (OrderLineItem item : order.getOrderLineItems()) {
			validateOrderLineItem(item, menus.get(item.getMenuId()));
		}
	}

	private void validateOrderLineItem(OrderLineItem item, Menu menu) {
		if (!menu.getName().equals(item.getName())) {
			throw new IllegalArgumentException("기본 상품이 변경되었습니다.");
		}
		for (OrderOptionGroup group : item.getGroups()) {
			validateOrderOptionGroup(group, menu);
		}
	}

	private void validateOrderOptionGroup(OrderOptionGroup group, Menu menu) {
		for (OptionGroupSpecification spec : menu.getOptionGroupSpecs()) {
			if (spec.isSatisfiedBy(group.convertToOptionGroup()))
				return;
		}
		throw new IllegalArgumentException("매뉴가 변경됐습니다.");
	}

	private Map<Long, Menu> getMenus(Order order) {
		return menuRepository.findAllById(order.getMenuIds()).stream().collect(toMap(Menu::getId, identity()));
	}

	private Shop getShop(Order order) {
		return shopRepository.findById(order.getShopId()).orElseThrow(IllegalArgumentException::new);
	}
}
