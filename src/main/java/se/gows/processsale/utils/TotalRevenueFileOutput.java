package se.gows.processsale.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import se.gows.processsale.model.*;

public class TotalRevenueFileOutput implements SumOfCostsObserver {
    private String filePath;
    private double sumOfCosts;

    public TotalRevenueFileOutput(String filePath) {
        this.filePath = filePath;
    }

    public TotalRevenueFileOutput(){
        this.sumOfCosts = 0;
    }


    private void addNewCost(Amount cost){
        sumOfCosts += cost.getValue();
    }


    @Override
    public void newSumOfCost(Amount sumOfCost) {
        addNewCost(sumOfCost);
        FileOutputSumOfCost();
    }

    
    private void FileOutputSumOfCost() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            writer.printf(">>> Total Revenue : %.2f kr%n", sumOfCosts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
