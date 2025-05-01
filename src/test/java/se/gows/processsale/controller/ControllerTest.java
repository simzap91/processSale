package se.gows.processsale.controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import se.gows.processsale.DTO.*;
import se.gows.processsale.integration.*;
import se.gows.processsale.model.*;

public class ControllerTest {

    private Controller instanceToTest;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;
    private FakeInventoryDBHandler fakeInvHandler;;
    private FakeAccountingDBHandler fakeAccHandler;
    private FakeDiscountDBHandler fakeDiscHandler;

    @BeforeEach
    public void setUp(){
        fakeInvHandler = new FakeInventoryDBHandler();
        fakeAccHandler = new FakeAccountingDBHandler();
        fakeDiscHandler = new FakeDiscountDBHandler();

        instanceToTest = new Controller(fakeInvHandler, fakeAccHandler, fakeDiscHandler);
        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
    }

    @AfterEach
    public void tearDown() {
        fakeInvHandler = null;
        fakeAccHandler = null;
        fakeDiscHandler = null;

        instanceToTest = null;
        printoutBuffer = null;
        System.setOut(originalSysOut);
    }

    @Test
    void testScanItem() {
        instanceToTest.startSale();
        ViewDTO result = instanceToTest.scanItem(1001, 2);

        assertNotNull(result, "ViewDTO not created as expected");
        assertEquals("TestItem", result.regItem.item.itemDescription, "Item description should match");
        assertEquals(2, result.regItem.quantity, "Quantity should match input");
    }

    @Test
    void testEndSale() {
        instanceToTest.startSale();
        instanceToTest.scanItem(1001, 1);
        SaleDTO result = instanceToTest.endSale();

        assertNotNull(result, "SaleDTO should not be null");
        assertTrue(result.saleSums.totalPrice > 0, "Total price should be greater than zero.");
        assertEquals(1, result.itemList.length, "Should contain one registered item.");
    }

    @Test
    void testRequestDiscount() {
        instanceToTest.startSale();
        instanceToTest.scanItem(1001, 1);

        SaleDTO saleBeforeDiscount = instanceToTest.endSale();
        double originalPrice = saleBeforeDiscount.saleSums.totalPrice;

        int[] discountTypes = {1, 2, 3};
        int customerID = 1;

        SaleDTO saleAfterDiscount = instanceToTest.requestDiscount(customerID, saleBeforeDiscount, discountTypes);

        assertNotNull(saleAfterDiscount, "Discounted SaleDTO not created as expected.");
        assertTrue(saleAfterDiscount.saleSums.totalPrice < originalPrice, "Total price not reduced after discount.");
    }

    @Test
    void testRegisterPayment() {
        instanceToTest.startSale();
        instanceToTest.scanItem(1001, 1);
        instanceToTest.endSale();

        Amount testPayment = new Amount(2000);

        instanceToTest.registerPayment(testPayment);

        Receipt receipt = fakeAccHandler.testReceipt;
        assertNotNull(receipt, "Receipt should not be null");
        assertEquals(2000, receipt.amountPaid.amount, "Paid amount should match input");
        assertTrue(receipt.amountChange.amount > 0, "Change should be greater than 0");
        assertEquals(testPayment.amount - receipt.saleSums.totalIncVat, receipt.amountChange.amount, 0.01, "Change should be correct");
    }

    @Test
    void testCreateReceipt() {
        
        instanceToTest.startSale();
        instanceToTest.scanItem(1001, 2);
        SaleDTO saleDto = instanceToTest.endSale();
        
        Amount testPayment = new Amount(5000);
        Transaction testTrans = new Transaction(testPayment, saleDto.saleSums.totalIncVat);

        Receipt receipt = instanceToTest.createReceipt(saleDto, testTrans);

        assertNotNull(receipt, "Receipt should not be null");
        assertEquals(saleDto.timeOfSale, receipt.timeOfSale, "timeOfSale should be copied");
        assertSame  (saleDto.saleSums, receipt.saleSums, "saleSums should be the same object");
        assertArrayEquals(saleDto.itemList, receipt.itemList, "itemList should be the same array");
        assertEquals(testPayment.amount, receipt.amountPaid.amount, "Paid amount matches transaction");
        assertEquals(testTrans.amountChange.amount, receipt.amountChange.amount, "Change matches transaction");
    }

    class FakeInventoryDBHandler extends InventoryDBHandler {
        public boolean updateCalled = false;
        @Override
        public ItemDTO fetchItemFromDB(int itemID) {
            if (itemID == 1001) {
                return new ItemDTO(itemID, "TestItem", 1000, 0.2);
            }
            return null;
        }
        @Override
        public void updateInventoryDB(RegisteredItem[] regItems) {
            updateCalled = true;
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
        public Receipt testReceipt = null;

        @Override
        public void updateAccountBalance(Receipt receipt) {
            updateCalled = true;
            testReceipt = receipt;
        }
    }
}
