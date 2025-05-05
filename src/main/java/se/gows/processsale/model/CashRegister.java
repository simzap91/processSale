package se.gows.processsale.model;

/**
 * A CashRegister object that contains the information about the transaction and the receipt, and stores it in controller
 * @param trans customers payements and total price of sale, stores as a Transaction object
 * @param receipt All information about the current sale, including a updated total price from dicounts.
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