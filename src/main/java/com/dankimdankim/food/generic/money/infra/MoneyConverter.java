package com.dankimdankim.food.generic.money.infra;

import com.dankimdankim.food.generic.money.domain.Money;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MoneyConverter implements AttributeConverter<Money, Long> {
	@Override
	public Long convertToDatabaseColumn(Money money) {
		return money.longValue();
	}

	@Override
	public Money convertToEntityAttribute(Long amount) {
		return Money.wons(amount);
	}
}
