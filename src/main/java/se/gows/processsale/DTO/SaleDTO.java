package se.gows.processsale.DTO;

import java.time.LocalDateTime;

/**
 * Sale DTO. Holds information about a sale.
 * @param timeOfSale time when sale started
 * @param saleSums contains total sum, total VAT and total sum inc. VAT of the sale
 * @param itemList list with purchased items
 */
public class SaleDTO {
    private LocalDateTime timeOfSale;
    private SumDTO saleSums;
    private RegisteredItemDTO[] itemList;

    public SaleDTO (LocalDateTime timeOfSale, double totalPrice, double totalVAT, RegisteredItemDTO[] itemList){
        this.timeOfSale = timeOfSale;
        this.saleSums = new SumDTO(totalPrice, totalVAT);
        this.itemList = itemList;
    }

    public LocalDateTime getTimeOfSale() {return this.timeOfSale;}
    public SumDTO getSaleSums() {return this.saleSums;}
    public RegisteredItemDTO[] getItemList() {return this.itemList;}
}
