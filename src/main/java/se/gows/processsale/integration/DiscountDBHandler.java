package se.gows.processsale.integration;

import se.gows.processsale.model.RegisteredItem;

public class DiscountDBHandler {
    
    /**
     * Data base with member customers and three discount types.
     */
    int[] memberCustomerIDs = {1,2,3,4,5}; // Format: {customerID}
    double[][] discountDBItemsSum = {{1.0, 0.20},{2.0, 0.10}}; // Type 1, format: {itemID (as double), discountRate}
    double[] discountDBSaleRate = {200.0, 0.1}; // Type 2, format: {total price lower limit, disc rate}
    double discountDBCustomerRate = 0.15; // Type 3, format: disc rate

    /**
     * Public method that determines which type of discount should be applied on the sale and calculates the total discount.
     * @param discountTypes The different types of discounts, stores as an array
     * @param customerID the customers ID
     * @param purchasedItems a list of all items that are present in the current sale
     * @param totalPrice the total price of all items in the current sale
     * @return updated totalprice
     *  
     */
    public double getDiscountedPrice(int[] discountTypes, int customerID, RegisteredItem[] purchasedItems, double totalPrice){
        double discountedTotalPrice = totalPrice;
        for (int type : discountTypes) {
            if (type == 1) {
                discountedTotalPrice -= calculateDiscountItemsSum(purchasedItems);
            }
            if (type == 2) {
                discountedTotalPrice -= discountedTotalPrice*calculateDiscountSaleRate(totalPrice);
            }
            if (type == 3) {
                discountedTotalPrice -= discountedTotalPrice*calculateDiscountCustomerRate(customerID);
            }
            if (type > 3 || type < 0) {
                System.out.println("Invalid discount type");
            }
        }
        return discountedTotalPrice;
    }

    /**
     * @param purchasedItems
     * @return 
     *  
     */
    private double calculateDiscountItemsSum(RegisteredItem[] purchasedItems) {
        double discountSum = 0;
        for (double[] discObj : discountDBItemsSum){
            for (RegisteredItem regItem : purchasedItems) {

                int discObjItemId = (int) discObj[0];
                if (discObjItemId == regItem.item.itemID){

                    double discItemRate = discObj[1];
                    double discItemSum = regItem.item.price * discItemRate * regItem.quantity;

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
    private double calculateDiscountSaleRate(double totalPrice){
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
    private double calculateDiscountCustomerRate(int customerID){
        double discountRate = 0;
        for (int memberID : memberCustomerIDs) {
            if (memberID == customerID) {
                discountRate = discountDBCustomerRate;
            }
        }
        return discountRate;
    }
}
