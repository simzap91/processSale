package se.gows.processsale.integration;

import java.util.Locale;

import se.gows.processsale.model.Receipt;

/**     
     * Public handler that communicates with external accounting data base. 
     * In this case the data base consists of the variable accountBalance which is declared internally and updated by this class.
     * */
public class AccountingDBHandler {
    private double accountBalance = 100;
    /**     
     * Public method that updates the internal accountbalance and prints the updated balance to console
     * Method uses receipt 
     * @param amountPaid amount of cash received from customer
     * @param totalPriceIncVat total price from the entire sale
     */
    public void updateAccountBalance(Receipt receipt) {
        accountBalance += receipt.amountPaid.amount;
        accountBalance -= receipt.amountChange.amount;
        System.out.println("Accounting database updated!");
        System.out.println("New balance: " +  String.format(Locale.US, "%.2f",accountBalance));
    }
}
