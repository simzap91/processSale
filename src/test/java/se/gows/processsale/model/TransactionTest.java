package se.gows.processsale.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class TransactionTest {

    private Transaction instanceToTest;
    private Amount testPayment;
    private Amount testTotalIncVat;

    @Test
    public void testCalculateChange() {
        testPayment = new Amount(200);
        testTotalIncVat = new Amount(50);
        instanceToTest = new Transaction(testPayment, testTotalIncVat);
        double expectedChange = 200 - 50;

        assertEquals(expectedChange, instanceToTest.getAmountChange().getValue(), "Change not correctly calculated.");
    }
}
