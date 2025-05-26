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
    private Amount amountPaidTest;
    private Amount totalPriceTest;
    private Amount totalVatTest;
    private Amount totalPriceIncVatTest;
    private Transaction transactionTest;
    private RegisteredItemDTO[] itemListTest;
    private SaleDTO saleDTOTest;
    private Receipt receiptTest;
    private Printer printerTest;

    

     @BeforeEach
    public void setUp(){
        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);

        amountPaidTest = new Amount(100.00);
        totalPriceTest = new Amount(44.7); 
        totalVatTest = new Amount(11.175); 
        totalPriceIncVatTest = new Amount(totalPriceTest.getValue() + totalVatTest.getValue());
        transactionTest = new Transaction(amountPaidTest,totalPriceIncVatTest);
    }
    @AfterEach
    public void tearDown() {
        printoutBuffer = null;
        System.setOut(originalSysOut);
        amountPaidTest = null;
        totalPriceTest = null;
        totalVatTest = null;
        totalPriceIncVatTest = null;
        transactionTest = null;
        itemListTest = null;
        saleDTOTest = null;
        receiptTest = null;
        printerTest = null;
    }
    @Test
    public void testPrinter(){
        
        RegisteredItemDTO[] itemListTest = {new RegisteredItemDTO(1, "Mjölk", 14.90, 0.25, 3)};
        saleDTOTest = new SaleDTO(totalPriceTest, totalVatTest, itemListTest);
        receiptTest = new Receipt(saleDTOTest,transactionTest);

        printerTest = new Printer();
        printerTest.printReceipt(receiptTest);
        String printout = printoutBuffer.toString();
        
        String expectedOutputItemList = "3st Mjölk á 14.9kr -> 44.7kr";
        assertTrue(printout.contains(expectedOutputItemList), "Wrong item list is printed to console");

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
