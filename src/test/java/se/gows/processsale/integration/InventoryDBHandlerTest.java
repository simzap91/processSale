package se.gows.processsale.integration;
import se.gows.processsale.DTO.ItemDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InventoryDBHandlerTest {
    private InventoryDBHandler testInvHandler;
    private int testID;
    private ItemDTO testItemDTO;
    
    @BeforeEach
    void setUp(){
        
        testInvHandler = new InventoryDBHandler();
        testItemDTO = new ItemDTO(1, "Mjölk", 14.90, 0.25);
    }
    
    @AfterEach
    void tearDown(){
        
        testInvHandler = null;
        testItemDTO = null;
    }
    
    @Test
    void testFetchItemFromDB() throws ItemIdNotFoundException, DatabaseNotRunningException {
        testID = 1;
        boolean expectedResult = true;
        ItemDTO testItem = testInvHandler.fetchItemFromInventory(testID);
        boolean resultPrice = testItemDTO.getPrice() == testItem.getPrice();
        boolean resultVAT = testItemDTO.getVatRate() == testItem.getVatRate();
        boolean resultDesc = testItem.getItemDescription().equals(testItemDTO.getItemDescription());
        boolean result = resultPrice == resultVAT == resultDesc;
        assertEquals(expectedResult, result, "Wrong item fetched from DB");
    }

    @Test
    void testFetchUnknownItemFromDB() throws ItemIdNotFoundException, DatabaseNotRunningException {
        testID = -99;
        assertThrows(ItemIdNotFoundException.class, () -> {
            testInvHandler.fetchItemFromInventory(testID);
        }, "Do not throw ItemIdNotFoundException for unknown ID");
    }
}
