package com.dankimdankim.food.generic.money.infra;

import com.dankimdankim.food.generic.money.domain.Ratio;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RatioConverter implements AttributeConverter<Ratio, Double> {

	@Override
	public Double convertToDatabaseColumn(Ratio ratio) {
		return ratio.getRate();
	}

	@Override
	public Ratio convertToEntityAttribute(Double rate) {
		return Ratio.valueOf(rate);
	}
}
