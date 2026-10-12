package ua.edu.lab1.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import ua.edu.lab1.model.Tariff;

/** Компанія володіє власним списком тарифів, а не успадковує тариф. */
public final class MobileCompany {
    private final List<Tariff> tariffs;

    public MobileCompany(List<Tariff> tariffs) {
        this.tariffs = new ArrayList<>(List.copyOf(tariffs));
    }

    public List<Tariff> getTariffs() {
        return List.copyOf(tariffs);
    }

    /** Навчальна модель: кожен клієнт належить одному тарифу. */
    public long getTotalClients() {
        return tariffs.stream().mapToLong(Tariff::getClientCount).sum();
    }

    /** Зростання плати; за однакової плати зберігається початковий порядок. */
    public void sortByMonthlyFee() {
        tariffs.sort(Comparator.comparing(Tariff::getMonthlyFee));
    }

    public List<Tariff> findTariffs(TariffCriteria criteria) {
        Objects.requireNonNull(criteria, "Критерії обов'язкові");
        return tariffs.stream().filter(criteria::matches).toList();
    }
}
