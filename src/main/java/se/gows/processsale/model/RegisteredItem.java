package se.gows.processsale.model;

import se.gows.processsale.DTO.ItemDTO;

/**
 * RegisteredItem object, containing information about an item, stored as a ItemDTO,
 * combined with the quantity of the item.
 * @param item information about the item as a DTO
 * @param quantity quantity of the item
 */
public class RegisteredItem {
    private ItemDTO item;
    private int quantity;

    public RegisteredItem(ItemDTO item, int quantity){
        this.item = item;
        this.quantity = quantity;
    }
    /**
     * Public method that sets the quantity of a given RegisteredItem.
     * @param quantity the new quantity for a item
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    /**
     * Public method that gets the quantity of a given RegisteredItem.
     * @param quantity the current quantity for a item
     */
    public int getQuantity() {
       return this.quantity;
    }

    public ItemDTO getItem() {
        return this.item;
     }

    /**
     * Public method that check is a RegisteredItem is equal to a given itemID.
     * @param itemID ID of an item
     * @return true or false
     */
    public boolean idsAreEqual(int itemID){
        return (itemID == this.item.getItemID());
    }
    /**
     * Public method that returns the price of a given item times its quantity
     * @param quantity the quantity for a item
     * @return item price times quantity
     */
    public double getTotalPriceOfItemQuantity(){
        return this.item.getPrice() * this.quantity;
    }
    /**
     * Public method that returns the total Vat of a given item times its quantity
     * @param quantity the quantity for a item
     * @return total Vat of given item times its quantity
     */
    public double getTotalVatOfItemQuantity(){
        return this.item.getPrice() * this.item.getVatRate() * this.quantity;
    }
}
