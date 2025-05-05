package se.gows.processsale.integration;

import se.gows.processsale.DTO.ItemDTO;

/**
 * Item object, containing all information about an item, which is stored in the inventoryDB.
 * @param itemID ID for the item
 * @param description the name of the item
 * @param price the price for the item
 * @param vatRate the Vat-rate for the item
 * @param inventoryQuantity the current quantity of the item in stock
 */
public class Item {
    private int itemID;
    private String description;
    private double price;
    private double vatRate;
    
    public Item (int itemID, String description, double price, double vatRate){
        this.itemID = itemID;
        this.description = description;
        this.price = price;
        this.vatRate = vatRate;
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
     */
    public void updateInventoryStatus(){};

    public int getID(){
        return this.itemID;
    }
}
