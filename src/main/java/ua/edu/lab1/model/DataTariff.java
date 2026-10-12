package ua.edu.lab1.model;

import java.math.BigDecimal;

/** Тариф для передачі даних без пакета хвилин. */
public final class DataTariff extends Tariff {
    public DataTariff(String name, BigDecimal monthlyFee,
                        int clientCount, int internetGb) {
        super(name, monthlyFee, clientCount, 0, internetGb);
    }

    @Override
    public String getType() {
        return "Інтернет";
    }
}
