package se.gows.processsale.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import se.gows.processsale.model.Amount;

public class TotalRevenueFileOutput implements SumOfCostsObserver {
    private final String filePath;

    public TotalRevenueFileOutput(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void newSumOfCost(Amount totalRevenue) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            writer.printf(">>> Total Revenue (File): %.2f%n", totalRevenue);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
