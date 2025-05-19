package se.gows.processsale.DTO;

import se.gows.processsale.utils.Amount;

/**
 * Sale DTO. Holds information about a sale.
 * @param saleSums contains total sum, total VAT and total sum inc. VAT of the sale
 * @param itemList list with purchased items
 */
public class SaleDTO {
    private SumDTO saleSums;
    private RegisteredItemDTO[] itemList;

    public SaleDTO (Amount totalPrice, Amount totalVAT, RegisteredItemDTO[] itemList){
        this.saleSums = new SumDTO(totalPrice, totalVAT);
        this.itemList = itemList;
    }

    public SumDTO getSaleSums() {return this.saleSums;}
    public RegisteredItemDTO[] getItemList() {return this.itemList;}
}
