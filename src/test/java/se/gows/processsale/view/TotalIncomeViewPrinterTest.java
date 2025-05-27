package se.gows.processsale.view;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TotalIncomeViewPrinterTest {

    private TotalIncomeViewPrinter instanceToTest;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;

    @BeforeEach
    public void setUp(){
        instanceToTest = new TotalIncomeViewPrinter();
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
    void testDoShowTotalIncome() {
        double testTotalIncome = 99.99;
        String expectedOutput = "Total Income: " + testTotalIncome;
        
        instanceToTest.doShowTotalIncome(testTotalIncome);
        String printout = printoutBuffer.toString();

        assertTrue(printout.contains(expectedOutput), "Total income not printed as expected.");
    }

    @Test
    void testHandleErrors() {
        String expectedOutput = "Not able to display total income.";
        instanceToTest.handleErrors(new Exception("Simulated exception."));
        String printout = printoutBuffer.toString();
        assertTrue(printout.contains(expectedOutput), "handleErrors not printing message as expected.");
    }
}
