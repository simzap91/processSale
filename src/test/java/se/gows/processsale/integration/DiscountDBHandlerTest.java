package se.gows.processsale.integration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import se.gows.processsale.DTO.*;
import se.gows.processsale.model.Amount;;

public class DiscountDBHandlerTest {
    /*
     * Test for all discounts
     */
    @Test
    void testGetDiscountedPriceAllDiscounts() {
        DiscountDBHandler discHandler = new DiscountDBHandler();
        
        ItemDTO item1 = new ItemDTO(1, "Test Item", 100.0, 0.25);
        RegisteredItemDTO regItem1 = new RegisteredItemDTO(
            item1.getItemID(), item1.getItemDescription(), item1.getPrice(), item1.getVatRate(), 1);
        
        ItemDTO item2 = new ItemDTO(99, "Non-discounted Item", 150.0, 0.25);
        RegisteredItemDTO regItem2 = new RegisteredItemDTO(
            item2.getItemID(), item2.getItemDescription(), item2.getPrice(), item2.getVatRate(), 1);
        
        RegisteredItemDTO[] purchasedItems = { regItem1, regItem2 };
        int[] discountTypes = {1, 2, 3}; 
        int customerID = 1; 
        Amount totalPrice = new Amount(250.0);

        Amount discountedPrice = discHandler.getDiscountedPrice(discountTypes, customerID, purchasedItems, totalPrice);

        assertEquals(175.95, discountedPrice.getValue(), 0.01, "Discount is not calculated as expected");
    }

    /*
     * Test for no discounts
     */
    @Test
    void testGetDiscountedPriceNoDiscountApplied() {
        DiscountDBHandler discHandler = new DiscountDBHandler();

        ItemDTO item = new ItemDTO(99, "Non-discounted Item", 100.0, 0.25);
        RegisteredItemDTO regItem = new RegisteredItemDTO(
            item.getItemID(), item.getItemDescription(), item.getPrice(), item.getVatRate(), 1);

        RegisteredItemDTO[] purchasedItems = { regItem };
        int[] discountTypes = {}; 
        int customerID = 99; 
        Amount totalPrice = new Amount(100.0);

        Amount discountedPrice = discHandler.getDiscountedPrice(discountTypes, customerID, purchasedItems, totalPrice);

        assertEquals(100.0, discountedPrice.getValue(), 0.01, "Discount is not calculated as expected");
    }

    /*
     * Test for type one discount
     */
    @Test
    void testGetDiscountedPriceTypeOneDiscountApplied() {
        DiscountDBHandler discHandler = new DiscountDBHandler();

        ItemDTO item = new ItemDTO(1, "Discounted Item", 100.0, 0.25);
        RegisteredItemDTO regItem = new RegisteredItemDTO(
            item.getItemID(), item.getItemDescription(), item.getPrice(), item.getVatRate(), 1);

        RegisteredItemDTO[] purchasedItems = { regItem };
        int[] discountTypes = { 1 }; 
        int customerID = 99; 
        Amount totalPrice = new Amount(100.0);

        Amount discountedPrice = discHandler.getDiscountedPrice(discountTypes, customerID, purchasedItems, totalPrice);

        assertEquals(80.0, discountedPrice.getValue(), 0.01, "Discount for type one is not calculated as expected");
    }

    /*
     * Test for type two discount
     */
    @Test
    void testGetDiscountedPriceTypeTwoDiscountApplied() {
        DiscountDBHandler discHandler = new DiscountDBHandler();

        ItemDTO item = new ItemDTO(99, "Non-discounted Item", 300.0, 0.25);
        RegisteredItemDTO regItem = new RegisteredItemDTO(
            item.getItemID(), item.getItemDescription(), item.getPrice(), item.getVatRate(), 1);

        RegisteredItemDTO[] purchasedItems = { regItem };
        int[] discountTypes = { 2 }; 
        int customerID = 1; 
        Amount totalPrice = new Amount(300.0);

        Amount discountedPrice = discHandler.getDiscountedPrice(discountTypes, customerID, purchasedItems, totalPrice);

        assertEquals(270.0, discountedPrice.getValue(), 0.01, "Discount is not calculated as expected");
    }

    /*
     * Test for type three discount
     */
    @Test
    void testGetDiscountedPriceTypeThreeDiscountApplied() {
        DiscountDBHandler discHandler = new DiscountDBHandler();

        ItemDTO item = new ItemDTO(99, "Non-discounted Item", 100.0, 0.25);
        RegisteredItemDTO regItem = new RegisteredItemDTO(
            item.getItemID(), item.getItemDescription(), item.getPrice(), item.getVatRate(), 1);

        RegisteredItemDTO[] purchasedItems = { regItem };
        int[] discountTypes = {3}; 
        int customerID = 1; 
        Amount totalPrice = new Amount(100.0);

        Amount discountedPrice = discHandler.getDiscountedPrice(discountTypes, customerID, purchasedItems, totalPrice);

        assertEquals(85.0, discountedPrice.getValue(), 0.01, "Discount is not calculated as expected");
    }
}
