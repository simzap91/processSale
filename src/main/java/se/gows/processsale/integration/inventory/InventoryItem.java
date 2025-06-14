package se.gows.processsale.integration.inventory;

import se.gows.processsale.DTO.ItemDTO;

/**
 * Inventory item object, containing all information about an item, which is stored in the inventory database.
 */
public class InventoryItem {
    private int itemID;
    private String description;
    private double price;
    private double vatRate;
    
    /**
     * @param itemID ID for the item
     * @param description the name of the item
     * @param price the price for the item
     * @param vatRate the Vat-rate for the item
     */
    public InventoryItem (int itemID, String description, double price, double vatRate){
        this.itemID = itemID;
        this.description = description;
        this.price = price;
        this.vatRate = vatRate;
    }
    /**
     * Public method that creates a new ItemDTO with the information of this inventory item.
     * 
     * @return ItemDTO
     */
    public ItemDTO createItemDTO() {
        ItemDTO dto = new ItemDTO(this.itemID, this.description, this.price, this.vatRate);
        return dto;
    }

    public int getID(){return this.itemID;}
}
