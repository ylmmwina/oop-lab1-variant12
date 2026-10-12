package ua.edu.lab1.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/** Спільні характеристики незмінного тарифу за календарний місяць. */
public abstract class Tariff {
    private final String name;
    private final BigDecimal monthlyFee;
    private final int clientCount;
    private final int minutes;
    private final int internetGb;

    protected Tariff(String name, BigDecimal monthlyFee, int clientCount,
                     int minutes, int internetGb) {
        Objects.requireNonNull(name, "Назва обов'язкова");
        Objects.requireNonNull(monthlyFee, "Плата обов'язкова");
        if (name.isBlank()) {
            throw new IllegalArgumentException("Назва не може бути порожньою");
        }
        if (monthlyFee.signum() < 0) {
            throw new IllegalArgumentException("Плата не може бути від'ємною");
        }
        if (clientCount < 0 || minutes < 0 || internetGb < 0) {
            throw new IllegalArgumentException("Параметри мають бути >= 0");
        }
        this.name = name.strip();
        this.monthlyFee = monthlyFee.setScale(2, RoundingMode.UNNECESSARY);
        this.clientCount = clientCount;
        this.minutes = minutes;
        this.internetGb = internetGb;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getMonthlyFee() {
        return monthlyFee;
    }

    public int getClientCount() {
        return clientCount;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getInternetGb() {
        return internetGb;
    }

    public abstract String getType();
}
