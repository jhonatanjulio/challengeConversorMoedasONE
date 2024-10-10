package conversordemoedas;

import conversordemoedas.controller.ConverterController;
import conversordemoedas.controller.RequestApiController;
import conversordemoedas.model.FactoryRecordCurrency;
import conversordemoedas.model.RecordCurrency;
import conversordemoedas.util.HistoryConversion;
import conversordemoedas.view.ConsoleIO;

import javax.accessibility.AccessibleContext;

public class App {
    public static void main(String[] args) {
        ConsoleIO console = new ConsoleIO();
        console.startMenu();
    }
}
