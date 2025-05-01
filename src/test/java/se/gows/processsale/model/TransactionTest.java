package se.gows.processsale.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class TransactionTest {

    private Transaction instanceToTest;
    private Amount testPayment;

    @Test
    public void testCalculateChange() {
        testPayment = new Amount(200);
        instanceToTest = new Transaction(testPayment, 50);
        double expectedChange = 200 - 50;

        assertEquals(expectedChange, instanceToTest.amountChange.amount, "Change not correctly calculated.");
    }
}
