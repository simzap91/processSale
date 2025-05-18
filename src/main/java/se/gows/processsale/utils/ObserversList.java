package se.gows.processsale.utils;

import java.util.ArrayList;
import java.util.List;

import se.gows.processsale.view.TotalRevenueView;

public class ObserversList {
    private ArrayList<SumOfCostsObserver> sumOfCostsObservers;

    public ObserversList () {
        this.sumOfCostsObservers = new ArrayList<>();
        addSumOfCostObserver(new TotalRevenueView());
        addSumOfCostObserver(new TotalRevenueFileOutput("revenueFileOutput.txt"));
    }

    /**
     * Adds observer to the list of observers
     * @param obs the observer 
     */
    public void addSumOfCostObserver(SumOfCostsObserver obs) {
        sumOfCostsObservers.add(obs);
    }

    public List<SumOfCostsObserver> getSumOfCostsObservers() {
        return this.sumOfCostsObservers;
    }
}
