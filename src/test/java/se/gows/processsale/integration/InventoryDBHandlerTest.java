package se.gows.processsale.integration;
import se.gows.processsale.DTO.ItemDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    @Test
    void testFetchItemFromDB() {
        testID = 1;
        boolean expectedResult = true;
        //test to see if fetched item equals the expected item
        ItemDTO testItem = testInvHandler.fetchItemFromDB(testID);
        boolean resultPrice = testItemDTO.price==testItem.price;
        boolean resultVAT = testItemDTO.vatRate==testItem.vatRate;
        boolean resultDesc = testItem.itemDescription.equals(testItemDTO.itemDescription);
        boolean result = resultPrice == resultVAT == resultDesc;
        assertEquals(expectedResult, result, "Wrong item fetched from DB");
    }

    @Test
    void testUpdateInventoryDB() {

    }
}
