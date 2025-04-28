package se.gows.processsale.model;

public class Transaction {
    public Amount amountChange;
    public double totalPrice;

    private Amount calculateChange(Amount amountPaid,double totalPrice){
        Amount change = new Amount(amountPaid.amount - totalPrice);
        return change;

    }

    public Transaction(Amount amountChange, double totalPrice){
        this.amountChange = calculateChange(amountChange, totalPrice);
        this.totalPrice = totalPrice;
    }
}
