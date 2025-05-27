package se.gows.processsale.DTO;

import se.gows.processsale.utils.Amount;

/**
 * Holds information relevant for calculating a discount.
 */
public class DiscountRequestDTO {
    private int customerId;
    private RegisteredItemDTO[] purchasedItems;
    private Amount totalPrice;

    /**
     * @param customerId Customers member id
     * @param purchasedItems A list with all items purchased
     * @param totalPrice Total price of the sale before discount
     */
    public DiscountRequestDTO(int customerId, RegisteredItemDTO[] purchasedItems, Amount totalPrice){
        this.customerId = customerId;
        this.purchasedItems = purchasedItems;
        this.totalPrice = totalPrice;
    }
    
    public int getCustomerId() {return customerId;}
    public RegisteredItemDTO[] getPurchasedItems(){return purchasedItems;}
    public Amount getTotalPrice(){return totalPrice;}
}
