package se.gows.processsale.model;

public class Transaction {
    public Amount amountPaid;
    public Amount amountChange;

    public Transaction(Amount amountPaid, double totalPrice){
        this.amountPaid = amountPaid;
        calculateChange(amountPaid, totalPrice);
    }

    private void calculateChange(Amount amountPaid,double totalPrice){
        this.amountChange = new Amount(amountPaid.amount - totalPrice);
    }
}
