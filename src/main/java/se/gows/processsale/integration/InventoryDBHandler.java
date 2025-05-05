package se.gows.processsale.integration;

import se.gows.processsale.DTO.ItemDTO;
import se.gows.processsale.model.RegisteredItem;

/**     
 * Public handler that communicates with external inventory data base.
 * In this case the data base is declared internally and contains two test items, mjölk and smör, of the type Item.
 */
public class InventoryDBHandler {

    /**
     * Inventory data base.
     */
    private Item mjölk = new Item(1, "Mjölk", 14.90, 0.25);
    private Item smör = new Item(2, "Smör", 39.90, 0.25);
    private Item[] inventoryDB = {mjölk, smör};
    
    /**
     * Public method that fetches item from inventoryDB.
     * @param itemID unique identifier used to find item in DB
     * @return fetched item as ItemDTO. If no item ID matches the methods returns null.
     */
    public ItemDTO fetchItemFromDB(int itemID) {
        ItemDTO scannedItem = null;

        for (Item item : inventoryDB) {
            if (idsAreEqual(itemID,item.getID())) {
                scannedItem = item.createItemDTO();
            }
        }
        return scannedItem;
    }

    /**
     * Public method that updates the inventory status for each item after a sale.
     * @param itemList list containing all registered items in sale.
     */
    public void updateInventoryDB(RegisteredItem[] itemList) {
        for (RegisteredItem regItem : itemList) {

            int itemID = regItem.getItem().getItemID();
            int itemCount = regItem.getQuantity();
            updateItemInvStatus(itemID, itemCount);
        }
        //System.out.println("Inventory updated.");
    }

    private void updateItemInvStatus(int itemID, int itemCount) {
        for (Item item : inventoryDB) {
            if (idsAreEqual(itemID,item.getID())) {
                item.updateInventoryStatus();
            }
        }
    }
    public boolean idsAreEqual(int itemID, int comparedID){
        return (itemID == comparedID);
    }
}