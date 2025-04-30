package se.gows.processsale.model;

import java.time.LocalTime;

import se.gows.processsale.DTO.SaleDTO;
import se.gows.processsale.DTO.SumDTO;

public class Receipt {
    public LocalTime timeOfSale;
    public SumDTO saleSums;
    public RegisteredItem[] itemList;
    public Amount amountPaid;
    public Amount amountChange;
    

    public Receipt(SaleDTO saleDTO, Transaction trans) {
        this.timeOfSale = saleDTO.timeOfSale;
        this.saleSums = saleDTO.saleSums;
        this.itemList = saleDTO.itemList;
        this.amountPaid = trans.amountPaid;
        this.amountChange = trans.amountChange;
    }
}
