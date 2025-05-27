package se.gows.processsale.integration.discount;

import se.gows.processsale.DTO.DiscountRequestDTO;
import se.gows.processsale.utils.Amount;

/**
 * Calculates a reduced total sale price using a fixed discount rate. The discount applies if the customer is member.
 * The class implements the DiscountCalculator interface.
 */
public class CustomerDiscount implements DiscountCalculator {
    
    private int[] memberCustomerIDs = {1, 2, 3};
    private double discountRate = 0.15;

    /**
     * Calculates a discounted price for sale when given a customer ID that is eligable for a discount
     * 
     * @param discountRequest DiscountRequestDTO containing all information required for applying a discount, including customer ID.
     * @return a new discounted price.
     */
    public Amount getDiscount(DiscountRequestDTO discountRequest){
        double discountedPrice = discountRequest.getTotalPrice().getValue();

        for (int memberId : memberCustomerIDs) {
            if (memberId == discountRequest.getCustomerId()) {
                discountedPrice *= (1 - discountRate);
            }
        }
        return new Amount(discountedPrice);
    }

}
