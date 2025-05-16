package se.gows.processsale.integration.discount;

import se.gows.processsale.DTO.SaleDTO;
import se.gows.processsale.model.Amount;
import se.gows.processsale.model.CustomerId;

public class CustomerDiscount implements DiscountCalculator {

    private CustomerId[] memberCustomerIDs = {new CustomerId(1),new CustomerId(2), new CustomerId(3)};
    private double discountRate = 0.15;
/**
 * Calculates a discounted price from sale when given a customer ID that is eligable for a discount
 * @param sale SaleDTO containing all information about the sale including customer ID.
 * @return a new discounted price.
 */
    public Amount getDiscount(SaleDTO sale){
        double discountedPrice = sale.getSaleSums().getTotalPrice().getValue();

        for (CustomerId memberId : memberCustomerIDs) {
            if (memberId.isEqual(sale.getCustomerId().getId())){
                discountedPrice *= (1 - discountRate);
            }
        }
        return new Amount(discountedPrice);
    }

}
