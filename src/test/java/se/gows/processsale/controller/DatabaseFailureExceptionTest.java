package se.gows.processsale.controller;

import org.junit.jupiter.api.Test;

import se.gows.processsale.integration.*;
import se.gows.processsale.utils.ObserversList;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class DatabaseFailureExceptionTest {

    private InventoryDBHandler invHandler;;
    private AccountingDBHandler accHandler;
    private DiscountDBHandler discHandler;
    private Controller testCtrl;
    private ObserversList obsList;

    @BeforeEach
    public void setup() {
        invHandler = new InventoryDBHandler();
        testCtrl = new Controller(invHandler, accHandler, discHandler, obsList);
        testCtrl.startSale();
    }

    @AfterEach
    public void tearDown() {
        invHandler = null;
        testCtrl = null;
    }

    @Test
    void testDatabaseFailureExceptionIsThrown() {
        int failureId = 404;

        DatabaseFailureException thrown = assertThrows(
            DatabaseFailureException.class,
            () -> testCtrl.scanItem(failureId, 1),
            "DatabaseFailureException not thrown as expected."
        );

        assertEquals("Unable to connect to database.", thrown.getMessage(), "Exception message not correctly set.");
        assertNotNull(thrown.getCause(), "Exception cause not correctly set.");
        assertEquals(DatabaseNotRunningException.class, thrown.getCause().getClass(), "Exception cause not correctly set.");
    }
}
