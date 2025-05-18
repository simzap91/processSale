package se.gows.processsale.model;

import java.util.ArrayList;

import se.gows.processsale.DTO.SaleDTO;
import se.gows.processsale.utils.ObserversList;
import se.gows.processsale.utils.SumOfCostsObserver;

/**
 * Class that stores sale transaction and receipt.
 * The class also contains a receipt printer that prints a receipt when a new payment is registered.
 */
public class CashRegister {
    private Printer printer;
    private Transaction transaction;
    private Receipt receipt;
    private ObserversList obsList;

    public CashRegister (ObserversList obsList) {
        this.printer = new Printer();
        this.obsList = obsList;
    }

    /**
     * Register payment as a new Transaction object, creates a new receipt and finally prints the receipt.
     * @param payment Payment made by customer.
     * @param sale Information about the sale.
     */
    public void registerPayment(Amount payment, SaleDTO sale){
        transaction = new Transaction(payment, sale.getSaleSums().getTotalIncVat());
        createReceipt(sale);
        printReceipt();
        notifyObservers(sale);
    }

    private void createReceipt(SaleDTO sale) {receipt = new Receipt(sale, transaction);}
    private void printReceipt(){printer.printReceipt(receipt);}
    public Receipt getReceipt(){return this.receipt;}


    private void notifyObservers(SaleDTO sale) {
        for (SumOfCostsObserver obs : obsList.getSumOfCostsObservers()) {
            obs.newSumOfCost(sale.getSaleSums().getTotalIncVat());
        }
    }
}