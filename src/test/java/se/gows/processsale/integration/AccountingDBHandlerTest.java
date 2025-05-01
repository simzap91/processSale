package se.gows.processsale.integration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

@BeforeEach
public void setUp(){
    testAmountPaid = new Amount(100);
    //double test
    testSaleDTO  = new SaleDTO(null, 80, 20, null);
    testTransaction = new Transaction(testAmountPaid, 100);
    testReceipt = new Receipt(testSaleDTO, testTransaction);
    accDBtest = new AccountingDBHandler();
}
@AfterEach
public void tearDown(){
    testAmountPaid = null;
    testSaleDTO = null;
    testTransaction = null;
    testReceipt = null;
    accDBtest = null;
}
    @Test
    void testUpdateAccountBalance(Receipt testReceipt) {
        boolean expected = true;
        
    }
}
