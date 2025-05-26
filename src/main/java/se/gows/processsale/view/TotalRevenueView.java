package se.gows.processsale.view;

import java.util.Locale;

import se.gows.processsale.utils.TotalRevenueDisplay;

/**
 * A subclass to the TotalRevenueDisplay superclass that prints the total revenue sum to the View-console.
 * This class is put in the ObserversList by the Main-class when the program start.
 */
public class TotalRevenueView extends TotalRevenueDisplay {

    @Override
    protected void doShowTotalIncome(double sumOfCosts) {
        System.out.println();
        System.out.println("###############################\n");
        System.out.println("Total income: " + String.format(Locale.US, "%.2f", sumOfCosts) + "\n");
        System.out.println("###############################");
    }

    /**
     * This error handler will not be called at the moment, since doShowTotalIncome does not throw any Exceptions.
     */
    @Override
    protected void handleErrors(Exception e) {
        System.out.println();
        System.out.println("Not able to display total income.");
    }
}
