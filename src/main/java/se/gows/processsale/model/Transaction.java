package se.gows.processsale.model;

public class Transaction {
    private Amount amountChange;
    private double totalPrice;

    private Amount calculateChange(Amount amountPaid,double totalPrice){
        Amount change = new Amount(amountPaid.getValue() - totalPrice);
        return change;
    }

    public Transaction(Amount amountChange, double totalPrice){
        this.amountChange = calculateChange(amountChange, totalPrice);
        this.totalPrice = totalPrice;
    }

    public int payment(Amount payment, double totalPrice){
        
        return pay;
    }
}
