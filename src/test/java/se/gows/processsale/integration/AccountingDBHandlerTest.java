package se.gows.processsale.integration;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import se.gows.processsale.DTO.SaleDTO;
import se.gows.processsale.model.*;

public class AccountingDBHandlerTest {
    private AccountingDBHandler accDBtest;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;

    @BeforeEach
    public void setUp(){
        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
    }

    @AfterEach
    public void tearDown(){
        accDBtest = null;
        printoutBuffer = null;
        System.setOut(originalSysOut);
    }

    @Disabled
    @Test
    void testUpdateAccountBalance() {

        Amount testAmountPaid = new Amount(100);   
        Amount testTotalPrice = new Amount(80);
        Amount testTotalVat = new Amount(20);
        SaleDTO testSaleDTO  = new SaleDTO(testTotalPrice, testTotalVat, null, null);
        Transaction testTransaction = new Transaction(testAmountPaid, new Amount(100));
        Receipt testReceipt = new Receipt(testSaleDTO, testTransaction);
        accDBtest = new AccountingDBHandler();

        accDBtest.updateAccountBalance(testReceipt);
        String printout = printoutBuffer.toString();
        String expectedOutput = "New balance: 200";
        assertTrue(printout.contains(expectedOutput), "Wrong value is updated to DB");
        
    }
}
