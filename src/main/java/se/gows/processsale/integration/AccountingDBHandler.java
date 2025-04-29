package se.gows.processsale.integration;

import se.gows.processsale.model.Receipt;

public class AccountingDBHandler {
    private double acountBalance = 100;
    
    public void updateAccountingDB(Receipt receipt) {
        acountBalance += receipt.amountPaid.amount;
        acountBalance -= receipt.amountChange.amount;
        System.out.println("Acount Balace: " + acountBalance);
        System.out.println("Acounting dataBase updated!");
    }
}
