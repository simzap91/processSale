package se.gows.processsale.view;

import java.util.Locale;

import se.gows.processsale.model.*;
import se.gows.processsale.utils.SumOfCostsObserver;

public class TotalRevenueView implements SumOfCostsObserver {
    private double sumOfCosts;

    public TotalRevenueView(){
        this.sumOfCosts = 0;
    }

    @Override
    public void newSumOfCost(Amount sumOfCost){
        addNewCost(sumOfCost);
        printCurrentSumOfCosts();
    };

    private void addNewCost(Amount cost){
        sumOfCosts += cost.getValue();
    }

    private void printCurrentSumOfCosts() {
        System.out.println();
        System.out.println("###############################\n");
        System.out.println("Today's total revenue: " + String.format(Locale.US, "%.2f", sumOfCosts) + "\n");
        System.out.println("###############################");
    }
}
