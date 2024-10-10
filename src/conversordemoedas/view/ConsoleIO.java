package conversordemoedas.view;

import conversordemoedas.controller.ConverterController;
import conversordemoedas.controller.RequestApiController;
import conversordemoedas.model.FactoryRecordCurrency;
import conversordemoedas.model.RecordCurrency;
import conversordemoedas.util.HistoryConversion;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleIO {
    private final HistoryConversion history = new HistoryConversion(5);

    public void startMenu() {
        userInteraction();
    }

    private void printMenu(int whichMenu) {
        //menu 1
        String initMenu = """
                ***************************************
                Bem vindo ao Conversor de Moedas do Challenge ONE!
                
                Selecione uma opção:
                
                1 -> Selecionar câmbios
                2 -> Exibir histórico de conversões
                3 -> Sair
                ***************************************
                """;

        //menu 2
        StringBuilder currencyMenuFrom = new StringBuilder("""
                ***************************************
                Selecione a moeda a ser convertida:
                """);
        //menu 3
        StringBuilder currencyMenuTo = new StringBuilder("""
                ***************************************
                Selecione a moeda para qual converter:
                """);

        //concatenar strings para ambas strings currency acima
        FactoryRecordCurrency.Currencies[] currencies = FactoryRecordCurrency.Currencies.values();
        for(int i = 0; i < currencies.length; i++) {
            currencyMenuFrom.append("\n%d -> %s: %s".formatted(
                    i + 1,
                    currencies[i].name(),
                    currencies[i].getDescription()));

            currencyMenuTo.append("\n%d -> %s: %s".formatted(
                    i + 1,
                    currencies[i].name(),
                    currencies[i].getDescription()));
        }
        currencyMenuFrom.append("\n***************************************");
        currencyMenuTo.append("\n***************************************");

        switch (whichMenu) {
            case 1 -> System.out.println(initMenu);
            case 2 -> System.out.println(currencyMenuFrom);
            case 3 -> System.out.println(currencyMenuTo);
        }
    }

    private void userInteraction() {
        final String API_KEY = "0b900475dda01830db451458";
        int userChoice = 0;

        while (userChoice != 3) {
            try {
                this.printMenu(1); // imprimir o primeiro menu
                Scanner scanner = new Scanner(System.in);
                userChoice = scanner.nextInt();

                switch (userChoice) {
                    case 1 -> {
                        printMenu(2); // imprimir o segundo menu
                        Scanner scannerCurrency = new Scanner(System.in);
                        int option = scannerCurrency.nextInt();
                        FactoryRecordCurrency.Currencies currencyFrom = FactoryRecordCurrency.Currencies.values()[option - 1];
                        System.out.println("Moeda selecionada: " + currencyFrom.getDescription());

                        printMenu(3); // imprimir o ultimo menu
                        option = scannerCurrency.nextInt();
                        FactoryRecordCurrency.Currencies currencyTo = FactoryRecordCurrency.Currencies.values()[option - 1];
                        System.out.println("Moeda selecionada: " + currencyTo.getDescription());

                        Scanner value = new Scanner(System.in);
                        System.out.println("***************************************");
                        System.out.printf("\nDigite o valor a ser convertido: %s%n", currencyFrom.getSymbol());
                        double insertedValue = value.nextDouble();

                        RequestApiController request = new RequestApiController();
                        RecordCurrency record = FactoryRecordCurrency.createPairBases(currencyFrom, currencyTo);
                        ConverterController converter = new ConverterController(record);
                        String result = converter.convert(
                                insertedValue,
                                request.getExchangeRate(record.baseCodes(), API_KEY));

                        history.addToHistory(result);
                        System.out.println("Resultado da conversão:");
                        System.out.println(result + "\n");
                    }
                    case 2 -> history.printHistory();
                    case 3 -> {
                        history.saveLogHistory();
                        System.out.println("Saindo da aplicação...");
                    }
                    default -> System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
}
