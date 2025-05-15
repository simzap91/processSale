package se.gows.processsale.DTO;

/**
 * Item DTO that stores item data.
 */
public class ItemDTO {
    private int itemID;
    private String itemDescription;
    private double price;
    private double vatRate;

    /**
     * @param itemID Unique item identifier
     * @param itemDescription Name of item, e.g. "Smör"
     * @param price Price of item (SEK)
     * @param vatRate Item VAT rate (decimal)
     */
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
