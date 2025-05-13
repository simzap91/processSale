package se.gows.processsale.integration;

public class ItemIdNotFoundException extends Exception {
    private int itemIdNotFound;
    public ItemIdNotFoundException(int itemId){
        super("Item with id " + itemId + " can't be found in inventory.\n");
        this.itemIdNotFound = itemId;
    }
    public int getItemIdNotFound(){return this.itemIdNotFound;}
}
