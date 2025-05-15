package se.gows.processsale.integration;

import se.gows.processsale.DTO.RegisteredItemDTO;
import se.gows.processsale.model.Amount;
import se.gows.processsale.utils.DiscountTypes;

/**     
 * Public handler that communicates with external discount data base.
 * In this case the data base is declared internally and consists of a list with member IDs and lists/variables for three different discount types.
 */
public class DiscountDBHandler {

    /**
     * Public method that determines which type of discount should be applied on the sale and calculates the total discount.
     * @param DiscountTypes The different types of discounts, stores as an array
     * @param customerID the customers ID
     * @param purchasedItems a list of all items that are present in the current sale
     * @param totalPrice the total price (excl. Vat) of all items in the current sale
     * @return updated totalprice
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
            }
            else if (type == DiscountTypes.CUSTOMER) {
                CustomerDiscount discountCalculator = new CustomerDiscount();
                totalPrice = discountCalculator.getDiscount(customerID, purchasedItems, totalPrice);
            }
        }
        return totalPrice;
    }
}
