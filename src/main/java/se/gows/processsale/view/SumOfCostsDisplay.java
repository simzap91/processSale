package se.gows.processsale.view;

import java.util.ArrayList;
import java.util.List;

import se.gows.processsale.model.*;

public class SumOfCostsDisplay implements SumOfCostsObserver {
    private List<Amount> costs;

    public SumOfCostsDisplay(){
        this.costs = new ArrayList<>();
    }

    @Override
    public void newSumOfCost(Amount sumOfCost){
        addNewCost(sumOfCost);
        printCurrentSumOfCosts();
    };

    private void addNewCost(Amount cost){
        costs.add(cost);
    }

    private void printCurrentSumOfCosts() {
        double sumOfCosts = 0;
        for (Amount cost : costs) {
            sumOfCosts += cost.getValue();
        }
        System.out.println("###############################\n");
        System.out.println("Today's sum of costs: " + sumOfCosts + "\n");
        System.out.println("###############################");
    }
}
