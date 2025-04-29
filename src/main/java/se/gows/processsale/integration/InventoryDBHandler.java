package se.gows.processsale.integration;

import se.gows.processsale.DTO.ItemDTO;
import se.gows.processsale.model.RegisteredItem;

import java.util.ArrayList;

public class InventoryDBHandler {

    // Inventory DB - Format: {itemID, itemDescription, price, VAT (decimal), invStatus}
    String[] inventoryDB = {"1, Mjölk, 14.90, 0.25, 500", "2, Smör, 39.90, 0.25, 500"};
    
    /* 
     * Fetches item from inventoryDB.
     * Fetches item with ID that matches passed itemID.
     * If no item ID matches the methods returns null.
     */
    public ItemDTO fetchItemFromDB(int itemID) {

        // Fetch inventory item as list of strings
        String[] invItem = fetchInventoryItem(itemID);

        // Create new itemDTO
        ItemDTO scannedItem = createItemDTO(invItem);

        return scannedItem;
    }

    /* 
     * Updates the inventory status for each item after a sale.
     */
    public void updateInventoryDB(ArrayList<RegisteredItem> itemList) {

        // Step through all registered items in itemList
        for (RegisteredItem item : itemList) {

            int itemID = item.item.itemID;
            int itemCount = item.quantity;

            // Update item invStatus in DB
            updateItemInvStatus(itemID, itemCount);
        }
    }

    /* 
     * Calculate and updates the inventory status for a specific item in inventoryDB.
     */
    private void updateItemInvStatus(int itemID, int itemCount) {

        // 1) Step through the items in inventoryDB 
        for (String invItem : inventoryDB) {

            // 3) Compare list item IDs with scanned item ID
            if (readListItemID(invItem) == itemID){

                // 2) Fetch item as list of Strings
                String[] parts = getItemAsListOfStrings(invItem);

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
     * Takes an itemID, finds the item in invetoryDB, convert the item to a list of strings and return that list.
     * If the item is not present in iventoryDB, the method returns null.
     */
    private String[] fetchInventoryItem(int itemID){

        // Fetch item String from inventory DB
        String invItem = fetchItemStringFromDB(itemID);

        if (invItem != null) {
            // Convert invItem to list
            String[] parts = getItemAsListOfStrings(invItem);

            return parts;
        }
        return null;
    }

    /*
     * Takes an itemID, finds the item string in invetoryDB and returns it.
     * If the item is not present in iventoryDB, the method returns null.
     */
    private String fetchItemStringFromDB(int itemID){

        // Step through the items in inventoryDB 
        for (String invItem : inventoryDB) {
            if (readListItemID(invItem) == itemID) {
                return invItem;
            }
        }
        return null;
    }

    /**
     * Converts item string to a list of strings.
     * 
     * @param invItem  a comma-separated string, e.g. "1, Mjölk, 2, ..."
     * @return         a list of strings (e.g. ["1", "Mjölk", "2", ...])
     * @throws 
     */
    private String[] getItemAsListOfStrings(String invItem){
        // Throw exception if invItem is null
        if (invItem == null) {
            throw new IllegalArgumentException("Input string is null");
        }

        // Split invItem string on commas
        String[] parts = invItem.split(",");
        return parts;
    }

    /**
     * Parses the integer before the first comma.
     * 
     * @param invItem  a comma-separated string, e.g. "1, Mjölk, 2"
     * @return          the parsed int (the “1” in the example)
     * @throws IllegalArgumentException if invItem is null, empty, or does not start with a valid int
     */
    private int readListItemID(String invItem) {
        // Throw exception if invItem is null
        if (invItem == null) {
            throw new IllegalArgumentException("Input string is null");
        }

        // 1) Split invItem into two parts, with the expected ID as first part
        String[] parts = invItem.split(",", 2);

        // 2) If first part is empty, throw exception
        if (parts.length == 0 || parts[0].trim().isEmpty()) {
            throw new IllegalArgumentException("No value before the first comma in: \"" + invItem + "\"");
        }

        // 3) Parse first part (expected ID) and return as an int
        String firstPart = parts[0].trim();
        try {
            return Integer.parseInt(firstPart);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid integer value: \"" + firstPart + "\"", e);
        }
    }

    /**
     * Convert the item (as list of strings) to ItemDTO.
     * 
     * @param parts  a comma-separated string, e.g. "1, Mjölk, 2"
     * @return        a new itemDTO
     * @throws IllegalArgumentException if invItem is null, empty, or does not start with a valid int
     */
    private ItemDTO createItemDTO(String[] parts){

        // 1) Convert item attributes from invItem
        int itemID = Integer.parseInt(parts[0].trim());
        String itemDesc = parts[1].trim();
        double price = Double.parseDouble(parts[2].trim());
        double itemVat = Double.parseDouble(parts[3].trim());

        // 2) Convert to ItemDTO
        ItemDTO newItemDTO = new ItemDTO(itemID, itemDesc, price, itemVat);

        return newItemDTO;
    }
}