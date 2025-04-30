package se.gows.processsale.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.gows.processsale.DTO.DiscountDTO;
import se.gows.processsale.DTO.ItemDTO;
import se.gows.processsale.DTO.SaleDTO;
import se.gows.processsale.DTO.ViewDTO;
import se.gows.processsale.integration.*;
import se.gows.processsale.model.Amount;
import se.gows.processsale.model.Receipt;
import se.gows.processsale.model.RegisteredItem;

public class ControllerTest {

    private Controller ctrl;
    private InventoryDBHandler fakeInvHandler;;
    private AccountingDBHandler fakeAccHandler;
    private DiscountDBHandler fakeDiscHandler;

    @BeforeEach
    public void setUp(){
        fakeInvHandler = new FakeInventoryDBHandler();
        fakeAccHandler = new FakeAccountingDBHandler();
        fakeDiscHandler = new FakeDiscountDBHandler();
        ctrl = new Controller(fakeInvHandler, fakeAccHandler, fakeDiscHandler);
    }

    @Test
    void testCreatePrinter() {

    }

    @Test
    void testCreateReceipt() {

    }

    @Test
    void testStartSale() {
        
        assertDoesNotThrow(() -> ctrl.startSale());
    }

    @Test
    void testScanItem() {

        ctrl.startSale();

        int itemID = 1001;
        int quantity = 2;

        ViewDTO result = ctrl.scanItem(itemID, quantity);

        assertNotNull(result, "ViewDTO should not be null");
        assertEquals("TestItem", result.regItem.item.itemDescription, "Item description should match");
        assertEquals(2, result.regItem.quantity, "Quantity should match input");
    }

    @Test
    void testEndSale() {
        
        ctrl.startSale();
        ctrl.scanItem(1001, 1);

        SaleDTO result = ctrl.endSale();

        assertNotNull(result, "SaleDTO should not be null");
        assertTrue(result.saleSums.totalPrice > 0, "Total price should be greater than zero");
        assertEquals(1, result.itemList.length, "Should contain one registered item");
    }

    @Test
    void testRequestDiscount() {

        ctrl.startSale();
        ctrl.scanItem(1001, 1);

        SaleDTO saleBeforeDiscount = ctrl.endSale();

        int[] discountTypes = {1, 2, 3};
        int customerID = 1;

        SaleDTO saleAfterDiscount = ctrl.requestDiscount(customerID, saleBeforeDiscount, discountTypes);

        assertNotNull(saleAfterDiscount, "Discounted SaleDTO should not be null");
        assertTrue(saleAfterDiscount.saleSums.totalPrice < saleBeforeDiscount.saleSums.totalPrice, "Total price should be reduced after discount");
    }

    @Test
    void testRegisterPayment() {
        ctrl.startSale();
        ctrl.scanItem(1001, 1);  // Add one test item (10 kr)
        ctrl.endSale();

        Amount payment = new Amount(2000); // 20.00 kr

        ctrl.registerPayment(payment);

        FakeAccountingDBHandler acc = (FakeAccountingDBHandler) fakeAccHandler;

        // ✅ Check accounting was updated
        assertTrue(acc.updateCalled, "Accounting handler should have been updated");

        // ✅ Check receipt details
        Receipt receipt = acc.lastReceipt;
        assertNotNull(receipt, "Receipt should not be null");
        assertEquals(2000, receipt.amountPaid.amount, "Paid amount should match input");
        assertTrue(receipt.amountChange.amount > 0, "Change should be greater than 0");
        assertEquals(payment.amount - receipt.saleSums.totalIncVat, receipt.amountChange.amount, 0.01, "Change should be correct");
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
    class FakeDiscountDBHandler extends DiscountDBHandler {
        @Override
        public DiscountDTO fetchDiscount(int[] discountTypes, int customerID, RegisteredItem[] purchasedItems, double totalPrice) {
            return new DiscountDTO(5.0, 0.10, 0.15);
        }
    }

    class FakeAccountingDBHandler extends AccountingDBHandler {
        public boolean updateCalled = false;
        public Receipt lastReceipt = null;

        @Override
        public void updateAccountBalance(Receipt receipt) {
            updateCalled = true;
            lastReceipt = receipt;
        }
    }
}
