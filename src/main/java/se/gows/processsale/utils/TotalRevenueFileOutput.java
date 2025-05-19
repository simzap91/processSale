package se.gows.processsale.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import se.gows.processsale.DTO.SaleDTO;

/**
 * A class that keeps track of the sum of costs of all sales made during a program run.
 * The class also writes this sum to a .txt-file.
 * This class implements the SumOfCostsObserver interface and is put in the ObserversList by the Main-class when the program start.
 */
public class TotalRevenueFileOutput implements SumOfCostsObserver {
    private String filePath;
    private double sumOfCosts;

    /**
     * Creates an instance that writes revenue to the specified file path.
     * @param filePath The path to the file where total revenue will be written.
     */
    public TotalRevenueFileOutput(String filePath) {
        this.filePath = filePath;
        this.sumOfCosts = 0;
    }

    private void addNewCost(SaleDTO saleSummary){
        sumOfCosts += saleSummary.getSaleSums().getTotalIncVat().getValue();
    }

    /**
     * Called when a new cost is observed. Updates total revenue and writes to file.
     * @param sumOfCost The new cost amount to be added.
     */
    @Override
    public void newSumOfCost(SaleDTO saleSummary) {
        addNewCost(saleSummary);
        FileOutputSumOfCost();
    }
    
    private void FileOutputSumOfCost() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath), true)) {
            writer.printf(">>> Total Revenue : %.2f kr%n", sumOfCosts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
