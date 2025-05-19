package se.gows.processsale.integration;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.gows.processsale.DTO.ItemDTO;

public class DatabaseNotRunningExceptionTest {
    private InventoryDBHandler invHandler;

    @BeforeEach
    public void setup() {
        invHandler = new InventoryDBHandler();
    }

    @AfterEach
    void testScanItemThatShouldThrowException() throws ItemIdNotFoundException {
        int failureId = 404;

        DatabaseNotRunningException thrown = assertThrows(
            DatabaseNotRunningException.class,
            () -> invHandler.fetchItemFromInventory(failureId),
            "DatabaseNotRunningException not thrown as excpected."
        );

        assertEquals("Error 404: Database not running.", thrown.getMessage(), 
                "Errormessage not set as expected.");
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
