package se.gows.processsale.integration.Inventory;

import java.util.ArrayList;

public class InventoryDb {
    private static final InventoryDb instance = new InventoryDb();
    private ArrayList<InventoryItem> items;
    private InventoryDb(){
        items = new ArrayList<>();
        
        items.add(new InventoryItem(1, "Mjölk", 14.90, 0.25));
        items.add(new InventoryItem(2, "Smör", 39.90, 0.25));
        items.add(new InventoryItem(3, "Choklad", 9.90, 0.25));
    };
    public static InventoryDb getInstance(){return instance;}
    public ArrayList<InventoryItem> getItems(){return items;}
}
