package se.gows.processsale.DTO;

import se.gows.processsale.utils.Amount;

public class DiscountRequestDTO {

    private int customerId;
    private RegisteredItemDTO[] purchasedItems;
    private Amount totalPrice;

    public DiscountRequestDTO(int customerId, RegisteredItemDTO[] purchasedItems, Amount totalPrice){
        this.customerId = customerId;
        this.purchasedItems = purchasedItems;
        this.totalPrice = totalPrice;
    }
    public int getCustomerId() {return customerId;}
    public RegisteredItemDTO[] getPurchasedItems(){return purchasedItems;}
    public Amount getTotalPrice(){return totalPrice;}
}
