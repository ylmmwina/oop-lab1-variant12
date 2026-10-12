package ua.edu.lab1.service;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ua.edu.lab1.model.CombinedTariff;
import static org.junit.jupiter.api.Assertions.*;

class TariffCriteriaTest {
    @ParameterizedTest
    @CsvSource({"200,100,10,true", "300,500,40,true",
            "250,300,20,true", "199.99,300,20,false",
            "300.01,300,20,false", "250,99,20,false",
            "250,501,20,false", "250,300,9,false",
            "250,300,41,false"})
    void checksEveryInclusiveBoundary(String fee, int minutes,
                                      int gb, boolean expected) {
        TariffCriteria criteria = criteria("200", "300", 100, 500, 10, 40);
        assertEquals(expected, criteria.matches(new CombinedTariff(
                "Test", new BigDecimal(fee), 1, minutes, gb)));
    }

    @Test
    void rejectsInvalidRangesAndNulls() {
        assertThrows(NullPointerException.class,
                () -> new TariffCriteria(null, BigDecimal.ONE, 0, 0, 0, 0));
        assertThrows(NullPointerException.class,
                () -> new TariffCriteria(BigDecimal.ZERO, null, 0, 0, 0, 0));
        assertThrows(IllegalArgumentException.class,
                () -> criteria("-1", "2", 0, 0, 0, 0));
        assertThrows(IllegalArgumentException.class,
                () -> criteria("3", "2", 0, 0, 0, 0));
        assertThrows(IllegalArgumentException.class,
                () -> criteria("0", "2", -1, 0, 0, 0));
        assertThrows(IllegalArgumentException.class,
                () -> criteria("0", "2", 1, 0, 0, 0));
        assertThrows(IllegalArgumentException.class,
                () -> criteria("0", "2", 0, 0, -1, 0));
        assertThrows(IllegalArgumentException.class,
                () -> criteria("0", "2", 0, 0, 1, 0));
        assertThrows(NullPointerException.class,
                () -> criteria("0", "0", 0, 0, 0, 0).matches(null));
        assertTrue(criteria("0", "0", 0, 0, 0, 0).matches(
                new CombinedTariff("Zero", BigDecimal.ZERO, 0, 0, 0)));
    }

    private TariffCriteria criteria(String min, String max,
                                    int minM, int maxM, int minG, int maxG) {
        return new TariffCriteria(new BigDecimal(min), new BigDecimal(max),
                minM, maxM, minG, maxG);
    }
}
