package se.gows.processsale.integration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import se.gows.processsale.DTO.ItemDTO;
import se.gows.processsale.model.RegisteredItem;

public class DiscountDBHandlerTest {
    /*
     * Test for all discounts
     */
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

        assertEquals(175.95, discountedPrice, 0.01, "Discount is not calculated as expected");
    }

    /*
     * Test for no discounts
     */
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

        assertEquals(100.0, discountedPrice, 0.01, "Discount is not calculated as expected");
    }

    /*
     * Test for type one discount
     */
    @Test
    void testGetDiscountedPriceTypeOneDiscountApplied() {
        DiscountDBHandler discHandler = new DiscountDBHandler();

        ItemDTO item = new ItemDTO(1, "Discounted Item", 100.0, 0.25);
        RegisteredItem regItem = new RegisteredItem(item, 1);

        RegisteredItem[] purchasedItems = { regItem };
        int[] discountTypes = { 1 }; 
        int customerID = 99; 
        double totalPrice = 100.0;

        double discountedPrice = discHandler.getDiscountedPrice(discountTypes, customerID, purchasedItems, totalPrice);

        assertEquals(80.0, discountedPrice, 0.01, "Discount for type one is not calculated as expected");
    }

    /*
     * Test for type two discount
     */
    @Test
    void testGetDiscountedPriceTypeTwoDiscountApplied() {
        DiscountDBHandler discHandler = new DiscountDBHandler();

        ItemDTO item = new ItemDTO(99, "Non-discounted Item", 300.0, 0.25);
        RegisteredItem regItem = new RegisteredItem(item, 1);

        RegisteredItem[] purchasedItems = { regItem };
        int[] discountTypes = { 2 }; 
        int customerID = 1; 
        double totalPrice = 300.0;

        double discountedPrice = discHandler.getDiscountedPrice(discountTypes, customerID, purchasedItems, totalPrice);

        assertEquals(270.0, discountedPrice, 0.01, "Discount is not calculated as expected");
    }

    /*
     * Test for type three discount
     */
    @Test
    void testGetDiscountedPriceTypeThreeDiscountApplied() {
        DiscountDBHandler discHandler = new DiscountDBHandler();

        ItemDTO item = new ItemDTO(99, "Non-discounted Item", 100.0, 0.25);
        RegisteredItem regItem = new RegisteredItem(item, 1);

        RegisteredItem[] purchasedItems = { regItem };
        int[] discountTypes = {3}; 
        int customerID = 1; 
        double totalPrice = 100.0;

        double discountedPrice = discHandler.getDiscountedPrice(discountTypes, customerID, purchasedItems, totalPrice);

        assertEquals(85.0, discountedPrice, 0.01, "Discount is not calculated as expected");
    }
}
