package se.gows.processsale.model;

import se.gows.processsale.DTO.ItemDTO;

public class RegisteredItem {
    public ItemDTO item;
    public int quantity;
    public double discountedPrice;
    public double discountRate;

    public RegisteredItem(ItemDTO item, int quantity){
        this.item = item;
        this.quantity = quantity;
        this.discountedPrice = 0;
        this.discountRate = 0;
    }
/**
 * Public method that sets the quantity of a given RegisteredItem.
 * @param quantity the new quantity for a item
 */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

/**
 * Public method that check is a RegisteredItem is equal to a given itemID.
 * @param itemID ID of an item
 * @return true or false
 */
    public boolean idsAreEqual(int itemID){
        return (itemID == this.item.itemID);
    }
/**
 * Public method that returns the price of a given item times its quantity
 * @param quantity the quantity for a item
 * @return item price times quantity
 */
    public double getPriceOfMultipleItems(int quantity){
        return this.item.price * quantity;
    }
/**
 * Public method that returns the total Vat of a given item times its quantity
 * @param quantity the quantity for a item
 * @return total Vat of given item times its quantity
 */
    public double getVatOfMultipleItems(int quantity){
        return this.item.price * this.item.vatRate * quantity;
    }
}
