package se.gows.processsale.integration;

import java.util.Locale;

import se.gows.processsale.model.Receipt;

public class AccountingDBHandler {
    private double accountBalance = 100; 
    /**     
     * Public method that updates the internal accountbalance
     * Method uses receipt 
     * @param amountPaid
     * @param totalPriceIncVat
     * @return
     */
    public void updateAccountBalance(Receipt receipt) {
        accountBalance += receipt.amountPaid.amount;
        accountBalance -= receipt.amountChange.amount;
        System.out.println("Accounting database updated!");
        System.out.println("New balance: " +  String.format(Locale.US, "%.2f",accountBalance));
    }
}
