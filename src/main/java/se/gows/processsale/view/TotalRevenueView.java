package se.gows.processsale.view;

import java.util.Locale;

import se.gows.processsale.utils.TotalRevenueDisplay;
import se.gows.processsale.utils.FileLogger;

/**
 * A subclass to the TotalRevenueDisplay superclass that prints the total revenue sum to the View-console.
 * This class is put in the ObserversList by the Main-class when the program start.
 */
public class TotalRevenueView extends TotalRevenueDisplay {

    private FileLogger logger = new FileLogger();

    @Override
    protected void doShowTotalIncome(double sumOfCosts) {
        System.out.println();
        System.out.println("###############################\n");
        System.out.println("Total income: " + String.format(Locale.US, "%.2f", sumOfCosts) + "\n");
        System.out.println("###############################");
    }

    @Override
    protected void handleErrors(Exception e) {
        logger.log(e.getMessage());
        System.out.println("Not able to display total income.");
    }
}
