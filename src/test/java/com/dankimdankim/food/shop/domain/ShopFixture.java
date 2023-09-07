package com.dankimdankim.food.shop.domain;

import java.util.Arrays;

import com.dankimdankim.food.generic.money.domain.Money;
import com.dankimdankim.food.generic.money.domain.Ratio;

import com.dankimdankim.food.shop.domain.OptionGroup.OptionGroupBuilder;
import com.dankimdankim.food.shop.domain.OptionGroupSpecification.OptionGroupSpecificationBuilder;
import com.dankimdankim.food.shop.domain.Option.OptionBuilder;
import com.dankimdankim.food.shop.domain.OptionSpecification.OptionSpecificationBuilder;
import com.dankimdankim.food.shop.domain.Shop.ShopBuilder;
import com.dankimdankim.food.shop.domain.Menu.MenuBuilder;
public class ShopFixture {

	public static ShopBuilder aShop(){
		return Shop.builder()
			.id(1L)
			.open(true)
			.name("느티")
			.commissionRate(Ratio.valueOf(0.1))
			.minOrderAmount(Money.wons(1000));
	}

	public static MenuBuilder aMenu(){
		return Menu.builder()
			.id(1L)
			.name("커피 세트")
			.description("커피 + 아무 디저트 1개")
			.shopId(aShop().build().getId())
			.basic(anOptionGroupSpec().build())
			.additives(Arrays.asList(anOptionGroupSpec().build()));
	}

	public static OptionGroupBuilder anOptionGroup(){
		return OptionGroup.builder().name("커피").
		options(Arrays.asList(anOption().build()));
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
