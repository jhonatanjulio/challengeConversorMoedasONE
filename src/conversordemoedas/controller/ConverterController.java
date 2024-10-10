package conversordemoedas.controller;

import conversordemoedas.model.Converter;
import conversordemoedas.model.RecordCurrency;

import java.text.NumberFormat;
import java.util.Locale;

public class ConverterController {
    private final RecordCurrency pairBase;

    public ConverterController(RecordCurrency pairBase) {
        this.pairBase = pairBase;
    }

    public String convert(double valueInput, double conversionRate) {
        Converter converter = new Converter(conversionRate);

        String nameFrom = (String) this.pairBase.name().keySet().toArray()[0];
        String nameTo = (String) this.pairBase.name().values().toArray()[0];

        Locale locale = new Locale((String) pairBase.baseCodes().values().toArray()[0]);
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        String formattedValue = currencyFormat.format(converter.convert(valueInput)).substring(1);

        return "%s -> %s: %s %s".formatted(nameFrom, nameTo, pairBase.symbol(), formattedValue);
    }
}
