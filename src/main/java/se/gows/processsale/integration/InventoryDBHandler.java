package se.gows.processsale.integration;

import se.gows.processsale.DTO.*;
import se.gows.processsale.integration.inventory.InventoryDb;
import se.gows.processsale.integration.inventory.InventoryItem;

/**     
 * Public handler that communicates with the external inventory data base.
 */
public class InventoryDBHandler {
    private InventoryDb inventoryDb = InventoryDb.getInstance();
    
    /**
     * Public method that fetches item from inventoryDB.
     * Id 404 represent a data base connection error.
     * @param itemID unique identifier used to find item in DB
     * @return fetched item as ItemDTO. If no item ID matches the methods returns null.
     */
    public ItemDTO fetchItemFromInventory(int itemID) throws ItemIdNotFoundException, DatabaseFailureException {
        if (itemID == 404) {
            throw new DatabaseFailureException();
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
     * @param itemList list containing all registered items in sale.
     */
    public void updateInventory(RegisteredItemDTO[] itemList){};
}