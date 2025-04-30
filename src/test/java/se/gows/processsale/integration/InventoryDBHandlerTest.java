package se.gows.processsale.integration;
import se.gows.processsale.integration.InventoryDBHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InventoryDBHandlerTest {
    private InventoryDBHandler testInvHandler;
    private double testID;
    @BeforeEach
    void setUp(){
        testInvHandler = new InventoryDBHandler();
        testID = 1;
    }
    @Test
    void testFetchItemFromDB() {
        
    }

    @Test
    void testUpdateInventoryDB() {

    }
}
