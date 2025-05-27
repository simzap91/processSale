package se.gows.processsale.model;

import se.gows.processsale.utils.Amount;

/**
 * Transaction object containing information about a customers payment and the change paid back to the customer.
 */
public class Transaction {
    private Amount amountPaid;
    private Amount amountChange;
    
    /**
     * @param amountPaid amount paid by customer
     * @param totalPriceIncVat total price (inc Vat) of the sale
     */
    public Transaction(Amount amountPaid, Amount totalPriceIncVat){
        this.amountPaid = amountPaid;
        calculateChange(amountPaid, totalPriceIncVat);
    }
    /**
     * Private method that calculates the amount of change that should be returned to the customer.
     * 
     * @param amountPaid amount paid by customer
     * @param totalPriceIncVat total price (inc Vat) of the sale
     */
    private void calculateChange(Amount amountPaid, Amount totalPriceIncVat){
        this.amountChange = new Amount(amountPaid.getValue() - totalPriceIncVat.getValue());
    }

    public Amount getAmountPaid() {return amountPaid;}
    public Amount getAmountChange() {return amountChange;}
}