package se.gows.processsale.DTO;

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
