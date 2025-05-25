package se.gows.processsale.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * A subclass to the TotalRevenueDisplay superclass that prints the total revenue sum to a file.
 * This class is put in the ObserversList by the Main-class when the program start.
 */
public class TotalRevenueFileOutput extends TotalRevenueDisplay {
    private String filePath;

    /**
     * Creates an instance that first runs the constructor of TotalRevenueDisplay, then creates
     * a new filePath from passed String.
     * @param filePath The path to the file where total revenue will be written.
     */
    public TotalRevenueFileOutput(String filePath) {
        this.filePath = filePath;
    }
    
    protected void doShowTotalIncome(double sumOfCosts) throws Exception {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath), true)) {
            writer.printf(">>> Total Revenue : %.2f kr%n", sumOfCosts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void handleErrors(Exception e) {
        // SKRIV DENNA
    }
}
