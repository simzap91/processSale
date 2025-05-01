package se.gows.processsale.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import se.gows.processsale.DTO.*;
import se.gows.processsale.integration.*;
import se.gows.processsale.model.*;

public class SaleTest {

    private Sale instanceToTest;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;

    @BeforeEach
    public void setUp(){
        instanceToTest = new Sale();
        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
    }

    @AfterEach
    public void tearDown() {
        instanceToTest = null;
        printoutBuffer = null;
        System.setOut(originalSysOut);
    }

    @Test
    public void testAddItemAndCheckItemList() {
        ItemDTO testItemDTO = new ItemDTO(14, "TestItem", 10, 10);
        instanceToTest.addItem(testItemDTO, 2);
        boolean result = instanceToTest.checkItemList(14);

        assertTrue(result, "Test id not found in list as expected.");
    }

    @Test
    public void testUpdateItem() {
        ItemDTO testItemDTO = new ItemDTO(14, "TestItem", 10, 10);
        instanceToTest.addItem(testItemDTO, 2);
        instanceToTest.updateItem(14, 4);
        int expectedResult = 6;

        
    }


}
