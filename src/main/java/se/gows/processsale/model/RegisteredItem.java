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

    public void setQuantity(int quantity) {
        this.quantity += quantity;
    }

    public boolean idsAreEqual(int itemID){
        return (itemID == this.item.itemID);
    }

    public double getPriceOfMultipleItems(int quantity){
        return this.item.price * quantity;
    }

    public double getVatOfMultipleItems(int quantity){
        return this.item.price * this.item.vatRate * quantity;
    }
}
