package se.gows.processsale.integration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

//import se.gows.processsale.integration.DiscountDBHandler;
import se.gows.processsale.DTO.ItemDTO;
import se.gows.processsale.model.RegisteredItem;

public class DiscountDBHandlerTest {
    
    @Test
    void testGetDiscountedPriceAllDiscounts() {
        DiscountDBHandler discHandler = new DiscountDBHandler();
        
        // Item eligible for item-based discount
        ItemDTO item1 = new ItemDTO(1, "Test Item", 100.0, 0.25);
        RegisteredItem regItem1 = new RegisteredItem(item1, 1);
        
        // Item not eligible for item-based discount
        ItemDTO item2 = new ItemDTO(99, "Non-discounted Item", 150.0, 0.25);
        RegisteredItem regItem2 = new RegisteredItem(item2, 1);
        
        RegisteredItem[] purchasedItems = { regItem1, regItem2 };
        int[] discountTypes = {1, 2, 3}; 
        int customerID = 1; 
        double totalPrice = 250.0;

        double discountedPrice = discHandler.getDiscountedPrice(discountTypes, customerID, purchasedItems, totalPrice);
        assertEquals(175.95, discountedPrice, 0.01);
    }
}
