package se.gows.processsale.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import se.gows.processsale.DTO.*;

public class SaleTest {

    private Sale instanceToTest;

    @BeforeEach
    public void setUp(){
        instanceToTest = new Sale();
    }

    @AfterEach
    public void tearDown() {
        instanceToTest = null;
    }

    @Test
    public void testAddItemAndCheckItemList() {
        ItemDTO testItemDTO = new ItemDTO(14, "TestItem", 10, 0.2);
        instanceToTest.addItem(testItemDTO, 2);
        boolean resultThatShouldBeTrue = instanceToTest.checkItemList(14);
        boolean resultThatShouldBeFalse = instanceToTest.checkItemList(15);

        assertTrue(resultThatShouldBeTrue, "Test id not found in list as expected.");
        assertFalse(resultThatShouldBeFalse, "Test id found in list but it should not.");
    }

    @Test
    public void testUpdateSaleAndCreateViewDTO() {
        ItemDTO testItemDTO = new ItemDTO(14, "TestItem", 10, 0.2);
        instanceToTest.addItem(testItemDTO, 2);
        instanceToTest.updateSale(14, 4);

        ViewDTO testViewDTO = instanceToTest.createViewDTO(14);

        int quantityAfterUpdate = testViewDTO.regItem.quantity;
        int expectedResult = 6;

        assertNotNull(testViewDTO, "ViewDTO not created as expected.");
        assertEquals(quantityAfterUpdate, expectedResult, "Item quantity not updated as expected.");
    }

    @Test
    public void testEndSale() {
        ItemDTO testItemDTO = new ItemDTO(14, "TestItem", 10, 0.2);
        instanceToTest.addItem(testItemDTO, 2);
        SaleDTO testSaleDTO = instanceToTest.endSale();
        double expectedTotPrice = 2 * 10;
        double expectedTotVat = 2 * 10 * 0.2;
        double expectedTotIncVat = expectedTotPrice + expectedTotVat;

        assertEquals(1, testSaleDTO.itemList.length, "Number of items in items list not as expected.");
        assertEquals(expectedTotPrice, testSaleDTO.saleSums.totalPrice, "TotIncVat in SaleDTO not as expected.");
        assertEquals(expectedTotVat, testSaleDTO.saleSums.totalVAT, "TotIncVat in SaleDTO not as expected.");
        assertEquals(expectedTotIncVat, testSaleDTO.saleSums.totalIncVat, "TotIncVat in SaleDTO not as expected.");
    }

    @Test
    public void testCalculateDiscount() {
        ItemDTO testItemDTO = new ItemDTO(14, "TestItem", 10, 0.2);
        instanceToTest.addItem(testItemDTO, 2);
        SaleDTO testSaleDTO = instanceToTest.endSale();

        DiscountDTO testDiscount = new DiscountDTO(10, 0.2, 0);
        testSaleDTO = instanceToTest.calculateDiscount(testSaleDTO, testDiscount);
        double expectedNewTotal = 10 - 10 * 0.2;

        assertEquals(expectedNewTotal, testSaleDTO.saleSums.totalPrice, "Total price not reduced after discount as expected.");
    }
}
