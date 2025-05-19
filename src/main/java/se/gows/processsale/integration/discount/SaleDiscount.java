package se.gows.processsale.integration.discount;

import se.gows.processsale.DTO.DiscountRequestDTO;
import se.gows.processsale.utils.Amount;

/**
 * Calculates a reduced total sale price using a fixed discount rate. The discount applies if the total sale price is greater than 200.
 * The class implements the DiscountCalculator interface.
 */
public class SaleDiscount implements DiscountCalculator {

    private double totalPriceLowLimit = 200; 
    private double discountRate = 0.1;
    
    /**
    * Calculates a discounted price from sale when given a total price higher than the discounts lowest limit.
    * @param discountRequest DiscountRequestDTO containing all information required for applying a discount, including the sales total price.
    * @return a new discounted price.
    */
    public Amount getDiscount(DiscountRequestDTO discountRequest) {
        double totalPrice = discountRequest.getTotalPrice().getValue();
        if (totalPrice > totalPriceLowLimit) {
            double discountedPrice = totalPrice * (1 - discountRate);
            return new Amount(discountedPrice);
        }
        return discountRequest.getTotalPrice();
    }
}
