package se.gows.processsale.view;

import java.util.Locale;

import se.gows.processsale.DTO.SaleDTO;
import se.gows.processsale.utils.SumOfCostsObserver;

/**
 * A class that keeps track of the sum of costs of all sales made during a program run.
 * The class also prints this sum to the View-console.
 * This class implements the SumOfCostsObserver interface and is put in the ObserversList by the Main-class when the program start.
 */
public class TotalRevenueView implements SumOfCostsObserver {
    private double sumOfCosts;

    public TotalRevenueView(){
        this.sumOfCosts = 0;
    }

    /**
     * Called when a new cost is observed. Updates total revenue and prints it in the console.
     * @param sumOfCost The new cost amount to be added.
     */
    @Override
    public void newSumOfCost(SaleDTO saleSummary){
        addNewCost(saleSummary);
        printCurrentSumOfCosts();
    };

    private void addNewCost(SaleDTO saleSummary){
        sumOfCosts += saleSummary.getSaleSums().getTotalIncVat().getValue();
    }

    private void printCurrentSumOfCosts() {
        System.out.println();
        System.out.println("###############################\n");
        System.out.println("Today's total revenue: " + String.format(Locale.US, "%.2f", sumOfCosts) + "\n");
        System.out.println("###############################");
    }
}
