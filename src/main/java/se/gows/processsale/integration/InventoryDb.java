package se.gows.processsale.integration;

import java.util.ArrayList;

/**
 * Singelton class that represents the inventory database.
 * When initialized it adds the three test products "mjölk", "smör" and "choklad" to the inventory.
 */
class InventoryDb {
    private static final InventoryDb instance = new InventoryDb();
    private ArrayList<InventoryItem> items;
    private InventoryDb(){
        items = new ArrayList<>();
        
        items.add(new InventoryItem(1, "Mjölk", 14.90, 0.25));
        items.add(new InventoryItem(2, "Smör", 39.90, 0.25));
        items.add(new InventoryItem(3, "Choklad", 9.90, 0.25));
    };
    /**
     * Public method that returns the only instance of this class.
     * @return instance - the only instance of this class.
     */
    public static InventoryDb getInstance(){return instance;}
    /**
     * Public method that returns a list of the inventory items.
     * @return items - a list of all items present in inventory.
     */
    public ArrayList<InventoryItem> getItems(){return items;}
}
