package se.gows.processsale.view;

import java.util.Locale;

import se.gows.processsale.utils.TotalIncomePrinter;

/**
 * Class that prints the total income to the view console after a new sale is made.
 * This class is a subclass to TotalIncomePrinter.
 * This class is put in the ObserversList by the Main-class when the program start.
 */
public class TotalIncomeViewPrinter extends TotalIncomePrinter {

    /**
     * Declares the protected abstract method 'doShowTotalIncome' from the superclass. This method
     * is called by the superclass and prints the total income to the view console.
     * 
     * @param totalIncome Total income that is to be printed.
     */
    @Override
    protected void doShowTotalIncome(double totalIncome) {
        System.out.println();
        System.out.println("###############################\n");
        System.out.println("Total Income: " + String.format(Locale.US, "%.2f", totalIncome) + "\n");
        System.out.println("###############################");
    }

    /**
     * This error handler will never be called at the moment, since doShowTotalIncome do not throw any Exceptions.
     */
    @Override
    protected void handleErrors(Exception e) {
        System.out.println();
        System.out.println("Not able to display total income.");
    }
}
