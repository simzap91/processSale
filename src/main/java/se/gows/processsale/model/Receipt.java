package se.gows.processsale.model;

import java.time.LocalDateTime;

import se.gows.processsale.DTO.SaleDTO;
import se.gows.processsale.DTO.SumDTO;

/**
 * Public class for receipt.
 */
public class Receipt {
    public LocalDateTime timeOfSale;
    public SumDTO saleSums;
    public RegisteredItem[] itemList;
    public Amount amountPaid;
    public Amount amountChange;

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
        this.amountPaid = trans.amountPaid;
        this.amountChange = trans.amountChange;
    }
}
