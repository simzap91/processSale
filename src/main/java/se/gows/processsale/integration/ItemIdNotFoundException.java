package se.gows.processsale.integration;

/**
 * Thrown when search is unsuccessful in finding a specified item ID
 */
public class ItemIdNotFoundException extends Exception {
    private int itemIdNotFound;

    /**
     * Creates a new instance of itemID together with a message clarifying which item ID the search failed to find.
    * @param itemID the item ID that was not found
     */
    public ItemIdNotFoundException(int itemId){
        super("Item with id " + itemId + " can't be found in inventory.\n");
        this.itemIdNotFound = itemId;
    }
    /**
     * @return The item ID that could not be found.
     */    
    public int getItemIdNotFound(){return this.itemIdNotFound;}
}
