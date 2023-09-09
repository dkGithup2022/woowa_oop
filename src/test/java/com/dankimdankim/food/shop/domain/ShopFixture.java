package com.dankimdankim.food.shop.domain;

import java.time.LocalDateTime;
import java.util.Arrays;

import com.dankimdankim.food.billing.domain.Billing;
import com.dankimdankim.food.delivery.domain.Delivery;
import com.dankimdankim.food.generic.money.domain.Money;
import com.dankimdankim.food.generic.money.domain.Ratio;

import com.dankimdankim.food.billing.domain.Billing.BillingBuilder;
import com.dankimdankim.food.delivery.domain.Delivery.DeliveryBuilder;
import com.dankimdankim.food.order.domain.OrderOptionGroup.OrderOptionGroupBuilder;
import com.dankimdankim.food.order.domain.OrderOption.OrderOptionBuilder;
import com.dankimdankim.food.order.domain.OrderLineItem.OrderLineItemBuilder;
import com.dankimdankim.food.order.domain.Order.OrderBuilder;
import com.dankimdankim.food.order.domain.Order;
import com.dankimdankim.food.order.domain.OrderLineItem;
import com.dankimdankim.food.order.domain.OrderOption;
import com.dankimdankim.food.order.domain.OrderOptionGroup;
import com.dankimdankim.food.shop.domain.OptionGroup.OptionGroupBuilder;
import com.dankimdankim.food.shop.domain.OptionGroupSpecification.OptionGroupSpecificationBuilder;
import com.dankimdankim.food.shop.domain.Option.OptionBuilder;
import com.dankimdankim.food.shop.domain.OptionSpecification.OptionSpecificationBuilder;
import com.dankimdankim.food.shop.domain.Shop.ShopBuilder;
import com.dankimdankim.food.shop.domain.Menu.MenuBuilder;

public class ShopFixture {

	public static ShopBuilder aShop() {
		return Shop.builder()
			.id(1L)
			.open(true)
			.name("느티")
			.commissionRate(Ratio.valueOf(0.1))
			.minOrderAmount(Money.wons(1000));
	}

	public static MenuBuilder aMenu() {
		return Menu.builder()
			.id(1L)
			.name("커피 세트")
			.description("커피 + 아무 디저트 1개")
			.shopId(aShop().build().getId())
			.basic(anOptionGroupSpec().build())
			.additives(Arrays.asList(anOptionGroupSpec().build()));
	}

	public static OptionGroupBuilder anOptionGroup() {
		return OptionGroup.builder().name("커피").
			options(Arrays.asList(anOption().build()));
	}

	public static OptionGroupSpecificationBuilder anOptionGroupSpec() {
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

	private static OptionBuilder anOption() {
		return Option.builder().name("기본").price(Money.wons(10000));
	}

	public static OrderBuilder anOrder() {
		return Order.builder()
			.id(1L)
			.userId(1L)
			.shopId(aShop().build().getId())
			.orderStatus(Order.OrderStatus.ORDERED)
			.orderedTime(LocalDateTime.of(2023, 9, 9, 0, 0))
			.orderLineItems(Arrays.asList(
				anOrderLineItem().build()
			));
	}

	public static OrderLineItemBuilder anOrderLineItem() {
		return OrderLineItem.builder()
			.menuId(aMenu().build().getId())
			.name("커피")
			.count(1)
			.groups(Arrays.asList(
					anOrderOptionGroup()
						.name("사이즈")
						.options(Arrays.asList(
							anOrderOption().name("투샷").price(Money.wons(2000)).build())
						)
						.build(),
					anOrderOptionGroup()
						.name("디저트")
						.options(Arrays.asList(
							anOrderOption().name("작은 쿠키").price(Money.wons(1000)).build()
						))
						.build()
				)
			);
	}

	public static OrderOptionGroupBuilder anOrderOptionGroup() {
		return OrderOptionGroup.builder()
			.name("사이즈")
			.options(Arrays.asList(anOrderOption().build()));
	}

	private static OrderOptionBuilder anOrderOption() {
		return OrderOption.builder()
			.name("1L")
			.price(Money.wons(3000L));
	}

	public static DeliveryBuilder anDelivery() {
		return Delivery.builder()
			.id(1L)
			.deliveryStatus(Delivery.DeliveryStatus.DELIVERING)
			.orderId(anOrder().build().getId());
	}

	public static BillingBuilder aBilling() {
		return Billing.builder()
			.id(1L)
			.shopId(aShop().build().getId())
			.commission(Money.ZERO);
	}

}
