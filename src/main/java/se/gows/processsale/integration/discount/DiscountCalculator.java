package se.gows.processsale.integration.discount;

import se.gows.processsale.DTO.DiscountRequestDTO;
import se.gows.processsale.utils.Amount;

/**
 * Interface that all discount calculation classes implements.
 */
interface DiscountCalculator {

    /**
     * Method that takes a discount request and returns a discounted total price if any discount applies.
     *
     * @param discountRequest Contains all necessary information about the customer and the current sale used to calculate discount.
     * @return Amount object holding the updated total price.
     */
    Amount getDiscount(DiscountRequestDTO discountRequest);
}
