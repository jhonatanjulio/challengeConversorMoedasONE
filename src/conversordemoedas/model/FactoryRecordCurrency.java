package conversordemoedas.model;

import java.util.HashMap;

public abstract class FactoryRecordCurrency {
    public enum Currencies{
        USD("Dólar Americano", "U$"),
        CAD("Dólar Canadense", "C$"),
        BRL("Real Brasileiro", "R$"),
        CNY("Yuan Chinês", "¥"),
        JPY("Iene Japonês", "¥"),
        ARS("Peso Argentino", "$"),
        BOB("Boliviano da Bolívia", "Bs"),
        CLP("Peso Chileno", "$"),
        COP("Peso Colombiano", "$");

        private final String description;
        private final String symbol;

        Currencies(String description, String symbol) {
            this.description = description;
            this.symbol = symbol;
        }

        public String getDescription() {
            return description;
        }

        public String getSymbol() {
            return symbol;
        }
    }

    public static RecordCurrency createPairBases(Currencies currencyFrom, Currencies currencyTo) {
        HashMap<String, String> names = new HashMap<>();
        HashMap<String, String> baseCodes = new HashMap<>();
        names.put(currencyFrom.getDescription(), currencyTo.getDescription());
        baseCodes.put(currencyFrom.name(), currencyTo.name());
        return new RecordCurrency(names, baseCodes, currencyTo.getSymbol());
    }
}
