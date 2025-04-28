package se.gows.processsale.DTO;

public class ItemDTO {
    int itemID;
    String itemDescription;
    double price;
    int vatRate;

    public ItemDTO(int itemID, String itemDescription, double price, int vatRate) {
        this.itemID = itemID;
        this.itemDescription = itemDescription;
        this.price = price;
        this.vatRate = vatRate;
    }
}
