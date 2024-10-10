package conversordemoedas.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HistoryConversion {
    private List<String> historyLines = new ArrayList<>();
    private final int MAX_LINES;

    public HistoryConversion(int MAX_LINES) {
        this.MAX_LINES = MAX_LINES;
    }

    public void addToHistory(String line) {
        Locale locale = Locale.getDefault();
        String timeNow = DateTimeFormatter
                .ofLocalizedTime(FormatStyle.SHORT)
                .withLocale(locale)
                .format(LocalTime.now());

        if (historyLines.size() < MAX_LINES) {
            historyLines.add(timeNow + ": " + line + "\n");
        } else {
            historyLines.remove(0);
            historyLines.add(timeNow + ": " + line + "\n");
        }
    }

    public void saveLogHistory() {
        Locale locale = Locale.getDefault();
        String date = DateTimeFormatter
                .ofLocalizedDate(FormatStyle.MEDIUM)
                .withLocale(locale)
                .format(LocalDateTime.now());
        String dateTimeNow = DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.SHORT)
                .withLocale(locale)
                .format(LocalDateTime.now());
        try {
            if (!this.historyLines.isEmpty()) {
                File dir = new File("./resources/" + date);
                if (!dir.exists()) {
                    if (!dir.mkdir()) { System.out.println("Erro ao criar a pasta do log."); }
                }
                File log = new File(dir, dateTimeNow
                        .replaceAll("/", "-")
                        .replace(":", "h") + ".txt");

                if (!log.createNewFile()) { System.out.println("Erro ao criar o log."); }

                FileWriter writer = new FileWriter(log);
                PrintWriter printWriter = new PrintWriter(writer);
                printWriter.println(String.join("", this.historyLines));
                printWriter.flush();
                printWriter.close();
                System.out.println("\nSalvando log de conversões...");
            }
        } catch (IOException e) {
            System.out.println("Erro! Não foi possível escrever o arquivo.");
        }
    }

    public void printHistory() {
        System.out.println(String.join("", this.historyLines));
    }
}
