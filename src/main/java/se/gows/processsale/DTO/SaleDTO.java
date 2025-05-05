package se.gows.processsale.DTO;

import java.time.LocalDateTime;

import se.gows.processsale.model.RegisteredItem;

/**
     * Sale DTO. Holds information about a sale.
     * @param timeOfSale time when sale started
     * @param saleSums contains total sum, total VAT and total sum inc. VAT of the sale
     * @param itemList list with purchased items
     */
public class SaleDTO {
    private LocalDateTime timeOfSale;
    private SumDTO saleSums;
    private RegisteredItem[] itemList;

    public SaleDTO (LocalDateTime timeOfSale, double totalPrice, double totalVAT, RegisteredItem[] itemList){
        this.timeOfSale = timeOfSale;
        this.saleSums = new SumDTO(totalPrice, totalVAT);
        this.itemList = itemList;
    }

    public LocalDateTime getTimeOfSale() {return this.timeOfSale;};
    public SumDTO getSaleSums() {return this.saleSums;};
    public RegisteredItem[] getItemList() {return this.itemList;};
}
