package se.gows.processsale.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.gows.processsale.DTO.RegisteredItemDTO;
import se.gows.processsale.DTO.SaleDTO;
import se.gows.processsale.utils.ObserversList;
import se.gows.processsale.utils.SumOfCostsObserver;

public class CashRegisterTest {
    private ArrayList<SumOfCostsObserver> sumOfCostsObservers;
    private CashRegister instanceToTest;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;
    private ObserversList obsList;

    @BeforeEach
    public void setUp(){
        instanceToTest = new CashRegister(obsList);

        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
    }

    @AfterEach
    public void tearDown() {
        instanceToTest = null;
        printoutBuffer = null;
        System.setOut(originalSysOut);
    }

    @Test
    void testRegsiterPaymentAndCreateReceipt(){
        Amount testPayment = new Amount(100);
        RegisteredItemDTO[] testItemList = {new RegisteredItemDTO(1, "Mjölk", 10, 0.1, 2),
                                            new RegisteredItemDTO(2, "Smör", 20, 0.1, 1)};
        Amount testTotalPrice = new Amount(40);
        Amount testTotalVat = new Amount(4);
        SaleDTO testSaleDTO = new SaleDTO(testTotalPrice, testTotalVat, testItemList);

        instanceToTest.registerPayment(testPayment, testSaleDTO);

        double expectedAmountPaid = 100;

        assertNotNull(instanceToTest.getReceipt(), "Receipt not created as expected.");
        assertEquals(instanceToTest.getReceipt().getAmountPaid().getValue(), expectedAmountPaid, "Registered amount paid not as expected.");
    }

    @Test
    void testPrintReceipt(){
        Amount testPayment = new Amount(100);
        RegisteredItemDTO[] testItemList = {new RegisteredItemDTO(1, "Mjölk", 10, 0.1, 2),
                                            new RegisteredItemDTO(2, "Smör", 20, 0.1, 1)};
        Amount testTotalPrice = new Amount(40);
        Amount testTotalVat = new Amount(4);
        SaleDTO testSaleDTO = new SaleDTO(testTotalPrice, testTotalVat, testItemList);

        instanceToTest.registerPayment(testPayment, testSaleDTO);

        String printout = printoutBuffer.toString();
        String expectedOutput = "Time of Sale";
        String expectedChange = "Amount change: " + (testPayment.getValue() - testSaleDTO.getSaleSums().getTotalIncVat().getValue());

        assertTrue(printout.contains(expectedOutput), "Receipt not printed as expected.");
        assertTrue(printout.contains(expectedChange), "Change not calculated as expected.");
    }
}
