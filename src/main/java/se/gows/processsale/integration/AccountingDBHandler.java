package se.gows.processsale.integration;

import se.gows.processsale.model.Receipt;

public class AccountingDBHandler {
    private double accountBalance = 100;
    
    public void updateAccountBalance(Receipt receipt) {
        accountBalance += receipt.amountPaid.amount;
        accountBalance -= receipt.amountChange.amount;
        System.out.println("Acounting dataBase updated!");
        System.out.println("New balance: " + accountBalance);
    }
}
