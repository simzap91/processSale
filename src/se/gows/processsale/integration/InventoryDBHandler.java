package se.gows.processsale.integration;

import se.gows.processsale.DTO.ItemDTO;

public class InventoryDBHandler {

    /*
     * Item database for testing
     * Contains two items
     */
    String[] itemtDBTest = {"1, mjölk, 14.9, 20", "2, smör, 39.9, 20"};

    ItemDTO fetchItemFromDB(int itemID) {

        /*
         * Procedure in 2 steps that fetches item from itemtDBTest
         */
        // 1) Split the first string on commas
        String[] parts = itemtDBTest[0].split(",");

        // 2) Add item values to test variables
        String testDesc = parts[1].trim();
        double testPrice = Double.parseDouble(parts[2].trim());
        int testVAT = Integer.parseInt(parts[3].trim());

        /*
         * Create new ItemDTO from fetched item data
         */
        ItemDTO scannedItem = new ItemDTO(itemID, testDesc, testPrice, testVAT);

        return scannedItem;
    }
}
