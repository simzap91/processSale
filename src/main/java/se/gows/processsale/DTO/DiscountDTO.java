package se.gows.processsale.DTO;

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
