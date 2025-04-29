package se.gows.processsale.integration;

import se.gows.processsale.model.RegisteredItem;
import se.gows.processsale.DTO.DiscountDTO;

public class DiscountDBHandler {
    
    // Data base
    int[] memberCustomerIDs = {1,2,3,4,5}; // Format: {customerID}
    int[][] activeItemDiscounts = {{1, 20},{2, 10}}; // Format: {itemID, discountRate (%)}
    double memberDiscount = 0.15; // Discount rate (%) on entire sale for member customers
    int discountTypeTwoLimit = 200; // Total price limit (SEK) to reach discount status
    double discountEntireSale = 0.1; // Discount rate (%) on entire sale if total price > limit

    public DiscountDTO fetchDiscount(int[] discountTypes, int customerID, RegisteredItem[] purchasedItems, double totalPrice){

        System.out.println("enter fetchDiscount");

        DiscountDTO discountDTO = null;
        double discountSumItems = 0;
        double discountRateSalePrice = 1;
        double discountRateCustomer = 1;

        for (int type : discountTypes) {
            
            if (type == 0) {
                continue;
            }
            if (type == 1) {
                System.out.println("discount type 1 detectedt");
                discountSumItems = calculateDiscountSumTypeOne(purchasedItems);
                System.out.println(discountSumItems);
            }
            if (type == 2) {
                System.out.println("discount type 2 detectedt");
                discountRateSalePrice = calculateDiscountRateTypeTwo(totalPrice);
                System.out.println(discountRateSalePrice);
            }
            if (type == 3) {
                System.out.println("discount type 3 detectedt");
                discountRateCustomer = calculateDiscountRateTypeThree(customerID);
                System.out.println(discountRateCustomer);
            }
            if (type > 3 || type < 0) {
                System.out.println("Invalid discount type");
            }
        }

        // If discount, create new discountDTO
        if (discountSumItems > 0 || discountRateSalePrice < 1 || discountRateCustomer < 1) {

            System.out.println("cretae discount Dto");
            discountDTO = new DiscountDTO(discountSumItems, discountRateSalePrice, discountRateCustomer);
            System.out.println(discountDTO.discountSumItems);
            System.out.println(discountDTO.discountRateCustomer);
            System.out.println(discountDTO.discountRateSalePrice);
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

                    // Calculate discounted sum
                    double discItemSum = Math.round(regItem.item.price * discItemRate * 100.0) / 100.0;

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
