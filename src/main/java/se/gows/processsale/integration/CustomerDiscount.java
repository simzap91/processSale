package se.gows.processsale.integration;

import se.gows.processsale.DTO.RegisteredItemDTO;
import se.gows.processsale.model.Amount;

public class CustomerDiscount implements DiscountCalculator {

    private int[] memberCustomerIDs = {1,2,3,4,5};
    private double discountRate = 0.15;

    public Amount getDiscount(int customerID, RegisteredItemDTO[] purchasedItems, Amount totalPrice){
        double discountedPrice = totalPrice.getValue();

        for (int memberID : memberCustomerIDs) {
            if (memberID == customerID) {
                discountedPrice *= (1 - discountRate);
            }
        }
        return new Amount(discountedPrice);
    }

}
