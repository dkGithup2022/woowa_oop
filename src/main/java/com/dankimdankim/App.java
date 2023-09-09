package com.dankimdankim;

import java.util.Arrays;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dankimdankim.food.generic.money.domain.Money;
import com.dankimdankim.food.generic.money.domain.Ratio;
import com.dankimdankim.food.order.service.Cart;
import com.dankimdankim.food.order.service.OrderService;
import com.dankimdankim.food.shop.domain.Menu;
import com.dankimdankim.food.shop.domain.MenuRepository;
import com.dankimdankim.food.shop.domain.Option;
import com.dankimdankim.food.shop.domain.OptionGroupSpecification;
import com.dankimdankim.food.shop.domain.OptionSpecification;
import com.dankimdankim.food.shop.domain.Shop;
import com.dankimdankim.food.shop.domain.ShopRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class App implements ApplicationRunner {

	private final ShopRepository shopRepository;
	private final MenuRepository menuRepository;
	private final OrderService orderService;

	private Long shopId = 1L;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {


		log.info("Hello world");
		loadInitialData();

		log.info("Data uploaded");

		Cart cart = new Cart(1L, 1L,
			new Cart.CartLineItem(1L, "매뉴", 1,
				new Cart.CartOptionGroup("기본 옵션 1",
					new Cart.CartOption("기본으로 선택됨", Money.wons(1000L))
					),
				new Cart.CartOptionGroup("옵션 2",
					new Cart.CartOption("이 옵션이 있으면 주문 가능 금액임", Money.wons(5000L))
					)
			)
		);

		orderService.placeOrder(cart);
		orderService.payOrder(1L);
		orderService.deliverOrder(1L);
	}

	public void loadInitialData() {
		Shop shop = Shop.builder()
			.id(shopId)
			.name("테스트숍")
			.commissionRate(Ratio.valueOf(0.1))
			.minOrderAmount(Money.wons(5000))
			.open(true)
			.build();

		shopRepository.save(shop);

		Menu menu = Menu.builder()
			.name("매뉴")
			.shopId(shopId)
			.description("테스트용 기본 매뉴에용")
			.basic(
				OptionGroupSpecification.builder()
					.name("기본 옵션 1")
					.options(
						Arrays.asList(
							OptionSpecification.builder()
								.name("기본으로 선택됨")
								.price(Money.wons(1000L))
								.build()
						)
					)
					.build()
			)
			.additives(
				Arrays.asList(
					OptionGroupSpecification.builder()
						.name("옵션 2")
						.options(
							Arrays.asList(
								OptionSpecification.builder()
									.name("이 옵션이 있으면 주문 가능 금액임")
									.price(Money.wons(5000L))
									.build()
							)
						)
						.build()
				)
			)
			.build();

		menuRepository.save(menu);
	}

}
