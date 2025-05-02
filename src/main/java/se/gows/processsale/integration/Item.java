package se.gows.processsale.integration;

import se.gows.processsale.DTO.ItemDTO;

public class Item {
    public int itemID;
    public String description;
    public double price;
    public double vatRate;
    public int inventoryQuantity;
/**
 * Item object, containing all information about an item, which is stored in the inventoryDB.
 * @param itemID ID for the item
 * @param description the name of the item
 * @param price the price for the item
 * @param vatRate the Vat-rate for the item
 * @param inventoryQuantity the current quantity of the item in stock
 */
    public Item (int itemID, String description, double price, double vatRate, int inventoryQuantity){
        this.itemID = itemID;
        this.description = description;
        this.price = price;
        this.vatRate = vatRate;
        this.inventoryQuantity = inventoryQuantity;
    }
    /**
     * Public method that creates a ItemDTO 
     * @return ItemDTO
     */
    public ItemDTO createItemDTO() {
        ItemDTO dto = new ItemDTO(this.itemID, this.description, this.price, this.vatRate);
        return dto;
    }
    /**
     * Updates the quantity of a given item within the inventoryDB.
     * @param quantity
     */
    public void updateInventoryStatus(int quantity){
        this.inventoryQuantity -= quantity;
    }
}
