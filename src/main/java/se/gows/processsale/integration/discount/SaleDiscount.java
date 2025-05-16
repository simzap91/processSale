package se.gows.processsale.integration.discount;

import se.gows.processsale.DTO.DiscountRequestDTO;
import se.gows.processsale.model.Amount;

public class SaleDiscount implements DiscountCalculator {

    private double totalPriceLowLimit = 200; 
    private double discountRate = 0.1;

    public Amount getDiscount(DiscountRequestDTO discRequest) {
        double totalPrice = discRequest.getTotalPrice().getValue();
        if (totalPrice > totalPriceLowLimit) {
            double discountedPrice = totalPrice * (1 - discountRate);
            return new Amount(discountedPrice);
        }
        return discRequest.getTotalPrice();
    }
}
