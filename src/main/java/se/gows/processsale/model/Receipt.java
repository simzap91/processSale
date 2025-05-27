package se.gows.processsale.model;

import java.time.LocalDateTime;

import se.gows.processsale.DTO.*;
import se.gows.processsale.utils.Amount;

/**
 * Public class for the receipt.
 */
public class Receipt {
    private LocalDateTime timeOfSale;
    private SumDTO saleSums;
    private RegisteredItemDTO[] itemList;
    private Amount amountPaid;
    private Amount amountChange;

    /**
     * Public method that initializes a receipt object by copying sale details from a SaleDTO object that it receives, 
     * and payment details from a Transaction object named trans.
     * 
     * @param saleSummary Holds sale information
     * @param trans Holds transaction information
     */
    public Receipt(SaleDTO saleSummary, Transaction trans) {
        this.timeOfSale = LocalDateTime.now();
        this.saleSums = saleSummary.getSaleSums();
        this.itemList = saleSummary.getItemList();
        this.amountPaid = trans.getAmountPaid();
        this.amountChange = trans.getAmountChange();
    }

    public LocalDateTime getTimeOfSale() {return timeOfSale;}
    public SumDTO getSaleSums() {return saleSums;}
    public RegisteredItemDTO[] getItemList() {return itemList;}
    public Amount getAmountPaid() {return amountPaid;}
    public Amount getAmountChange() {return amountChange;}
}
