package se.gows.processsale.model;

import se.gows.processsale.DTO.SaleDTO;
import se.gows.processsale.utils.Amount;
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
     * @param saleSummary Information about the sale.
     */
    public void registerPayment(Amount payment, SaleDTO saleSummary){
        transaction = new Transaction(payment, saleSummary.getSaleSums().getTotalIncVat());
        createReceipt(saleSummary);
        printReceipt();
        notifyObservers(saleSummary.getSaleSums().getTotalPrice().getValue());
    }

    private void createReceipt(SaleDTO saleSummary) {receipt = new Receipt(saleSummary, transaction);}
    private void printReceipt(){printer.printReceipt(receipt);}
    public Receipt getReceipt(){return this.receipt;}

    private void notifyObservers(double totalCostOfSale) {
        for (SumOfCostsObserver obs : obsList.getSumOfCostsObservers()) {
            obs.newSaleWasMade(totalCostOfSale);
        }
    }
}