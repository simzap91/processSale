package se.gows.processsale.DTO;

public class DiscountDTO {
    double discountRateSalePrice;
    double discountSumItems;
    double discountRateCustomer;

    public DiscountDTO(double discountRateSalePrice, double discountSumItems, double discountRateCustomer) {
        this.discountRateSalePrice = discountRateSalePrice;
        this.discountSumItems = discountSumItems;
        this.discountRateCustomer = discountRateCustomer;
    }
}
