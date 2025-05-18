package se.gows.processsale.integration;

import org.junit.jupiter.api.Test;

import se.gows.processsale.DTO.ItemDTO;
import se.gows.processsale.controller.*;
import se.gows.processsale.utils.ObserversList;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class DatabaseFailureExceptionTest {

    private InventoryDBHandler invHandler;;
    private AccountingDBHandler accHandler;
    private DiscountDBHandler discHandler;
    private ObserversList obsList;
    private Controller testCtrl;

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
    void testScanItemThatShouldThrowException() throws ItemIdNotFoundException {
        int failureId = 404;

        try {
            invHandler.fetchItemFromInventory(failureId);
        } catch(DatabaseNotRunningException exc) {
            assertEquals(exc.getMessage(), "Error 404: Database not running.");
        }
    }

    @Test
    void testScanItemThatShouldNotThrowException()  {
        int existingId = 1;

        assertDoesNotThrow(() -> {
            ItemDTO dto = invHandler.fetchItemFromInventory(existingId);
            assertNotNull(dto, "DTO should not be null for valid item ID");
        });
    }
}
