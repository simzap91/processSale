package se.gows.processsale.integration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;

import se.gows.processsale.controller.*;
import se.gows.processsale.utils.ObserversList;
import se.gows.processsale.DTO.*;

import org.junit.jupiter.api.BeforeEach;

public class ItemIdNotFoundExceptionTest {

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
    void testScanItemThatDoesNotExist() throws DatabaseFailureException, DatabaseNotRunningException {
        int missingId = 90;

        try {
            invHandler.fetchItemFromInventory(missingId);
        } catch(ItemIdNotFoundException exc) {
            assertEquals(exc.getItemIdNotFound(), missingId);
            assertEquals(exc.getMessage(), "Item with id " + missingId + " can not be found in inventory.");
        }
    }

    @Test
    void testScanItemThatShouldNotThrowException() {
        int existingId = 1;

        assertDoesNotThrow(() -> {
            ItemDTO dto = invHandler.fetchItemFromInventory(existingId);
            assertNotNull(dto, "DTO should not be null for valid item ID");
        });
    }
}
