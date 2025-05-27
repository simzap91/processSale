package se.gows.processsale.DTO;

import se.gows.processsale.utils.Amount;

/**
 * Sale DTO. Holds information about an ended sale.
 */
public class SaleDTO {
    private SumDTO saleSums;
    private RegisteredItemDTO[] itemList;

    /**
     * @param totalPrice Total price of the sale (excl. vat)
     * @param totalVAT Total vat sum of the sale
     * @param itemList A list with all purchased items
     */
    public SaleDTO (Amount totalPrice, Amount totalVAT, RegisteredItemDTO[] itemList){
        this.saleSums = new SumDTO(totalPrice, totalVAT);
        this.itemList = itemList;
    }

    public SumDTO getSaleSums() {return this.saleSums;}
    public RegisteredItemDTO[] getItemList() {return this.itemList;}
}
