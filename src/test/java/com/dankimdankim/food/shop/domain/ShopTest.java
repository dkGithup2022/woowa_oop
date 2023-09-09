package com.dankimdankim.food.shop.domain;

import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.dankimdankim.food.generic.money.domain.Money;
import com.dankimdankim.food.generic.money.domain.Ratio;

class ShopTest {

	@Test
	public void 최소주문금액_체크() {
		Long minOrderAmount = 999999L;
		Shop shop = ShopFixture.aShop().minOrderAmount(Money.wons(minOrderAmount)).build();

		assertFalse(shop.isValidOrderAmount(Money.ZERO));
		assertTrue(shop.isValidOrderAmount(Money.wons(minOrderAmount)));
		assertTrue(shop.isValidOrderAmount(Money.wons(minOrderAmount + 1)));
	}

	@Test
	public void 수수료_계산() {
		Shop shop = ShopFixture.aShop()
			.commissionRate(Ratio.valueOf(0.1))
			.build();

		assertTrue(
			shop.calculateCommissionFee(Money.wons(1000))
				.equals(Money.wons(100))
		);
	}
}