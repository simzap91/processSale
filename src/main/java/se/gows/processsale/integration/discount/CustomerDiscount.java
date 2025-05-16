package se.gows.processsale.integration.discount;

import se.gows.processsale.DTO.SaleDTO;
import se.gows.processsale.model.Amount;
import se.gows.processsale.model.CustomerId;

public class CustomerDiscount implements DiscountCalculator {

    private CustomerId[] memberCustomerIDs = {new CustomerId(1),new CustomerId(2), new CustomerId(3)};
    private double discountRate = 0.15;

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
