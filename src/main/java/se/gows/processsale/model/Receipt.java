package se.gows.processsale.model;

import java.time.LocalDateTime;

import se.gows.processsale.DTO.SaleDTO;
import se.gows.processsale.DTO.SumDTO;

/**
 * Public class for receipt.
 */
public class Receipt {
    private LocalDateTime timeOfSale;
    private SumDTO saleSums;
    private RegisteredItem[] itemList;
    private Amount amountPaid;
    private Amount amountChange;

    /**
    * Public method that initializes a receipt object by copying sale details from a saleDTO object that it receives, 
    * and payment details from a Transaction object named trans.
    * @param saleDTO Holds sale information
    * @param trans Holds transaction information
    */
    public Receipt(SaleDTO saleDTO, Transaction trans) {
        this.timeOfSale = saleDTO.getTimeOfSale();
        this.saleSums = saleDTO.getSaleSums();
        this.itemList = saleDTO.getItemList();
        this.amountPaid = trans.getAmountPaid();
        this.amountChange = trans.getAmountChange();
    }

    /**
     * @return The time when the sale occurred
     */
    public LocalDateTime getTimeOfSale() {
        return timeOfSale;
    }

    /**
     * @param timeOfSale The time when the sale occurred
     */
    public void setTimeOfSale(LocalDateTime timeOfSale) {
        this.timeOfSale = timeOfSale;
    }

    /**
     * @return An object containing totals for price, VAT, and total including VAT
     */
    public SumDTO getSaleSums() {
        return saleSums;
    }

    /**
     * @param saleSums The object containing total price and VAT information for the sale
     */
    public void setSaleSums(SumDTO saleSums) {
        this.saleSums = saleSums;
    }

    /**
     * @return An array of items included in the sale
     */
    public RegisteredItem[] getItemList() {
        return itemList;
    }

    /**
     * @param itemList The array of items included in the sale
     */
    public void setItemList(RegisteredItem[] itemList) {
        this.itemList = itemList;
    }

    /**
     * @return The amount of money paid by the customer
     */
    public Amount getAmountPaid() {
        return amountPaid;
    }

    /**
     * @param amountPaid The amount of money paid by the customer
     */
    public void setAmountPaid(Amount amountPaid) {
        this.amountPaid = amountPaid;
    }

    /**
     * @return The amount of change returned to the customer
     */
    public Amount getAmountChange() {
        return amountChange;
    }

    /**
     * @param amountChange The amount of change returned to the customer
     */
    public void setAmountChange(Amount amountChange) {
        this.amountChange = amountChange;
    }
}
