package se.gows.processsale.integration;

import se.gows.processsale.DTO.RegisteredItemDTO;
import se.gows.processsale.integration.discount.*;
import se.gows.processsale.model.Amount;
import se.gows.processsale.utils.DiscountTypes;

/**     
 * Public handler that communicates with the external discount database.
 */
public class DiscountDBHandler {

    /**
     * Public method that fetch all requested and relevant discounts and calculates a new reduced total price.
     * @param DiscountTypes The different types of discounts, stores as an array
     * @param customerID Customer ID.
     * @param purchasedItems A list of all registered items.
     * @param totalPrice The total price (excl. Vat) of all items in the current sale.
     * @return Updated totalprice. Returns unchanged if no discount apply.
     *  
     */
    public Amount getDiscountedPrice(DiscountTypes[] discountTypes, int customerID, RegisteredItemDTO[] purchasedItems, Amount totalPrice){

        for (DiscountTypes type : discountTypes) {
            if (type == DiscountTypes.ITEMS){
                ItemDiscount discountCalculator = new ItemDiscount();
                totalPrice = discountCalculator.getDiscount(customerID, purchasedItems, totalPrice);
            } else if (type == DiscountTypes.SALE){
                SaleDiscount discountCalculator = new SaleDiscount();
                totalPrice = discountCalculator.getDiscount(customerID, purchasedItems, totalPrice);
            } else if (type == DiscountTypes.CUSTOMER) {
                CustomerDiscount discountCalculator = new CustomerDiscount();
                totalPrice = discountCalculator.getDiscount(customerID, purchasedItems, totalPrice);
            }
        }
        return totalPrice;
    }
}
