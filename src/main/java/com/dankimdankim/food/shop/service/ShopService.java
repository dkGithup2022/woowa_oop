package com.dankimdankim.food.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dankimdankim.food.shop.domain.Menu;
import com.dankimdankim.food.shop.domain.MenuRepository;
import com.dankimdankim.food.shop.domain.Shop;
import com.dankimdankim.food.shop.domain.ShopRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopService {
	private final ShopRepository shopRepository;
	private final MenuRepository menuRepository;

	@Transactional
	public MenuBoard getMenuBoard(Long shopId) {
		Shop shop = shopRepository.findById(shopId).orElseThrow(IllegalArgumentException::new);
		List<Menu> menus = menuRepository.findByShopId(shopId);
		return new MenuBoard(shop, menus);
	}

}
