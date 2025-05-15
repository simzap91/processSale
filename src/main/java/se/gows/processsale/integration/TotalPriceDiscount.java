package se.gows.processsale.integration;

import se.gows.processsale.DTO.RegisteredItemDTO;
import se.gows.processsale.model.Amount;

public class TotalPriceDiscount implements DiscountCalculator {

    private double totalPriceLowLimit = 200; 
    private double discountRate = 0.1;

    public Amount getDiscount(int customerID, RegisteredItemDTO[] purchasedItems, Amount totalPrice) {
        if (totalPrice.getValue() > totalPriceLowLimit) {
            double discountedPrice = totalPrice.getValue() * (1 - discountRate);
            return new Amount(discountedPrice);
        }
        return totalPrice;
    }
}
