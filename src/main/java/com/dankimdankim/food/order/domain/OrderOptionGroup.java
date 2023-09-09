package com.dankimdankim.food.order.domain;

import java.util.List;
import java.util.stream.Collectors;

import com.dankimdankim.food.generic.money.domain.Money;
import com.dankimdankim.food.shop.domain.OptionGroup;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "ORDER_OPTION_GROUPS")
@Getter
public class OrderOptionGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ORDER_OPTION_GROUP_ID")
	private Long id;


	@Column(name="NAME")
	private String name;


	@ElementCollection
	@CollectionTable(name = "ORDER_OPTIONS", joinColumns = @JoinColumn(name = "ORDER_OPTION_GROUP_ID"))
	private List<OrderOption> orderOptions;

	public  OrderOptionGroup(String name, List<OrderOption> options){
		this(null, name, options);
	}

	@Builder
	public OrderOptionGroup(Long id, String name, List<OrderOption> options){
		this.id = id;
		this.name = name;
		this.orderOptions = options;
	}

	OrderOptionGroup(){}

	public Money calculatePrice(){
		return Money.sum(orderOptions, OrderOption::getPrice);
	}

	public OptionGroup convertToOptionGroup(){
		return new OptionGroup(name, orderOptions.stream().map(OrderOption::convertToOption).collect(Collectors.toList()));
	}
}
