package se.gows.processsale.integration;
import se.gows.processsale.model.Sale;

public class DiscountDBHandler {
    
    public double fetchDiscount(int customerID, Sale currentSale){

        double discountRate = customerDiscount(customerID);
        discountRate = discountRate*itemDiscounts(currentSale);

        discountRate = discountRate*totalSaleDiscount(currentSale);

        return discountRate;
    }
    private double itemDiscounts(Sale currentSale){
        return 0.8;
    }
    private double customerDiscount(int customerID){
        return 0.8;
    }
    private double totalSaleDiscount(Sale currentSale){
        return currentSale.getTotal;
    }

}
