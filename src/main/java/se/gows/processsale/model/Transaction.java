package se.gows.processsale.model;

public class Transaction {
    public Amount amountChange;
    public Amount amountPaid;
    public double totalPrice;

    private Amount calculateChange(Amount amountPaid,double totalPrice){
        Amount change = new Amount(amountPaid.amount - totalPrice);
        return change;

    }

    public Transaction(Amount amountPaid,Amount amountChange, double totalPrice){
        this.amountChange = calculateChange(amountChange, totalPrice);
        this.totalPrice = totalPrice;
        this.amountPaid = amountPaid;
    }
}
