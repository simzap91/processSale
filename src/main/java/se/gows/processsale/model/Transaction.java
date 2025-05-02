package se.gows.processsale.model;

public class Transaction {
    public Amount amountPaid;
    public Amount amountChange;
    /**
     * Public method that creates a Transaction object
     * Method uses amountPaid from Payment method and totalPriceIncVat from currentSaleDTO
     * @param amountPaid
     * @param totalPriceIncVat
     * @return Transaction
     */
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
        this.amountChange = new Amount(amountPaid.amount - totalPriceIncVat);
    }
}