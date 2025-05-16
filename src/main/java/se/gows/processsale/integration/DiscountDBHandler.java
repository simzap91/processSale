package se.gows.processsale.integration;

import java.util.Arrays;

import se.gows.processsale.DTO.SaleDTO;
import se.gows.processsale.integration.discount.*;
import se.gows.processsale.model.Amount;
import se.gows.processsale.utils.DiscountTypes;


/**     
 * Public handler that communicates with the external discount database.
 */
public class DiscountDBHandler {

    /**
     * Public method that fetch all requested and relevant discounts and calculates a new reduced total price.
     * @param discountTypes The different types of discounts, stores as an array
     * @param sale Contains information about the current sale.
     * @return Updated totalprice. Returns unchanged if no discount apply.
     *  
     */
    public Amount getDiscountedPrice(DiscountTypes[] discountTypes, SaleDTO sale){

        Amount totalPrice = sale.getSaleSums().getTotalPrice();

        if (Arrays.stream(discountTypes).anyMatch(n -> n == DiscountTypes.ITEMS)) {
            ItemDiscount discountCalculator = new ItemDiscount();
            totalPrice = discountCalculator.getDiscount(sale);
        }
        if (Arrays.stream(discountTypes).anyMatch(n -> n == DiscountTypes.SALE)) {
            SaleDiscount discountCalculator = new SaleDiscount();
            totalPrice = discountCalculator.getDiscount(sale);
        }
        if (Arrays.stream(discountTypes).anyMatch(n -> n == DiscountTypes.CUSTOMER)) {
            CustomerDiscount discountCalculator = new CustomerDiscount();
            totalPrice = discountCalculator.getDiscount(sale);
        }
        return totalPrice;
    }
}
