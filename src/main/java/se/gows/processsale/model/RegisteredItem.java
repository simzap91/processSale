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
        this.quantity = quantity;
    }

    public void setDiscountedPrice() {

        // Get discount rate as decimal number
        this.discountRate = discountRate/100;

        // Calculate discounted price
        this.discountedPrice = item.price * (1 - discountRate);
    }
}
