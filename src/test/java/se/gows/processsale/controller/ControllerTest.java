package se.gows.processsale.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import se.gows.processsale.DTO.ItemDTO;
import se.gows.processsale.DTO.ViewDTO;
import se.gows.processsale.integration.*;

public class ControllerTest {
    @Test
    void testCreatePrinter() {

    }

    @Test
    void testCreateReceipt() {

    }

    @Test
    void testEndSale() {

    }

    @Test
    void testRegisterPayment() {

    }

    @Test
    void testRequestDiscount() {

    }

    @Test
    void testScanItem() {
        InventoryDBHandler invHandler = new FakeInventoryDBHandler();
        AccountingDBHandler accHandler = new AccountingDBHandler();
        DiscountDBHandler discHandler = new DiscountDBHandler();

        Controller ctrl = new Controller(invHandler, accHandler, discHandler);

        ctrl.startSale();

        int itemID = 1001;
        int quantity = 2;

        ViewDTO result = ctrl.scanItem(itemID, quantity);

        assertNotNull(result, "ViewDTO should not be null");
        assertEquals("TestItem", result.regItem.item.itemDescription, "Item description should match");
        assertEquals(2, result.regItem.quantity, "Quantity should match input");




    }

    @Test
    void testStartSale() {
        InventoryDBHandler invHandler = new FakeInventoryDBHandler();
        AccountingDBHandler accHandler = new AccountingDBHandler();
        DiscountDBHandler discHandler = new DiscountDBHandler();

        Controller ctrl = new Controller(invHandler, accHandler, discHandler);
        assertDoesNotThrow(() -> ctrl.startSale());
    }

    class FakeInventoryDBHandler extends InventoryDBHandler {
    @Override
    public ItemDTO fetchItemFromDB(int itemID) {
        if (itemID == 1001) {
            return new ItemDTO(itemID, "TestItem", 1000, 200);
        }
        return null;
    }
}
}
