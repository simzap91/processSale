package se.gows.processsale.integration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import se.gows.processsale.DTO.ItemDTO;
import se.gows.processsale.model.RegisteredItem;

public class DiscountDBHandlerTest {
    
    @Test
    void testGetDiscountedPriceAllDiscounts() {
        DiscountDBHandler discHandler = new DiscountDBHandler();
        
        ItemDTO item1 = new ItemDTO(1, "Test Item", 100.0, 0.25);
        RegisteredItem regItem1 = new RegisteredItem(item1, 1);
        
        ItemDTO item2 = new ItemDTO(99, "Non-discounted Item", 150.0, 0.25);
        RegisteredItem regItem2 = new RegisteredItem(item2, 1);
        
        RegisteredItem[] purchasedItems = { regItem1, regItem2 };
        int[] discountTypes = {1, 2, 3}; 
        int customerID = 1; 
        double totalPrice = 250.0;

        double discountedPrice = discHandler.getDiscountedPrice(discountTypes, customerID, purchasedItems, totalPrice);

        // Asserts that the total discounted price is calculated correctly when all discounts are applied

        assertEquals(175.95, discountedPrice, 0.01, "Discount is not calculated as expected");
    }

    @Test
    void testGetDiscountedPriceNoDiscountApplied() {
        DiscountDBHandler discHandler = new DiscountDBHandler();

        ItemDTO item = new ItemDTO(99, "Non-discounted Item", 100.0, 0.25);
        RegisteredItem regItem = new RegisteredItem(item, 1);

        RegisteredItem[] purchasedItems = { regItem };
        int[] discountTypes = {}; 
        int customerID = 99; 
        double totalPrice = 100.0;

        double discountedPrice = discHandler.getDiscountedPrice(discountTypes, customerID, purchasedItems, totalPrice);

        // Asserts that the price does not change if there are no dicounts applied.
        assertEquals(100.0, discountedPrice, 0.01, "Discount is not calculated as expected");
    }
}
