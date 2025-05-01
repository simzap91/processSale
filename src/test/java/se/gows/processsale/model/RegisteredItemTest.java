package se.gows.processsale.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.*;
import se.gows.processsale.DTO.ItemDTO;

public class RegisteredItemTest {

    private RegisteredItem instanceToTest;

    @Test
    void testIdsAreEqual() {
        ItemDTO testItem = new ItemDTO(1001, "TestItem", 1000, 0.2);
        instanceToTest = new RegisteredItem(testItem, 2);
        assertTrue(instanceToTest.idsAreEqual(1001), "Operation is not true as expected.");
    }
}
