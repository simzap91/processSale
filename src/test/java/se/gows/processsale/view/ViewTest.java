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

        instanceToTest.testRun();

        String printout = printoutBuffer.toString();
        String expectedStartString = "A new sale has been started.";
        String expectedScanString = "Item name:";
        String expectedEndString = "Sale ended";
        String expectedDiscountString = "Discount requested.";
        String expectedAfterDiscountString = "after discount:";
        
        assertTrue(printout.contains(expectedStartString), "Test run did not print sale start correctly.");
        assertTrue(printout.contains(expectedScanString), "Test run did not print scanned item correctly.");
        assertTrue(printout.contains(expectedEndString), "Test run did not print sale end correctly.");
        assertTrue(printout.contains(expectedDiscountString), "Test run did not print discount output correctly.");
        assertTrue(printout.contains(expectedAfterDiscountString), "Test run did not print after discount output correctly.");
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
