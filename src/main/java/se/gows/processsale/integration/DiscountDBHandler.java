package se.gows.processsale.integration;

import java.util.Arrays;

import se.gows.processsale.DTO.DiscountRequestDTO;
import se.gows.processsale.DTO.RegisteredItemDTO;
import se.gows.processsale.integration.discount.*;
import se.gows.processsale.model.Amount;
import se.gows.processsale.model.CustomerId;
import se.gows.processsale.utils.DiscountTypes;


/**     
 * Public handler that communicates with the external discount database.
 */
public class DiscountDBHandler {

    /**
     * Public method that fetches all requested and applicable discounts and calculates a new reduced total price.
     * @param requestedDiscounts A list of requested discount types.
     * @param discRequest Contains customer ID, purchased items and total price of the sale.
     * @return Updated totalprice. Returns unchanged if no discount apply.
     *  
     */
    public Amount getDiscountedPrice(DiscountTypes[] requestedDiscounts, DiscountRequestDTO discRequest){

        CustomerId customerId = discRequest.getCustomerId();
        RegisteredItemDTO[] purchasedItems = discRequest.getItemList();
        Amount totalPrice = discRequest.getTotalPrice();

        if (Arrays.stream(requestedDiscounts).anyMatch(n -> n == DiscountTypes.ITEMS)) {
            ItemDiscount discountCalculator = new ItemDiscount();
            totalPrice = discountCalculator.getDiscount(discRequest);
            discRequest = new DiscountRequestDTO(customerId, purchasedItems, totalPrice);
        }
        if (Arrays.stream(requestedDiscounts).anyMatch(n -> n == DiscountTypes.SALE)) {
            totalPrice = new SaleDiscount().getDiscount(discRequest);
            discRequest = new DiscountRequestDTO(customerId, purchasedItems, totalPrice);
        }
        if (Arrays.stream(requestedDiscounts).anyMatch(n -> n == DiscountTypes.CUSTOMER)) {
            CustomerDiscount discountCalculator = new CustomerDiscount();
            totalPrice = discountCalculator.getDiscount(discRequest);
        }
        return totalPrice;
    }
}
