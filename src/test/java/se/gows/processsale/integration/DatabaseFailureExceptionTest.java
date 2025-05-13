package se.gows.processsale.integration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseFailureExceptionTest {
    @Test
    void testConstructorSetsMessage() {
        DatabaseFailureException exc = new DatabaseFailureException();
        String expectedMessage = "Database can not be called.";

        assertEquals(expectedMessage, exc.getMessage());
    }
}
