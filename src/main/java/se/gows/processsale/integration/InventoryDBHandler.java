package se.gows.processsale.integration;

import se.gows.processsale.DTO.ItemDTO;
import se.gows.processsale.model.RegisteredItem;

import java.util.ArrayList;

public class InventoryDBHandler {

    Item mjölk = new Item(1, "Mjölk", 14.90, 0.25, 500);
    Item smör = new Item(2, "Smör", 39.90, 0.25, 500);
    Item[] inventoryDB = {mjölk, smör};
    
    /* 
     * Fetches item from inventoryDB.
     * Fetches item with ID that matches passed itemID.
     * If no item ID matches the methods returns null.
     */
    public ItemDTO fetchItemFromDB(int itemID) {
        ItemDTO scannedItem = null;

        for (Item item : inventoryDB) {
            if (item.itemID == itemID) {
                scannedItem = item.createItemDTO();
            }
        }
        return scannedItem;
    }

    /* 
     * Updates the inventory status for each item after a sale.
     */
    public void updateInventoryDB(ArrayList<RegisteredItem> itemList) {
        for (RegisteredItem regItem : itemList) {

            int itemID = regItem.item.itemID;
            int itemCount = regItem.quantity;
            updateItemInvStatus(itemID, itemCount);
        }
        System.out.println("Inventory updated.");
    }

    /* 
     * Calculate and updates the inventory status for a specific item in inventoryDB.
     */
    private void updateItemInvStatus(int itemID, int itemCount) {
        for (Item item : inventoryDB) {
            if (item.itemID == itemID) {
                item.updateInventoryStatus(itemCount);
            }
        }
    }
}