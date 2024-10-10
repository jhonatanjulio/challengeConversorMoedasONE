package conversordemoedas.model;

public class Converter {
    private double conversionRate;

    public Converter(double conversionRate) {
        this.conversionRate = conversionRate;
    }

    public double convert(double valueInput) {
        return valueInput * this.conversionRate;
    }
}
