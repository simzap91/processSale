package se.gows.processsale.integration;

import se.gows.processsale.DTO.ItemDTO;

public class Item {
    public int itemID;
    public String description;
    public double price;
    public double vatRate;
    public int inventoryQuantity;

    public Item (int itemID, String description, double price, double vatRate, int inventoryQuantity){
        this.itemID = itemID;
        this.description = description;
        this.price = price;
        this.vatRate = vatRate;
        this.inventoryQuantity = inventoryQuantity;
    }

    public ItemDTO createItemDTO() {
        ItemDTO dto = new ItemDTO(this.itemID, this.description, this.price, this.vatRate);
        return dto;
    }
}
