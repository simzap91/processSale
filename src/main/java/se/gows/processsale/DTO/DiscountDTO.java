package se.gows.processsale.DTO;

/**
     * Discount DTO. Holds data of discount to be reduced from total price.
     * @param discountItemsSum sum (SEK) to be reduced from total price if discount type 1 is requested.
     * @param discountSaleRate rate (decimal) of total price to be reduced from total price if discount type 2 is requested.
     * @param discountCustomerRate rate (decimal) of total price to be reduced from total price if discount type 3 is requested.
     */
public class DiscountDTO {
    public double discountItemsSum;
    public double discountSaleRate;
    public double discountCustomerRate;

    public DiscountDTO(double discountItemsSum, double discountSaleRate, double discountCustomerRate) {
        this.discountItemsSum = discountItemsSum;
        this.discountSaleRate = discountSaleRate;
        this.discountCustomerRate = discountCustomerRate;
    }
}
