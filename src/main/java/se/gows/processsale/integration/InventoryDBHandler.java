package se.gows.processsale.integration;

import se.gows.processsale.DTO.ItemDTO;
import se.gows.processsale.model.RegisteredItem;

import java.util.ArrayList;

public class InventoryDBHandler {

    // Inventory DB - Format: {itemID, itemDescription, price, VAT (decimal), invStatus}
    String[] inventoryDB = {"1, Mjölk, 14.90, 0.25, 500", "2, Smör, 39.90, 0.25, 500"};
    
    public ItemDTO fetchItemFromDB(int itemID) {

        // 1) Declare new itemDTO
        ItemDTO scannedItem = null;

        // 2) Step through the items in inventoryDB 
        for (String itemInInventory : inventoryDB) {

            // 3) Compare list item IDs with scanned item ID
            if (readListItemID(itemInInventory) == itemID){
                
                // 4) If item found -> Fetch item as list of Strings
                String[] parts = fetchItemAsList(itemInInventory);

                // 5) Fetch item attributes from invItem
                String itemDesc = parts[1].trim();
                double price = Double.parseDouble(parts[2].trim());
                double itemVat = Double.parseDouble(parts[3].trim());

                // 6) Create new ItemDTO
                scannedItem = new ItemDTO(itemID, itemDesc, price, itemVat);

                // 7) Item is found -> break loop
                break;
            }
        }
        return scannedItem;
    }

    public void updateInventoryDB(ArrayList<RegisteredItem> itemList) {

        // Step through all registered items in itemList
        for (RegisteredItem item : itemList) {

            int itemID = item.item.itemID;
            int itemCount = item.quantity;

            // Update item invStatus in DB
            updateItemInvStatus(itemID, itemCount);
        }
    }

    private void updateItemInvStatus(int itemID, int itemCount) {

        // 1) Step through the items in inventoryDB 
        for (String invItem : inventoryDB) {

            // 3) Compare list item IDs with scanned item ID
            if (readListItemID(invItem) == itemID){

                // 2) Fetch item as list of Strings
                String[] parts = fetchItemAsList(invItem);

                // 3) Fetch item invStatus
                int invStatus = Integer.parseInt(parts[4].trim());

                // 4) Calculate new invStatus
                int updatedInvStatus = invStatus - itemCount;

                // 5) Replace invStatus with updatedInvStatus (convert int to string)
                parts[parts.length - 1] = String.valueOf(updatedInvStatus).trim();

                // 6) Join the parts back into a single comma-separated string
                String updatedItemString = String.join(",", parts);

                // 7) Put updated item back in inventoryDB
                invItem = updatedItemString;
                
                // 8) All done -> break loop
                break;
            }
        }
    }

    /*
     * Takes a 
     */
    private String[] fetchItemAsList(String dbItem){

        // Split dbItem string on commas
        String[] parts = dbItem.split(",");

        return parts;
    }

    private int readListItemID(String invItem){
        // Split invItem on commas
        String[] parts = invItem.split(",");

        // Get list item ID
        int listItemId = Integer.parseInt(parts[0].trim());

        // Return list item ID
        return listItemId;
    }
}