package se.gows.processsale.integration;

import se.gows.processsale.DTO.ItemDTO;

public class InventoryDBHandler {

    // TestDB
    String[] testDB = {"1, mjölk, 14.9, 20", "2, smör, 39.9, 20"};

    ItemDTO fetchItemFromDB(int itemID) {

        // Fetch data from testDB
        // 1) Split the first string on commas
        String[] parts = testDB[0].split(",");
        String testDesc = parts[1].trim();
        double testPrice = Double.parseDouble(parts[2].trim());
        int testVAT = Integer.parseInt(parts[3].trim());

        // Create new ItemDTO
        ItemDTO scannedItem = new ItemDTO(itemID, testDesc, testPrice, testVAT);

        // Return scannedItem
        return scannedItem;
    }
}
