package se.gows.processsale.integration;

import java.util.Arrays;

import se.gows.processsale.DTO.DiscountRequestDTO;
import se.gows.processsale.DTO.RegisteredItemDTO;
import se.gows.processsale.integration.discount.*;
import se.gows.processsale.utils.Amount;
import se.gows.processsale.utils.DiscountTypes;


/**     
 * Public handler that communicates with the external discount database.
 */
public class DiscountDBHandler {

    /**
     * Public method that fetches all requested and applicable discounts and calculates a new reduced total price.
     * 
     * @param requestedDiscountTypes A list of requested discount types.
     * @param discountRequest Contains customer ID, purchased items and total price of the sale.
     * @return Updated totalprice. Returns unchanged if no discount apply.
     *  
     */
    public Amount getDiscountedPrice(DiscountTypes[] requestedDiscountTypes, DiscountRequestDTO discountRequest){

        int customerId = discountRequest.getCustomerId();
        RegisteredItemDTO[] purchasedItems = discountRequest.getPurchasedItems();
        Amount totalPrice = discountRequest.getTotalPrice();

        if (Arrays.stream(requestedDiscountTypes).anyMatch(n -> n == DiscountTypes.ITEMS)) {
            totalPrice = new ItemDiscount().getDiscount(discountRequest);
            discountRequest = new DiscountRequestDTO(customerId, purchasedItems, totalPrice);
        }
        if (Arrays.stream(requestedDiscountTypes).anyMatch(n -> n == DiscountTypes.SALE)) {
            totalPrice = new SaleDiscount().getDiscount(discountRequest);
            discountRequest = new DiscountRequestDTO(customerId, purchasedItems, totalPrice);
        }
        if (Arrays.stream(requestedDiscountTypes).anyMatch(n -> n == DiscountTypes.CUSTOMER)) {
            totalPrice = new CustomerDiscount().getDiscount(discountRequest);
        }
        return totalPrice;
    }
}
