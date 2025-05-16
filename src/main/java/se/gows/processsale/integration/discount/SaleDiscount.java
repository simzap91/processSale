package se.gows.processsale.integration.discount;

import se.gows.processsale.DTO.SaleDTO;
import se.gows.processsale.model.Amount;

public class SaleDiscount implements DiscountCalculator {

    private double totalPriceLowLimit = 200; 
    private double discountRate = 0.1;

    public Amount getDiscount(SaleDTO sale) {
        double totalPrice = sale.getSaleSums().getTotalPrice().getValue();
        if (totalPrice > totalPriceLowLimit) {
            double discountedPrice = totalPrice * (1 - discountRate);
            return new Amount(discountedPrice);
        }
        return sale.getSaleSums().getTotalPrice();
    }
}
