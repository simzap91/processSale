package se.gows.processsale.integration;

import se.gows.processsale.model.RegisteredItem;
import se.gows.processsale.DTO.DiscountDTO;

public class DiscountDBHandler {
    
    /**
     * Data base with member customers and three discount types.
     */
    int[] memberCustomerIDs = {1,2,3,4,5}; // Format: {customerID}
    double[][] discountDBTypeOne = {{1.0, 0.20},{2.0, 0.10}}; // Format: {itemID (as double), discountRate}
    double[] discountDBTypeTwo = {200.0, 0.1}; // Total price limit (SEK) to reach discount status
    double discountDBTypeThree = 0.15; // Discount rate on entire sale for member customers

    /**
     * @param discountTypes
     * @param customerID
     * @param purchasedItems
     * @param totalPrice
     * @return 
     *  
     */
    public DiscountDTO fetchDiscount(int[] discountTypes, int customerID, RegisteredItem[] purchasedItems, double totalPrice){

        DiscountDTO discountDTO = null;
        double discountSumTypeOne = 0;
        double discountRateTypeTwo = 1;
        double discountRateTypeThree = 1;

        for (int type : discountTypes) {

            if (type == 1) {
                discountSumTypeOne = calculateDiscountSumTypeOne(purchasedItems);
            }
            if (type == 2) {
                discountRateTypeTwo = calculateDiscountRateTypeTwo(totalPrice);
            }
            if (type == 3) {
                discountRateTypeThree = calculateDiscountRateTypeThree(customerID);
            }
            if (type > 3 || type < 0) {
                System.out.println("Invalid discount type");
            }
        }
        if (discountSumTypeOne > 0 || discountRateTypeTwo < 1 || discountRateTypeThree < 1)
            discountDTO = new DiscountDTO(discountSumTypeOne, discountRateTypeTwo, discountRateTypeThree);

        return discountDTO;
    }

    /**
     * @param purchasedItems
     * @return 
     *  
     */
    private double calculateDiscountSumTypeOne(RegisteredItem[] purchasedItems) {

        double discountSum = 0;

        for (double[] discObj : discountDBTypeOne){
            for (RegisteredItem regItem : purchasedItems) {

                int discObjItemId = (int) discObj[0];
                if (discObjItemId == regItem.item.itemID){

                    double discItemRate = discObj[1];
                    double discItemSum = regItem.item.price * discItemRate;

                    regItem.discountRate = discItemRate;
                    regItem.discountedPrice = regItem.item.price - discItemSum;

                    discountSum += discItemSum;
                }
            }
        }
        return discountSum;
    }

    /**
     * @param totalPrice
     * @return 
     *  
     */
    private double calculateDiscountRateTypeTwo(double totalPrice){

        double discountRate = 0;
        
        if (totalPrice > discountDBTypeTwo[0]) {
            discountRate = discountDBTypeTwo[1];
        }
        return discountRate;
    }

    /**
     * @param customerID
     * @return 
     *  
     */
    private double calculateDiscountRateTypeThree(int customerID){

        double discountRate = 0;
        
        for (int memberID : memberCustomerIDs) {
            if (memberID == customerID) {
                discountRate = discountDBTypeThree;
            }
        }
        return discountRate;
    }
}
