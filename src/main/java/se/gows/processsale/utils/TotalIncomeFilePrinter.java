package se.gows.processsale.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class that prints the total income to a file after a new sale is made.
 * This class is a subclass to TotalIncomePrinter.
 * This class is put in the ObserversList by the Main-class when the program start.
 */
public class TotalIncomeFilePrinter extends TotalIncomePrinter {

    private String filePath;
    private FileLogger logger;

    /**
     * Creates an instance that first runs the constructor of TotalRevenueDisplay, then creates
     * a new filePath from passed String.
     * 
     * @param filePath Path to file where total income will be written.
     */
    public TotalIncomeFilePrinter(String filePath) {
        this.filePath = filePath;
        this.logger = new FileLogger();
    }
    
    @Override
    protected void doShowTotalIncome(double totalIncome) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath), true)) {
            writer.printf(">>> Total Income : %.2f kr%n", totalIncome);
        }
    }

    @Override
    protected void handleErrors(Exception e) {
        logger.log("Failed to print total income to file: " + e.getMessage());
    }
}
