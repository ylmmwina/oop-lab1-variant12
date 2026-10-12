package ua.edu.lab1.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import ua.edu.lab1.model.Tariff;
import ua.edu.lab1.model.VoiceTariff;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MobileCompanyTest {
    @Test
    void sumsClientsUsingMockitoAndLongArithmetic() {
        Tariff first = mock(Tariff.class);
        Tariff second = mock(Tariff.class);
        when(first.getClientCount()).thenReturn(Integer.MAX_VALUE);
        when(second.getClientCount()).thenReturn(10);
        MobileCompany company = new MobileCompany(List.of(first, second));
        assertEquals(2147483657L, company.getTotalClients());
        verify(first).getClientCount();
        verify(second).getClientCount();
    }

    @Test
    void sortsNumericallyAndPreservesEqualFeeOrder() {
        Tariff high = tariff("High", "100", 3);
        Tariff low = tariff("Low", "9", 4);
        Tariff equal = tariff("Equal", "9.00", 5);
        MobileCompany company = new MobileCompany(List.of(high, low, equal));
        company.sortByMonthlyFee();
        assertEquals(List.of(low, equal, high), company.getTariffs());
        assertEquals(12, company.getTotalClients());
        company.sortByMonthlyFee();
        assertEquals(List.of(low, equal, high), company.getTariffs());
    }

    @Test
    void protectsCollectionAndFindsAllMatchesWithoutMutation() {
        Tariff a = tariff("A", "10", 1);
        Tariff b = tariff("B", "20", 2);
        Tariff c = tariff("C", "30", 3);
        List<Tariff> input = new ArrayList<>(List.of(c, a, b));
        MobileCompany company = new MobileCompany(input);
        input.clear();
        List<Tariff> snapshot = company.getTariffs();
        assertThrows(UnsupportedOperationException.class, snapshot::clear);
        List<Tariff> result = company.findTariffs(range("10", "20"));
        assertEquals(List.of(a, b), result);
        assertThrows(UnsupportedOperationException.class, result::clear);
        assertEquals(List.of(c, a, b), company.getTariffs());
        assertEquals(List.of(b), company.findTariffs(range("20", "20")));
        assertTrue(company.findTariffs(range("40", "50")).isEmpty());
        company.sortByMonthlyFee();
        assertEquals(List.of(c, a, b), snapshot);
    }

    @Test
    void handlesEmptyCompanyAndRejectsNulls() {
        MobileCompany company = new MobileCompany(List.of());
        assertEquals(0, company.getTotalClients());
        company.sortByMonthlyFee();
        assertTrue(company.getTariffs().isEmpty());
        assertTrue(company.findTariffs(range("0", "1")).isEmpty());
        assertThrows(NullPointerException.class,
                () -> new MobileCompany(null));
        assertThrows(NullPointerException.class,
                () -> new MobileCompany(Arrays.asList((Tariff) null)));
        assertThrows(NullPointerException.class,
                () -> company.findTariffs(null));
    }

    private Tariff tariff(String name, String fee, int clients) {
        return new VoiceTariff(name, new BigDecimal(fee), clients, 100);
    }

    private TariffCriteria range(String min, String max) {
        return new TariffCriteria(new BigDecimal(min), new BigDecimal(max),
                0, 1000, 0, 100);
    }
}
