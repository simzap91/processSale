package se.gows.processsale.integration.discount;

import se.gows.processsale.DTO.DiscountRequestDTO;
import se.gows.processsale.model.Amount;
import se.gows.processsale.model.CustomerId;

public class CustomerDiscount implements DiscountCalculator {

    private CustomerId[] memberCustomerIDs = {new CustomerId(1),new CustomerId(2), new CustomerId(3)};
    private double discountRate = 0.15;
/**
 * Calculates a discounted price for sale when given a customer ID that is eligable for a discount
 * @param discountRequest DiscountRequestDTO containing all information required for applying a discount, including customer ID.
 * @return a new discounted price.
 */
    public Amount getDiscount(DiscountRequestDTO discountRequest){
        double discountedPrice = discountRequest.getTotalPrice().getValue();

        for (CustomerId memberId : memberCustomerIDs) {
            if (memberId.isEqual(discountRequest.getCustomerId())) {
                discountedPrice *= (1 - discountRate);
            }
        }
        return new Amount(discountedPrice);
    }

}
