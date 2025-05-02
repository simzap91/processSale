package se.gows.processsale.model;

import java.time.LocalDateTime;

import se.gows.processsale.DTO.SaleDTO;
import se.gows.processsale.DTO.SumDTO;

public class Receipt {
    public LocalDateTime timeOfSale;
    public SumDTO saleSums;
    public RegisteredItem[] itemList;
    public Amount amountPaid;
    public Amount amountChange;

    /**
    * Public method that initializes a receipt object by copying sale details from a saleDTO object that it receives, 
    * and payment details from a Transaction object named trans.
    * @param saleDTO
    * @param trans
    */
    public Receipt(SaleDTO saleDTO, Transaction trans) {
        this.timeOfSale = saleDTO.timeOfSale;
        this.saleSums = saleDTO.saleSums;
        this.itemList = saleDTO.itemList;
        this.amountPaid = trans.amountPaid;
        this.amountChange = trans.amountChange;
    }
}
