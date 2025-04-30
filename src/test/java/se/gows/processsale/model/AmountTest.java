package se.gows.processsale.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AmountTest {
    private Amount testAmount;
    private double testValue;
    @Test
    void testGetValue() {
        testValue = 100;
        testAmount = new Amount(testValue);
        boolean expectedResult = true;
        boolean result = testValue == testAmount.getValue();
        assertEquals(expectedResult, result, "Result should be equal");
    }
}
