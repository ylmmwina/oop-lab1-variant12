package ua.edu.lab1.model;

import java.math.BigDecimal;

/** Тариф для дзвінків без пакета інтернету. */
public final class VoiceTariff extends Tariff {
    public VoiceTariff(String name, BigDecimal monthlyFee,
                        int clientCount, int minutes) {
        super(name, monthlyFee, clientCount, minutes, 0);
    }

    @Override
    public String getType() {
        return "Голосовий";
    }
}
