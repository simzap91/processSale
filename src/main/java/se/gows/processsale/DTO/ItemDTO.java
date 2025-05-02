package se.gows.processsale.DTO;

/**
     * Item DTO. Holds information about item.
     * @param itemID Unique item identifier
     * @param itemDescription name of item, e.g. "Smör"
     * @param price price of item (SEK)
     * @param vatRate item VAT rate (decimal)
     */
public class ItemDTO {
    public int itemID;
    public String itemDescription;
    public double price;
    public double vatRate;

    public ItemDTO(int itemID, String itemDescription, double price, double vatRate) {
        this.itemID = itemID;
        this.itemDescription = itemDescription;
        this.price = price;
        this.vatRate = vatRate;
    }
}
