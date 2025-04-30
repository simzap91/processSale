package se.gows.processsale.model;

public class CashRegister {
    public Transaction trans;
    public Receipt receipt;

    public CashRegister (Transaction trans, Receipt receipt) {
        this.trans = trans;
        this.receipt = receipt;
    }
}
