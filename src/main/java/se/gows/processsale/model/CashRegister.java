package se.gows.processsale.model;

/**
 * A CashRegister object that stores information about the transaction and the receipt.
 * @param trans Transaction object that contains the customer's payment and total price of sale.
 * @param receipt All information about the current sale, including an updated total price after dicounts.
 */
public class CashRegister {
    private Receipt receipt;
    private Printer printer;

    public CashRegister (Receipt receipt) {
        this.receipt = receipt;
        this.printer = new Printer();
    }

    public Receipt getReceipt(){return this.receipt;}

    /**
     * Public method that prints the receipt
     */
    public void printReceipt(){
        this.printer.printReceipt(receipt);
    }
}