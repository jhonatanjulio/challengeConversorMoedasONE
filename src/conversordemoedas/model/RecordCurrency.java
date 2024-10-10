package conversordemoedas.model;

import java.util.HashMap;

public record RecordCurrency(HashMap<String, String> name, HashMap<String, String> baseCodes, String symbol) {
}
