package se.gows.processsale.DTO;

import java.time.LocalTime;

import se.gows.processsale.model.RegisteredItem;

/**
     * Sale DTO. Holds information about a sale.
     * @param timeOfSale time when sale started
     * @param saleSums contains total sum, total VAT and total sum inc. VAT of the sale
     * @param itemList list with purchased items
     */
public class SaleDTO {
    public LocalTime timeOfSale;
    public SumDTO saleSums;
    public RegisteredItem[] itemList;

    public SaleDTO (LocalTime timeOfSale, double totalPrice, double totalVAT, RegisteredItem[] itemList){
        this.timeOfSale = timeOfSale;
        this.saleSums = new SumDTO(totalPrice, totalVAT);
        this.itemList = itemList;
    }
}
