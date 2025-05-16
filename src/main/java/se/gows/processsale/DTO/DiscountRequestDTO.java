package se.gows.processsale.DTO;

import se.gows.processsale.model.Amount;
import se.gows.processsale.model.CustomerId;

public class DiscountRequestDTO {

    private CustomerId customerId;
    private RegisteredItemDTO[] purchasedItems;
    private Amount totalPrice;

    public DiscountRequestDTO(CustomerId customerId, RegisteredItemDTO[] purchasedItems, Amount totalPrice){
        this.customerId = customerId;
        this.purchasedItems = purchasedItems;
        this.totalPrice = totalPrice;
    }
    public CustomerId getCustomerId() {return customerId;}
    public RegisteredItemDTO[] getPurchasedItems(){return purchasedItems;}
    public Amount getTotalPrice(){return totalPrice;}
}
