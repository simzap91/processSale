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

    @Test
    void testUpdateAccountBalance() {

        Amount testAmountPaid = new Amount(100);   
        SaleDTO testSaleDTO  = new SaleDTO(null, 80, 20, null);
        Transaction testTransaction = new Transaction(testAmountPaid, 100);
        Receipt testReceipt = new Receipt(testSaleDTO, testTransaction);
        accDBtest = new AccountingDBHandler();

        accDBtest.updateAccountBalance(testReceipt);
        String printout = printoutBuffer.toString();
        String expectedOutput = "New balance: 200";
        assertTrue(printout.contains(expectedOutput), "Wrong value is updated to DB");
        
    }
}
