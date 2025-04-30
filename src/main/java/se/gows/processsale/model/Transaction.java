package se.gows.processsale.model;

public class Transaction {
    public Amount amountPaid;
    public Amount amountChange;

    public Transaction(Amount amountPaid, double totalPriceIncVat){
        this.amountPaid = amountPaid;
        calculateChange(amountPaid, totalPriceIncVat);
    }

    private void calculateChange(Amount amountPaid,double totalPrice){
        this.amountChange = new Amount(amountPaid.amount - totalPrice);
    }
}
