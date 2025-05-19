package se.gows.processsale.integration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import se.gows.processsale.DTO.*;
import se.gows.processsale.utils.Amount;
import se.gows.processsale.utils.DiscountTypes;

public class DiscountDBHandlerTest {
    
    @Test
    void testAllDiscountsApplied() {
        DiscountDBHandler discHandler = new DiscountDBHandler();
        
        ItemDTO item1 = new ItemDTO(1, "Test Item", 100.0, 0.25);
        RegisteredItemDTO regItem1 = new RegisteredItemDTO(
            item1.getItemID(), item1.getItemDescription(), item1.getPrice(), item1.getVatRate(), 1);
        
        ItemDTO item2 = new ItemDTO(99, "Non-discounted Item", 150.0, 0.25);
        RegisteredItemDTO regItem2 = new RegisteredItemDTO(
            item2.getItemID(), item2.getItemDescription(), item2.getPrice(), item2.getVatRate(), 1);
        
        RegisteredItemDTO[] purchasedItems = { regItem1, regItem2 };
        DiscountTypes[] requestedDiscounts = {DiscountTypes.ITEMS, DiscountTypes.SALE, DiscountTypes.CUSTOMER};
        int customerId = 1;
        Amount totalPrice = new Amount(250.0);
        DiscountRequestDTO testDiscRequest = new DiscountRequestDTO(customerId, purchasedItems, totalPrice);

        Amount discountedPrice = discHandler.getDiscountedPrice(requestedDiscounts, testDiscRequest);

        assertEquals(175.95, discountedPrice.getValue(), 0.01, "Discount is not calculated as expected");
    }

    @Test
    void testNoDiscountsApplied() {
        DiscountDBHandler discHandler = new DiscountDBHandler();

        ItemDTO item = new ItemDTO(99, "Non-discounted Item", 100.0, 0.25);
        RegisteredItemDTO regItem = new RegisteredItemDTO(
            item.getItemID(), item.getItemDescription(), item.getPrice(), item.getVatRate(), 1);

        RegisteredItemDTO[] purchasedItems = { regItem };
        DiscountTypes[] requestedDiscounts = {}; 
        int customerId = 99;
        Amount totalPrice = new Amount(100.0);
        DiscountRequestDTO testDiscRequest = new DiscountRequestDTO(customerId, purchasedItems, totalPrice);

        Amount discountedPrice = discHandler.getDiscountedPrice(requestedDiscounts, testDiscRequest);

        assertEquals(100.0, discountedPrice.getValue(), 0.01, "Discount is not calculated as expected");
    }

    @Test
    void testItemsDiscountApplied() {
        DiscountDBHandler discHandler = new DiscountDBHandler();

        ItemDTO item = new ItemDTO(1, "Discounted Item", 100.0, 0.25);
        RegisteredItemDTO regItem = new RegisteredItemDTO(
            item.getItemID(), item.getItemDescription(), item.getPrice(), item.getVatRate(), 1);

        RegisteredItemDTO[] purchasedItems = { regItem };
        DiscountTypes[] requestedDiscounts = {DiscountTypes.ITEMS};
        int customerId = 99;
        Amount totalPrice = new Amount(100.0);
        DiscountRequestDTO testDiscRequest = new DiscountRequestDTO(customerId, purchasedItems, totalPrice);

        Amount discountedPrice = discHandler.getDiscountedPrice(requestedDiscounts, testDiscRequest);

        assertEquals(80.0, discountedPrice.getValue(), 0.01, "Discount for type one is not calculated as expected");
    }

    @Test
    void testSaleDiscountApplied() {
        DiscountDBHandler discHandler = new DiscountDBHandler();

        ItemDTO item = new ItemDTO(99, "Non-discounted Item", 300.0, 0.25);
        RegisteredItemDTO regItem = new RegisteredItemDTO(
            item.getItemID(), item.getItemDescription(), item.getPrice(), item.getVatRate(), 1);

        RegisteredItemDTO[] purchasedItems = { regItem };
        DiscountTypes[] requestedDiscounts = {DiscountTypes.SALE}; 
        int customerId = 1;
        Amount totalPrice = new Amount(300.0);
        DiscountRequestDTO testDiscRequest = new DiscountRequestDTO(customerId, purchasedItems, totalPrice);

        Amount discountedPrice = discHandler.getDiscountedPrice(requestedDiscounts, testDiscRequest);

        assertEquals(270.0, discountedPrice.getValue(), 0.01, "Discount is not calculated as expected");
    }

    @Test
    void testCustomerDiscountApplied() {
        DiscountDBHandler discHandler = new DiscountDBHandler();

        ItemDTO item = new ItemDTO(99, "Non-discounted Item", 100.0, 0.25);
        RegisteredItemDTO regItem = new RegisteredItemDTO(
            item.getItemID(), item.getItemDescription(), item.getPrice(), item.getVatRate(), 1);

        RegisteredItemDTO[] purchasedItems = { regItem };
        DiscountTypes[] requestedDiscounts = {DiscountTypes.CUSTOMER}; 
        int customerId = 1;
        Amount totalPrice = new Amount(100.0);
        DiscountRequestDTO testDiscRequest = new DiscountRequestDTO(customerId, purchasedItems, totalPrice);

        Amount discountedPrice = discHandler.getDiscountedPrice(requestedDiscounts, testDiscRequest);

        assertEquals(85.0, discountedPrice.getValue(), 0.01, "Discount is not calculated as expected");
    }
}
