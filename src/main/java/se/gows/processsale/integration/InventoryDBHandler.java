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

            // 3) Fetch item as list of Strings
            String[] parts = fetchItemAsList(itemInInventory, itemID);

            // If item sucessfully received
            if (parts != null) {

                // 4) Fetch remaining item attributes from invItem
                String itemDesc = parts[1].trim();
                double price = Double.parseDouble(parts[2].trim());
                double itemVat = Double.parseDouble(parts[3].trim());

                // 5) Create new ItemDTO
                scannedItem = new ItemDTO(itemID, itemDesc, price, itemVat);

                // 6) Item is found -> break loop
                break;
            }
        }
        return scannedItem;
    }
    private String[] fetchItemAsList(String dbItem, int itemID){

        String[] outputParts = null;

        // 1) Split the invItem string on commas
        String[] parts = dbItem.split(",");
        // 2) Parse invItem itemID as int
        int invItemID = Integer.parseInt(parts[0]);

        if (invItemID == itemID) {
            outputParts = parts;
        }
        return outputParts;
    }
}
