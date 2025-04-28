package se.gows.processsale.DTO;

public class DiscountDTO {
    public double discountSumItems;
    public double discountRateSalePrice;
    public double discountRateCustomer;

    public DiscountDTO(double discountSumItems, double discountRateSalePrice, double discountRateCustomer) {
        this.discountSumItems = discountSumItems;
        this.discountRateSalePrice = discountRateSalePrice;
        this.discountRateCustomer = discountRateCustomer;
    }
}
