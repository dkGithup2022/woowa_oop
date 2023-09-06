package com.dankimdankim.food.shop.domain;

import java.util.Arrays;

import com.dankimdankim.food.generic.money.domain.Money;
import com.dankimdankim.food.generic.money.domain.Ratio;

import com.dankimdankim.food.shop.domain.OptionGroupSpecification.OptionGroupSpecificationBuilder;
import com.dankimdankim.food.shop.domain.Option.OptionBuilder;
import com.dankimdankim.food.shop.domain.OptionSpecification.OptionSpecificationBuilder;
public class ShopFixture {

	public static Shop aShop(){
		return Shop.builder()
			.id(1L)
			.open(true)
			.name("느티")
			.commissionRate(Ratio.valueOf(0.1))
			.minOrderAmount(Money.wons(1000))
			.build();
	}

	public static Menu aMenu(){
		return Menu.builder()
			.id(1L)
			.name("커피 세트")
			.description("커피 + 아무 디저트 1개")
			.shopId(aMenu().getShopId())
			.basic(anOptionGroupSpec().build())
			.additives(Arrays.asList(anOptionGroupSpec().build()))
			.build();
	}

	public static OptionGroupSpecificationBuilder anOptionGroupSpec(){
		return OptionGroupSpecification.builder()
			.basic(true)
			.exclusive(true)
			.name("기본 OptionGroupSpec")
			.options(Arrays.asList(anOptionSpec().build()))
			;
	}

	private static OptionSpecificationBuilder anOptionSpec() {
		return OptionSpecification.builder().name("기본 옵션").price(Money.wons(10000));
	}


	private static OptionBuilder anOption(){
		return Option.builder().name("기본").price(Money.wons(10000));
	}


}
