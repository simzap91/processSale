package se.gows.processsale.DTO;

import se.gows.processsale.model.Amount;
import se.gows.processsale.model.CustomerId;

public class DiscountRequestDTO {

    private CustomerId customerId;
    private RegisteredItemDTO[] itemList;
    private Amount totalPrice;

    public DiscountRequestDTO(CustomerId customerId, RegisteredItemDTO[] itemList, Amount totalPrice){
        this.customerId = customerId;
        this.itemList = itemList;
        this.totalPrice = totalPrice;
    }
    public CustomerId getCustomerId() {return customerId;}
    public RegisteredItemDTO[] getItemList(){return itemList;}
    public Amount getTotalPrice(){return totalPrice;}
}
