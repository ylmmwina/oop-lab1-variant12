package ua.edu.lab1.model;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TariffTest {
    @Test
    void storesFieldsAndUsesPolymorphism() {
        Tariff voice = new VoiceTariff(" Voice ", new BigDecimal("120"),
                10, 300);
        assertAll(() -> assertEquals("Voice", voice.getName()),
                () -> assertEquals(new BigDecimal("120.00"), voice.getMonthlyFee()),
                () -> assertEquals(10, voice.getClientCount()),
                () -> assertEquals(300, voice.getMinutes()),
                () -> assertEquals(0, voice.getInternetGb()),
                () -> assertEquals("Голосовий", voice.getType()));
        Tariff data = new DataTariff("Data", BigDecimal.ZERO, 0, 40);
        assertEquals("Інтернет", data.getType());
        assertEquals(0, data.getMinutes());
        assertEquals(40, data.getInternetGb());
        Tariff combined = new CombinedTariff("Both", BigDecimal.ONE,
                2, 100, 20);
        assertEquals("Комбінований", combined.getType());
        assertEquals(100, combined.getMinutes());
        assertEquals(20, combined.getInternetGb());
    }

    @Test
    void rejectsInvalidInitialization() {
        assertThrows(NullPointerException.class,
                () -> tariff(null, "1", 0, 0, 0));
        assertThrows(NullPointerException.class,
                () -> new VoiceTariff("x", null, 0, 0));
        assertThrows(IllegalArgumentException.class,
                () -> tariff(" ", "1", 0, 0, 0));
        assertThrows(IllegalArgumentException.class,
                () -> tariff("x", "-0.01", 0, 0, 0));
        assertThrows(IllegalArgumentException.class,
                () -> tariff("x", "1", -1, 0, 0));
        assertThrows(IllegalArgumentException.class,
                () -> tariff("x", "1", 0, -1, 0));
        assertThrows(IllegalArgumentException.class,
                () -> tariff("x", "1", 0, 0, -1));
        assertThrows(ArithmeticException.class,
                () -> tariff("x", "1.001", 0, 0, 0));
        assertEquals(new BigDecimal("0.00"),
                tariff("x", "0", 0, 0, 0).getMonthlyFee());
    }

    private Tariff tariff(String name, String fee, int clients,
                          int minutes, int gb) {
        return new CombinedTariff(name, new BigDecimal(fee),
                clients, minutes, gb);
    }
}
