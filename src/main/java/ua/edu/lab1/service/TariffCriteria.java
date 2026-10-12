package ua.edu.lab1.service;

import java.math.BigDecimal;
import java.util.Objects;
import ua.edu.lab1.model.Tariff;

/** Включні межі плати у гривнях, хвилин та інтернету у ГБ. */
public final class TariffCriteria {
    private final BigDecimal minFee;
    private final BigDecimal maxFee;
    private final int minMinutes;
    private final int maxMinutes;
    private final int minGb;
    private final int maxGb;

    public TariffCriteria(BigDecimal minFee, BigDecimal maxFee,
                          int minMinutes, int maxMinutes,
                          int minGb, int maxGb) {
        Objects.requireNonNull(minFee, "Мінімальна плата обов'язкова");
        Objects.requireNonNull(maxFee, "Максимальна плата обов'язкова");
        if (minFee.signum() < 0 || minFee.compareTo(maxFee) > 0) {
            throw new IllegalArgumentException("Некоректний діапазон плати");
        }
        if (minMinutes < 0 || minMinutes > maxMinutes) {
            throw new IllegalArgumentException("Некоректний діапазон хвилин");
        }
        if (minGb < 0 || minGb > maxGb) {
            throw new IllegalArgumentException("Некоректний діапазон ГБ");
        }
        this.minFee = minFee;
        this.maxFee = maxFee;
        this.minMinutes = minMinutes;
        this.maxMinutes = maxMinutes;
        this.minGb = minGb;
        this.maxGb = maxGb;
    }

    public boolean matches(Tariff tariff) {
        Objects.requireNonNull(tariff, "Тариф обов'язковий");
        return tariff.getMonthlyFee().compareTo(minFee) >= 0
                && tariff.getMonthlyFee().compareTo(maxFee) <= 0
                && tariff.getMinutes() >= minMinutes
                && tariff.getMinutes() <= maxMinutes
                && tariff.getInternetGb() >= minGb
                && tariff.getInternetGb() <= maxGb;
    }
}
