package com.dankimdankim.food.order.domain;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.List;

import com.dankimdankim.food.generic.money.domain.Money;
import com.dankimdankim.food.shop.domain.OptionGroup;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "ORDER_LINE_ITMES")
@Getter
public class OrderLineItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ORDER_LINE_ITEM_ID")
	private Long id;

	private Long menuId;

	private String name;

	private int count;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ORDER_LINE_ITEM_ID")
	private List<OrderOptionGroup> groups = new ArrayList<>();

	public OrderLineItem(Long menuId, String name, int count, List<OrderOptionGroup> groups) {
		this(null, menuId, name, count, groups);
	}

	@Builder
	public OrderLineItem(Long id, Long menuId, String name, int count, List<OrderOptionGroup> groups) {
		this.id = id;
		this.menuId = menuId;
		this.name = name;
		this.count = count;
		this.groups.addAll(groups);
	}

	OrderLineItem(){}


	public Money calculatePrice(){
		return Money.sum(groups, OrderOptionGroup::calculatePrice).times(count);
	}

	public List<OptionGroup> convertToOptionGroups(){
		return groups.stream().map(OrderOptionGroup::convertToOptionGroup).collect(toList());
	}
}
