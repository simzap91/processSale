package se.gows.processsale.view;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.gows.processsale.controller.Controller;
import se.gows.processsale.integration.AccountingDBHandler;
import se.gows.processsale.integration.DiscountDBHandler;
import se.gows.processsale.integration.InventoryDBHandler;
import se.gows.processsale.utils.ObserversList;

public class ViewTest {
    private View instanceToTest;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;
    private Controller ctrl;
    private InventoryDBHandler invHandler;;
    private AccountingDBHandler accHandler;
    private DiscountDBHandler discHandler;
    private ObserversList obsList;

    @BeforeEach
    public void setUp(){
        invHandler = new InventoryDBHandler();
        accHandler = new AccountingDBHandler();
        discHandler = new DiscountDBHandler();
        
        obsList = new ObserversList();
        ctrl = new Controller(invHandler, accHandler, discHandler, obsList);

        instanceToTest = new View(ctrl);
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
        ctrl = null;

        instanceToTest = null;
        printoutBuffer = null;
        System.setOut(originalSysOut);
    }

    @Test
    void testTestRun() {

        int itemQuantityInCart = 1;
        int itemIdInCart = 1;
        String itemNameInCart = "Mjölk";
        double itemCostInCart = 14.9;
        int itemVatInCart = 25;
        double runningTotInFirstSale = 37.25;
        double totAfterFirstSale = 37.25;
        int customerIdInQueue = 1;
        String requestetDiscType = "ITEM";
        double totAfterDiscountSaleOne = 27.71;

        String expectedStartString = "A new sale has been started.";
        String expectedAddString = "Add " + itemQuantityInCart;
        String expectedItemIdString = "Item ID: " + itemIdInCart;
        String expectedItemNameString = "Item name: " + itemNameInCart;
        String expectedItemCostString = "Item cost: " + itemCostInCart;
        String expectedVatString = "VAT: " + itemVatInCart;
        String expectedEndString = "Sale ended";
        String expectedRunTotString = "Running total (inc. VAT): " + runningTotInFirstSale;
        String expectedTotIncVatString = "Total (inc. VAT): " + totAfterFirstSale;
        String expectedDiscountString = "Discount requested.";
        String expectedCustIdString = "Customer ID: " + customerIdInQueue;
        String expectedDiscTypesString = "Discount types:";
        String expectedDiscTypeString = requestetDiscType;
        String expectedAfterDiscountString = "after discount: " + totAfterDiscountSaleOne;

        instanceToTest.testRun();
        String printout = printoutBuffer.toString();
        
        assertTrue(printout.contains(expectedStartString), "Test run did not print sale start correctly.");
        assertTrue(printout.contains(expectedAddString), "Test run did not print add item correctly.");
        assertTrue(printout.contains(expectedItemIdString), "Test run did not print item id correctly.");
        assertTrue(printout.contains(expectedItemNameString), "Test run did not print scanned item name correctly.");
        assertTrue(printout.contains(expectedItemCostString), "Test run did not print item cost correctly.");
        assertTrue(printout.contains(expectedVatString), "Test run did not print sale vat correctly.");
        assertTrue(printout.contains(expectedRunTotString), "Test run did not print running total correctly.");
        assertTrue(printout.contains(expectedEndString), "Test run did not print sale end correctly.");
        assertTrue(printout.contains(expectedTotIncVatString), "Test run did not print TotIncVat correctly.");
        assertTrue(printout.contains(expectedDiscountString), "Test run did not print discount output correctly.");
        assertTrue(printout.contains(expectedCustIdString), "Test run did not print customer id correctly.");
        assertTrue(printout.contains(expectedAfterDiscountString), "Test run did not print after discount output correctly.");
        assertTrue(printout.contains(expectedDiscTypesString), "Test run did not print discount types correctly.");
        assertTrue(printout.contains(expectedDiscTypeString), "Test run did not print discount type correctly.");   
    }

    @Test
    void testExceptionsInTestRun() {

        instanceToTest.testRun();

        String printout = printoutBuffer.toString();
        String expectedStringItemException = "Invalid item id.";
        String expectedStringDatabaseException = "Not able to connect to database.";

        assertTrue(printout.contains(expectedStringItemException), "Test run did not print item error message correctly.");
        assertTrue(printout.contains(expectedStringDatabaseException), "Test run did not print database error message correctly.");
    }
}
