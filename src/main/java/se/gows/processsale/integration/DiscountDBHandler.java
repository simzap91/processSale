package se.gows.processsale.integration;

import se.gows.processsale.model.RegisteredItem;
import se.gows.processsale.DTO.DiscountDTO;

public class DiscountDBHandler {
    
    /**
     * Data base with member customers and three discount types.
     */
    int[] memberCustomerIDs = {1,2,3,4,5}; // Format: {customerID}
    double[][] discountDBItemsSum = {{1.0, 0.20},{2.0, 0.10}}; // Type 1, format: {itemID (as double), discountRate}
    double[] discountDBSaleRate = {200.0, 0.1}; // Type 2, format: {total price lower limit, disc rate}
    double discountDBCustomerRate = 0.15; // Type 3, format: disc rate

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
        double initialDiscountItemsSum = 0;
        double discountItemsSum = initialDiscountItemsSum;
        double initialDiscountSaleRate = 1;
        double discountSaleRate = initialDiscountSaleRate;
        double initialDiscountCustomerRate = 1;
        double discountCustomerRate = initialDiscountCustomerRate;

        for (int type : discountTypes) {

            if (type == 1) {
                discountItemsSum = calculatediscountItmesSum(purchasedItems);
            }
            if (type == 2) {
                discountSaleRate = calculateDiscountRateTypeTwo(totalPrice);
            }
            if (type == 3) {
                discountCustomerRate = calculateDiscountRateTypeThree(customerID);
            }
            if (type > 3 || type < 0) {
                System.out.println("Invalid discount type");
            }
        }
        if (discountItemsSum > initialDiscountItemsSum || discountSaleRate < initialDiscountSaleRate || discountCustomerRate < initialDiscountCustomerRate)
            discountDTO = new DiscountDTO(discountItemsSum, discountSaleRate, discountCustomerRate);

        return discountDTO;
    }

    /**
     * @param purchasedItems
     * @return 
     *  
     */
    private double calculatediscountItmesSum(RegisteredItem[] purchasedItems) {

        double discountSum = 0;

        for (double[] discObj : discountDBItemsSum){
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
        
        if (totalPrice > discountDBSaleRate[0]) {
            discountRate = discountDBSaleRate[1];
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
                discountRate = discountDBCustomerRate;
            }
        }
        return discountRate;
    }
}
