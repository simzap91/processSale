package se.gows.processsale.integration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemIdNotFoundExceptionTest {

    @Test
    void testConstructorSetsMessageAndItemId() {
        int missingId = 123;
        ItemIdNotFoundException ex = new ItemIdNotFoundException(missingId);

        assertEquals(missingId, ex.getItemIdNotFound(),
            "getItemIdNotFound() do not return the ID passed to the constructor.");

        String expectedMessage = "Item with id " + missingId + " can't be found in inventory.\n";
        assertEquals(expectedMessage, ex.getMessage(),
            "getMessage() do not match the excpected message.");
    }
}
