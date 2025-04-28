package se.gows.processsale.integration;
import se.gows.processsale.model.Sale;

public class DiscountDBHandler {
    
    // Data base
    int[] memberCustomerIDs = {1,2,3,4,5}; // Format: {customerID}
    int[][] activeItemDiscounts = {{1, 20},{2, 10}}; // Format: {itemID, discountRate (%)}
    int memberDiscount = 15; // Discount rate (%) on entire sale for member customers
    int discountTypeTwoLimit = 200; // Total price limit (SEK) to reach discount status
    int discountEntireSale = 10; // Discount rate (%) on entire sale if total price > limit

    public DiscountDTO fetchDiscount(int[] discountTypes, int customerID, RegisteredItem[] purchasedItems, double totalPrice){

        DiscountDTO discountDTO = null;
        double sumDiscountTypeOne = 0;
        double rateDiscountTypeTwo = 0;
        double rateDiscountTypeThree = 0;

        for (int type : discountTypes) {
            if (type == 0) {
                continue;
            }
            if (type == 1) {
                sumDiscountTypeOne = calculateDiscountSumTypeOne(purchasedItems);
            }
            if (type == 2) {
                rateDiscountTypeTwo = calculateDiscountRateTypeTwo(totalPrice);
            }
            if (type == 3) {
                rateDiscountTypeThree = calculateDiscountRateTypeThree(customerID);
            }
            if (type > 3 || type < 0) {
                System.out.println("Invalid discount type");
            }
        }

        // If discount, create new discountDTO
        if (sumDiscountTypeOne > 0 || rateDiscountTypeTwo > 0 || rateDiscountTypeThree > 0) {
            discountDTO = new DiscountDTO(sumDiscountTypeOne, rateDiscountTypeTwo, rateDiscountTypeThree);
        }
        return discountDTO;
    }

    private double calculateDiscountSumTypeOne(RegisteredItem[] purchasedItems) {

        // Variable for total discount sum
        double discountSum = 0;

        // Step through all discount offers
        for (int[] discObj : activeItemDiscounts){
            // Step through all purchased items
            for (RegisteredItem regItem : purchasedItems) {
                // If item has discount offer -> calculate reduced price
                if (discObj[0] == regItem.item.itemID){

                    // Get discount rate as decimal number
                    double discItemRate = Math.round(discObj[1] * 100.0) / 10000.0;
                    System.out.println(discItemRate);

                    // Calculate discounted sum
                    double discItemSum = Math.round(regItem.item.price * discItemRate * 100.0) / 100.0;
                    System.out.println(discItemSum);

                    // Update registered item object
                    regItem.discountRate = discItemRate;
                    regItem.discountedPrice = discItemSum;

                    // Add item discount sum to total discount sum
                    discountSum += discItemSum;
                }
            }
        }
        // Return total discount sum
        return discountSum;
    }

    private double calculateDiscountRateTypeTwo(double totalPrice){

        double discountRate = 0;
        
        // Discount only applies if totalPrice > 200 kr
        if (totalPrice > discountTypeTwoLimit) {
            discountRate = discountEntireSale;
        }
        // Return discount rate (returns zero if totalPrice < 200)
        return discountRate;
    }

    private double calculateDiscountRateTypeThree(int customerID){

        double discountRate = 0;
        
        // Discount only applies if customer is member
        // Step thorugh all member-IDs
        for (int memberID : memberCustomerIDs) {
            // If customer is member, discount applies
            if (memberID == customerID) {
                discountRate = memberDiscount;
            }
        }
        // Return discount rate (returns zero if customer is not member)
        return discountRate;
    }
}
