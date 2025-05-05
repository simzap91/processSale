package se.gows.processsale.DTO;

/**
 * Item DTO. Holds information about item.
 * @param itemID Unique item identifier
 * @param itemDescription name of item, e.g. "Smör"
 * @param price price of item (SEK)
 * @param vatRate item VAT rate (decimal)
 */
public class ItemDTO {
    private int itemID;
    private String itemDescription;
    private double price;
    private double vatRate;

    public ItemDTO(int itemID, String itemDescription, double price, double vatRate) {
        this.itemID = itemID;
        this.itemDescription = itemDescription;
        this.price = price;
        this.vatRate = vatRate;
    }

    public int getItemID() {return this.itemID;}
    public String getItemDescription() {return this.itemDescription;}
    public double getPrice() {return this.price;}
    public double getVatRate() {return this.vatRate;}
}
