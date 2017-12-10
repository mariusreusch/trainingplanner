package ch.mare.trainingplanner.domain.jpa;

import org.javamoney.moneta.Money;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class MoneyConverter implements AttributeConverter<Money, Double> {

    @Override
    public Double convertToDatabaseColumn(Money money) {
        return money.getNumber().doubleValue();
    }

    @Override
    public Money convertToEntityAttribute(Double dbData) {
        return Money.of(dbData, "CHF");
    }
}
