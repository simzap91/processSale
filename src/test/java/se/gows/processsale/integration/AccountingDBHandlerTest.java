package se.gows.processsale.integration;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import se.gows.processsale.DTO.ItemDTO;
import se.gows.processsale.DTO.SaleDTO;
import se.gows.processsale.model.Amount;
import se.gows.processsale.model.Receipt;
import se.gows.processsale.model.Transaction;

public class AccountingDBHandlerTest {
    private Amount testAmountPaid;
    private SaleDTO testSaleDTO;
    private Transaction testTransaction;
    private Receipt testReceipt;
    private AccountingDBHandler accDBtest;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;
    private ItemDTO testItemDTO;

@BeforeEach
public void setUp(){
    testAmountPaid = new Amount(100);   
    testSaleDTO  = new SaleDTO(null, 80, 20, null);
    testTransaction = new Transaction(testAmountPaid, 100);
    testReceipt = new Receipt(testSaleDTO, testTransaction);
    accDBtest = new AccountingDBHandler();
    printoutBuffer = new ByteArrayOutputStream();
    PrintStream inMemSysOut = new PrintStream(printoutBuffer);
    originalSysOut = System.out;
    System.setOut(inMemSysOut);
    
}
@AfterEach
public void tearDown(){
    testAmountPaid = null;
    testSaleDTO = null;
    testTransaction = null;
    testReceipt = null;
    accDBtest = null;
    printoutBuffer = null;
    System.setOut(originalSysOut);
}

    @Test
    void testUpdateAccountBalance(Receipt testReceipt) {
        accDBtest.updateAccountBalance(testReceipt);
        String printout = printoutBuffer.toString();
        String expectedOutput = "New balance: 200";
        assertTrue(printout.contains(expectedOutput), "Wrong value is updated to DB");
        
    }
}
