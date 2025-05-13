package se.gows.processsale.controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import se.gows.processsale.DTO.*;
import se.gows.processsale.integration.*;
import se.gows.processsale.model.*;

public class ControllerTest {

    private Controller instanceToTest;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;
    private InventoryDBHandler invHandler;;
    private AccountingDBHandler accHandler;
    private DiscountDBHandler discHandler;

    @BeforeEach
    public void setUp(){
        invHandler = new InventoryDBHandler();
        accHandler = new AccountingDBHandler();
        discHandler = new DiscountDBHandler();

        instanceToTest = new Controller(invHandler, accHandler, discHandler);
        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
    }

    @AfterEach
    public void tearDown() {
        invHandler = null;
        accHandler = null;
        discHandler = null;

        instanceToTest = null;
        printoutBuffer = null;
        System.setOut(originalSysOut);
    }

    @Test
    void testScanItem() throws ItemIdNotFoundException, DatabaseFailureException {
        instanceToTest.startSale();
        ViewDTO resultItemNotRegistered = instanceToTest.scanItem(2, 2);

        assertNotNull(resultItemNotRegistered, "ViewDTO not created as expected");
        assertEquals("Smör", resultItemNotRegistered.getRegItem().getItemDescription(), "Item description not as expected.");
        assertEquals(2, resultItemNotRegistered.getRegItem().getQuantity(), "Not correct item quantity.");

        ViewDTO resultItemAlreadyRegistered = instanceToTest.scanItem(2, 2);
        assertEquals(4, resultItemAlreadyRegistered.getRegItem().getQuantity(), "Item quantity not updated correctly.");
    }

    @Test
    void testEndSale() throws ItemIdNotFoundException, DatabaseFailureException {
        instanceToTest.startSale();
        instanceToTest.scanItem(2, 2);
        SaleDTO result = instanceToTest.endSale();

        assertNotNull(result, "SaleDTO not created as expected.");
        assertTrue(result.getSaleSums().getTotalPrice() > 0, "Total price not greater than zero as expected.");
        assertEquals(1, result.getItemList().length, "Number of items in items list not as expected.");
    }

    @Test
    void testRequestDiscount() throws ItemIdNotFoundException, DatabaseFailureException {
        instanceToTest.startSale();
        instanceToTest.scanItem(2, 2);

        SaleDTO saleBeforeDiscount = instanceToTest.endSale();
        double originalPrice = saleBeforeDiscount.getSaleSums().getTotalPrice();

        int[] discountTypes = {1, 2, 3};
        int customerID = 1;

        SaleDTO saleAfterDiscount = instanceToTest.requestDiscount(customerID, saleBeforeDiscount, discountTypes);

        assertNotNull(saleAfterDiscount, "Discounted SaleDTO not created as expected.");
        assertTrue(saleAfterDiscount.getSaleSums().getTotalPrice() < originalPrice, "Total price not reduced after discount.");
    }

    @Test
    void testRegisterPaymentAndPrintReceipt() throws ItemIdNotFoundException, DatabaseFailureException {
        instanceToTest.startSale();
        instanceToTest.scanItem(2, 2);
        SaleDTO testSaleDTO = instanceToTest.endSale();

        Amount testPayment = new Amount(200);

        instanceToTest.registerPayment(testPayment);
        instanceToTest.printReceipt();
        String printout = printoutBuffer.toString();
        String expectedOutput = "Time of Sale";
        String expectedChange = "Amount change: " + (testPayment.getValue() - testSaleDTO.getSaleSums().getTotalIncVat());

        assertTrue(printout.contains(expectedOutput), "Receipt not printed as expected.");
        assertTrue(printout.contains(expectedChange), "Change not calculated as expected.");
    }
}