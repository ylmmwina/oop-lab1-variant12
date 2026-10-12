package ua.edu.lab1.app;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.List;
import ua.edu.lab1.model.CombinedTariff;
import ua.edu.lab1.model.DataTariff;
import ua.edu.lab1.model.Tariff;
import ua.edu.lab1.model.VoiceTariff;
import ua.edu.lab1.service.MobileCompany;
import ua.edu.lab1.service.TariffCriteria;

/** Мінімальна консольна демонстрація без інтерактивного меню. */
public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        run(System.out);
    }

    public static void run(PrintStream output) {
        MobileCompany company = new MobileCompany(List.of(
                new CombinedTariff("Максимум", new BigDecimal("450"),
                        800, 1000, 50),
                new VoiceTariff("Розмова", new BigDecimal("120"),
                        1200, 300),
                new DataTariff("Онлайн", new BigDecimal("200"),
                        600, 40),
                new CombinedTariff("Баланс", new BigDecimal("250"),
                        1500, 500, 20)));
        output.println("Лабораторна 1 — Мобільний зв'язок — Варіант 12");
        output.println("Навчальні дані; плата у грн/місяць.");
        output.println("Початковий список:");
        printTariffs(company.getTariffs(), output);
        output.println("Усього клієнтів: " + company.getTotalClients());
        company.sortByMonthlyFee();
        output.println("Сортування за зростанням абонплати:");
        printTariffs(company.getTariffs(), output);
        TariffCriteria criteria = new TariffCriteria(
                new BigDecimal("200"), new BigDecimal("300"),
                0, 600, 20, 40);
        output.println("Пошук: 200–300 грн; 0–600 хв; 20–40 ГБ включно:");
        printTariffs(company.findTariffs(criteria), output);
    }

    private static void printTariffs(List<Tariff> tariffs, PrintStream output) {
        for (Tariff tariff : tariffs) {
            output.printf("%s | %s | %s грн | %d клієнтів | %d хв | %d ГБ%n",
                    tariff.getName(), tariff.getType(),
                    tariff.getMonthlyFee().toPlainString(),
                    tariff.getClientCount(), tariff.getMinutes(),
                    tariff.getInternetGb());
        }
    }
}
