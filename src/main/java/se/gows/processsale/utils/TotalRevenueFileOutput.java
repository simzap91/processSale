package se.gows.processsale.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import se.gows.processsale.model.*;

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

    private void addNewCost(Amount cost){
        sumOfCosts += cost.getValue();
    }

    /**
     * Called when a new cost is observed. Updates total revenue and writes to file.
     * @param sumOfCost The new cost amount to be added.
     */
    @Override
    public void newSumOfCost(Amount sumOfCost) {
        addNewCost(sumOfCost);
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
