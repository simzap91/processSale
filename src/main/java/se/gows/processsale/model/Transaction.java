package se.gows.processsale.model;

/**
 * Transaction object contain information about a customers payment and the price for the entire sale.
 * Method uses amountPaid from Payment method and totalPriceIncVat from currentSaleDTO
 * @param amountPaid amount paid by customer
 * @param totalPriceIncVat total price (inc Vat) of the sale
 * @return Transaction
 */
public class Transaction {
    private Amount amountPaid;
    private Amount amountChange;
    
    public Transaction(Amount amountPaid, double totalPriceIncVat){
        this.amountPaid = amountPaid;
        calculateChange(amountPaid, totalPriceIncVat);
    }
    /**
    * private method that calculates the amount of change that should be returned to the customer
    * Method uses amountPaid from Payment method and totalPriceIncVat from currentSaleDTO
    * @param amountPaid
    * @param totalPriceIncVat
    * @return
    */
    private void calculateChange(Amount amountPaid, double totalPriceIncVat){
        this.amountChange = new Amount(amountPaid.getValue() - totalPriceIncVat);
    }

    /**
     * @return The amount paid by the customer
     */
    public Amount getAmountPaid() {
        return amountPaid;
    }

    /**
     * @param amountPaid The amount paid by the customer
     */
    public void setAmountPaid(Amount amountPaid) {
        this.amountPaid = amountPaid;
    }

    /**
     * @return The amount of change to return to the customer
     */
    public Amount getAmountChange() {
        return amountChange;
    }

    /**
     * @param amountChange The amount of change to return to the customer
     */
    public void setAmountChange(Amount amountChange) {
        this.amountChange = amountChange;
    }
}