package se.gows.processsale.integration.discount;

import se.gows.processsale.DTO.RegisteredItemDTO;
import se.gows.processsale.DTO.SaleDTO;
import se.gows.processsale.model.Amount;

public class ItemDiscount implements DiscountCalculator{ 

    private double[][] activeItemDiscounts = {{1.0, 0.20},{2.0, 0.10}}; // Format: {itemID (as double), discountRate}
    
    @Override
    public Amount getDiscount(SaleDTO sale){
        double discountedPrice = sale.getSaleSums().getTotalPrice().getValue();
        for (double[] discount : activeItemDiscounts){
            for (RegisteredItemDTO regItem : sale.getItemList()) {

                int discObjItemId = (int) discount[0];
                if (discObjItemId == regItem.getItemID()){

                    double discItemRate = discount[1];
                    double discItemSum = regItem.getTotalPriceOfItemQuantity() * discItemRate;

                    discountedPrice -= discItemSum;
                }
            }
        }
        return new Amount(discountedPrice);
    }
}
