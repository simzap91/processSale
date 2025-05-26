package se.gows.processsale.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;



import se.gows.processsale.utils.*;
import se.gows.processsale.DTO.RegisteredItemDTO;
import se.gows.processsale.DTO.SaleDTO;

public class PrinterTest {
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
    public void tearDown() {
        printoutBuffer = null;
        System.setOut(originalSysOut);
    }
    @Test
    public void testPrinter(){
        Amount amountPaidTest = new Amount(100.00);
        Amount totalPriceTest = new Amount(44.7); 
        Amount totalVatTest = new Amount(11.175); 
        Amount totalPriceIncVatTest = new Amount(totalPriceTest.getValue() + totalVatTest.getValue());
        Transaction transactionTest = new Transaction(amountPaidTest,totalPriceIncVatTest);
        RegisteredItemDTO[] itemListTest = {new RegisteredItemDTO(1, "Mjölk", 14.90, 0.25, 3)};
        SaleDTO saleDTOTest = new SaleDTO(totalPriceTest, totalVatTest, itemListTest);
        Receipt receiptTest = new Receipt(saleDTOTest,transactionTest);
        Printer printerTest = new Printer();
        printerTest.printReceipt(receiptTest);
        String printout = printoutBuffer.toString();
        
        String expectedOutputItemList = "3st Mjölk á 14.9kr -> 44.7kr";
        assertTrue(printout.contains(expectedOutputItemList), "Wrong itemList is printed to console");

        String expectedOutputTotalPrice = "Total price: 44.70kr";
        assertTrue(printout.contains(expectedOutputTotalPrice), "Wrong total price is printed to console");

        String expectedOutputTotalVat = "Total VAT: 11.175kr";
        assertTrue(printout.contains(expectedOutputTotalVat), "Wrong total VAT is printed to console");

        String expectedOutputTotalIncVat = "Total (incl. VAT): 55.88 kr";
        assertTrue(printout.contains(expectedOutputTotalIncVat), "Wrong total price including VAT is printed to console");

        String expectedOutputAmountPaid = "Amount paid: 100.00 kr";
        assertTrue(printout.contains(expectedOutputAmountPaid), "Wrong amount paid is printed to console");

        String expectedOutputAmountChange = "Amount change: 44.13 kr";
        assertTrue(printout.contains(expectedOutputAmountChange), "Wrong amount change is printed to console");


    }
}
