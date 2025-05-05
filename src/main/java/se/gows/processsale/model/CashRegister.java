package se.gows.processsale.model;

/**
 * A CashRegister object that contains the information about the transaction and the receipt, and stores it in controller
 * @param trans customers payements and total price of sale, stores as a Transaction object
 * @param receipt All information about the current sale, including a updated total price from dicounts.
 */
public class CashRegister {
    public Transaction trans;
    public Receipt receipt;
    public Printer printer;

    public CashRegister (Transaction trans, Receipt receipt) {
        this.trans = trans;
        this.receipt = receipt;
        this.printer = new Printer();
    }
/**
 * Public method that prints the receipt
 */
    public void printReceipt(){
        this.printer.printReceipt(receipt);
    }
}