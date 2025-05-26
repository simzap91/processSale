package se.gows.processsale.view;

import java.util.Locale;

import se.gows.processsale.utils.TotalIncomePrinter;

public class TotalIncomeViewPrinter extends TotalIncomePrinter {

    @Override
    protected void doShowTotalIncome(double totalIncome) {
        System.out.println();
        System.out.println("###############################\n");
        System.out.println("Total Income: " + String.format(Locale.US, "%.2f", totalIncome) + "\n");
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
