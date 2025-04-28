package se.gows.processsale.DTO;

public class DiscountDTO {
    double discountSumItems;
    double discountRateSalePrice;
    double discountRateCustomer;

    public DiscountDTO(double discountSumItems, double discountRateSalePrice, double discountRateCustomer) {
        this.discountSumItems = discountSumItems;
        this.discountRateSalePrice = discountRateSalePrice;
        this.discountRateCustomer = discountRateCustomer;
    }
}
