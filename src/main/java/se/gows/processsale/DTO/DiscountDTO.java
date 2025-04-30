package se.gows.processsale.DTO;

/**
     * Discount DTO. Holds data of discount to be reduced from total price.
     * @param discountSumTypeOne sum (SEK) to be reduced from total price if discount type 1 is requested.
     * @param discountSumTypeTwo rate (decimal) of total price to be reduced from total price if discount type 2 is requested.
     * @param discountSumTypeThree rate (decimal) of total price to be reduced from total price if discount type 3 is requested.
     */
public class DiscountDTO {
    public double discountSumTypeOne;
    public double discountRateTypeTwo;
    public double discountRateTypeThree;

    public DiscountDTO(double discountSumTypeOne, double discountRateTypeTwo, double discountRateTypeThree) {
        this.discountSumTypeOne = discountSumTypeOne;
        this.discountRateTypeTwo = discountRateTypeTwo;
        this.discountRateTypeThree = discountRateTypeThree;
    }
}
