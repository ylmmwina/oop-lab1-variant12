package ua.edu.lab1.model;

import java.math.BigDecimal;

/** Тариф з пакетом хвилин та інтернету. */
public final class CombinedTariff extends Tariff {
    public CombinedTariff(String name, BigDecimal monthlyFee,
                        int clientCount, int minutes, int internetGb) {
        super(name, monthlyFee, clientCount, minutes, internetGb);
    }

    @Override
    public String getType() {
        return "Комбінований";
    }
}
