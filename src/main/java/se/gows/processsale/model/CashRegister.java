package se.gows.processsale.model;

import se.gows.processsale.DTO.SaleDTO;

/**
 * A CashRegister object that stores information about the transaction and the receipt.
 * @param trans Transaction object that contains the customer's payment and total price of sale.
 * @param receipt All information about the current sale, including an updated total price after dicounts.
 */
public class CashRegister {
    private Printer printer;
    private Transaction transaction;
    private Receipt receipt;

    public CashRegister () {this.printer = new Printer();}

    public void registerPayment(Amount payment, SaleDTO sale){
        transaction = new Transaction(payment, sale.getSaleSums().getTotalIncVat());
        createReceipt(sale);
        printReceipt();
    }

    /**
     * Creates new receipt.
     * @param saleDTO sale data
     * @return receipt
     */
    private void createReceipt(SaleDTO sale) {receipt = new Receipt(sale, transaction);}
    
    /**
     * Public method that prints the receipt
     */
    public void printReceipt(){printer.printReceipt(receipt);}

    public Receipt getReceipt(){return this.receipt;}

    
}