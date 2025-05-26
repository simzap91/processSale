package se.gows.processsale.utils;

import java.util.ArrayList;
import java.util.List;

import se.gows.processsale.view.TotalIncomeViewPrinter;

/**
 * A class that holds a list with observed objects that implements the SumOfCostsObserver interface.
 * This class is initialized in the Main-class when the program start.
 */
public class ObserversList {
    private ArrayList<SumOfCostsObserver> sumOfCostsObservers;

    public ObserversList () {
        this.sumOfCostsObservers = new ArrayList<>();
        addSumOfCostObserver(new TotalIncomeViewPrinter());
        addSumOfCostObserver(new TotalIncomeFilePrinter("totalIncomeDisplay.txt"));
    }

    /**
     * Adds observer to the list of observers
     * 
     * @param obs Observer that is to be added.
     */
    public void addSumOfCostObserver(SumOfCostsObserver obs) {
        sumOfCostsObservers.add(obs);
    }

    public List<SumOfCostsObserver> getSumOfCostsObservers() {
        return this.sumOfCostsObservers;
    }
}
