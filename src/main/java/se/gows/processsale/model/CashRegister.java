package se.gows.processsale.model;

public class CashRegister {
    public Transaction trans;
    public Receipt receipt;
    public Printer printer;

    public CashRegister (Transaction trans, Receipt receipt) {
        this.trans = trans;
        this.receipt = receipt;
        this.printer = new Printer();
    }

    public void printReceipt(){
        this.printer.printReceipt(receipt);
    }
}