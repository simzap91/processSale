package se.gows.processsale.integration.discount;

import se.gows.processsale.DTO.DiscountRequestDTO;
import se.gows.processsale.DTO.RegisteredItemDTO;
import se.gows.processsale.utils.Amount;

/**
 * Calculates a reduced total sale price by calculating a new sum based on item specific discounts.
 * The class implements the DiscountCalculator interface.
 */
public class ItemDiscount implements DiscountCalculator{ 

    /**
     * Data base with all active item discounts on the following format: {itemID (as double), discountRate}
     */
    private double[][] activeItemDiscounts = {{1.0, 0.20},{2.0, 0.10}};

    /**
     * Calculates a discounted price from sale when comparing its item list with a list of active discounts, reducing the items price if it is on the list.
     * 
     * @param discountRequest DiscountRequestDTO containing all information required for applying a discount, including an item list.
     * @return a new discounted price.
     */
    @Override
    public Amount getDiscount(DiscountRequestDTO discountRequest){
        double discountedPrice = discountRequest.getTotalPrice().getValue();
        for (double[] itemDiscount : activeItemDiscounts){
            for (RegisteredItemDTO regItem : discountRequest.getPurchasedItems()) {

                int discObjItemId = (int) itemDiscount[0];
                if (discObjItemId == regItem.getItemID()){

                    double discItemRate = itemDiscount[1];
                    double discItemSum = regItem.getTotalPriceOfItemQuantity() * discItemRate;

                    discountedPrice -= discItemSum;
                }
            }
        }
        return new Amount(discountedPrice);
    }
}
