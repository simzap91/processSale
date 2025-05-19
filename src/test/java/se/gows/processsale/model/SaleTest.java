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
        instanceToTest.addNewItem(testItemDTO, 2);
        boolean resultThatShouldBeTrue = instanceToTest.isItemInItemList(14);
        boolean resultThatShouldBeFalse = instanceToTest.isItemInItemList(15);

        assertTrue(resultThatShouldBeTrue, "Test id not found in list as expected.");
        assertFalse(resultThatShouldBeFalse, "Test id found in list but it should not.");
    }

    @Test
    public void testUpdateSaleAndCreateViewDTO() {
        ItemDTO testItemDTO = new ItemDTO(14, "TestItem", 10, 0.2);
        instanceToTest.addNewItem(testItemDTO, 2);
        instanceToTest.updateExistingItem(14, 4);

        ViewDTO testViewDTO = instanceToTest.createViewDTO(14);

        int quantityAfterUpdate = testViewDTO.getRegItem().getQuantity();
        int expectedResult = 6;

        assertNotNull(testViewDTO, "ViewDTO not created as expected.");
        assertEquals(quantityAfterUpdate, expectedResult, "Item quantity not updated as expected.");
    }

    @Test
    public void testEndSale() {
        ItemDTO testItemDTO = new ItemDTO(14, "TestItem", 10, 0.2);
        instanceToTest.addNewItem(testItemDTO, 2);
        SaleDTO testSaleDTO = instanceToTest.endSale();
        double expectedTotPrice = 2 * 10;
        double expectedTotVat = 2 * 10 * 0.2;
        double expectedTotIncVat = expectedTotPrice + expectedTotVat;

        assertEquals(1, testSaleDTO.getItemList().length, "Number of items in items list not as expected.");
        assertEquals(expectedTotPrice, testSaleDTO.getSaleSums().getTotalPrice().getValue(), "TotIncVat in SaleDTO not as expected.");
        assertEquals(expectedTotVat, testSaleDTO.getSaleSums().getTotalVAT().getValue(), "TotIncVat in SaleDTO not as expected.");
        assertEquals(expectedTotIncVat, testSaleDTO.getSaleSums().getTotalIncVat().getValue(), "TotIncVat in SaleDTO not as expected.");
    }
}
