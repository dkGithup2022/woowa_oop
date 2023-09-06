package com.dankimdankim.food.generic.money.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class Ratio {
	@Getter
	private double rate;

	Ratio(double rate){
		this.rate = rate;
	}

	Ratio(){}

	public static Ratio valueOf(double rate){
		return new Ratio(rate);
	}

	public Money of(Money price){
		return price.times(rate);
	}

}
