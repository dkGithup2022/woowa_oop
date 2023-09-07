package com.dankimdankim.food.shop.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.dankimdankim.food.generic.money.domain.Money;

class MenuTest {

	@Test
	public void 매뉴이름_변경_오류() {
		Menu menu = ShopFixture.aMenu().build();
		assertThrows(
			IllegalArgumentException.class,
			() -> {
				menu.validateOrder("틀린이름", Arrays.asList(
					ShopFixture.anOptionGroup().build()));
			}
		);
	}

	@Test
	public void 옵션그룹이름_변경_오류() {
		Menu menu = ShopFixture.aMenu().basic(ShopFixture.anOptionGroupSpec().build()).build();
		assertThrows(
			IllegalArgumentException.class,
			() -> {
				menu.validateOrder(menu.getName(), Arrays.asList(ShopFixture.anOptionGroup().name("피구").build()));
			}
		);
	}

	@Test
	public void 옵션이름_변경_오류() {
		Menu menu = ShopFixture.aMenu().basic(ShopFixture.anOptionGroupSpec().build()).build();
		Money money = menu.getOptionGroupSpecs().get(0).getOptionSpecs().get(0).getPrice();
		String name = menu.getOptionGroupSpecs().get(0).getOptionSpecs().get(0).getName();

		assertThrows(
			IllegalArgumentException.class,
			() -> {
				menu.validateOrder(menu.getName(), Arrays.asList(
					ShopFixture.anOptionGroup().options(
						Arrays.asList(Option.builder().name(name + "!").price(money).build())
					).build()
				));
			}
		);
	}

	@Test
	public void 옵션가격_변경_오류() {

		Menu menu = ShopFixture.aMenu().basic(ShopFixture.anOptionGroupSpec().build()).build();
		Money money = menu.getOptionGroupSpecs().get(0).getOptionSpecs().get(0).getPrice();
		String name = menu.getOptionGroupSpecs().get(0).getOptionSpecs().get(0).getName();

		assertThrows(
			IllegalArgumentException.class,
			() -> {
				menu.validateOrder(menu.getName(), Arrays.asList(
					ShopFixture.anOptionGroup().options(
						Arrays.asList(Option.builder().name(name).price(money.plus(Money.wons(1))).build())
					).build()
				));
			}
		);
	}

}