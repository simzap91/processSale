package se.gows.processsale.integration;

import se.gows.processsale.DTO.*;
import se.gows.processsale.integration.inventory.*;

/**     
 * Public handler that communicates with the external inventory data base.
 */
public class InventoryDBHandler {
    private InventoryDb inventoryDb = InventoryDb.getInstance();
    
    /**
     * Public method that fetches item from inventoryDB. If the itemId is not present in inventory 
     * an ItemIdNotFoundException is thrown.
     * Id 404 represent data base not running. If this problem occurs a DatabaseNotRunningException is thrown.
     * 
     * @param itemID unique identifier used to find item in DB
     * @return fetched item as ItemDTO. If no item ID matches the methods returns null.
     * @throws ItemIdNotFoundException if item id can't be found in the inventory database
     * @throws DatabaseNotRunningException if the database is not running
     */
    public ItemDTO fetchItemFromInventory(int itemID) throws ItemIdNotFoundException, DatabaseNotRunningException {
        if (itemID == 404) {
            throw new DatabaseNotRunningException();
        }
        for (InventoryItem invItem : inventoryDb.getItems()) {
            if (invItem.getID() == itemID) {
                return invItem.createItemDTO();
            }
        }
        throw new ItemIdNotFoundException(itemID);
    }

    /**
     * Updates the inventory status of each item in itemList.
     * 
     * @param itemList list containing all registered items in sale.
     */
    public void updateInventory(RegisteredItemDTO[] itemList){};
}