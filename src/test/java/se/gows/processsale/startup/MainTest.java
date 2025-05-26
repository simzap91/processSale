package se.gows.processsale.startup;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class MainTest {

    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;

    @BeforeEach
    public void setUp(){
        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
    }

    @AfterEach
    public void tearDown() {
        printoutBuffer = null;
        System.setOut(originalSysOut);
    }

    @Test
    void testMain() {
        String[] args = null;
        Main.main(args);

        String printout = printoutBuffer.toString();
        String expectedWordInOutput = "started";
        assertTrue(printout.contains(expectedWordInOutput), "Test run did not start correctly.");
    }
}
