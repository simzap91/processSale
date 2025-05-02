package se.gows.processsale.DTO;

/**
     * Sum DTO. Holds the sales total sums.
     * @param totalPrice total price of the sale
     * @param totalVAT total VAT sum of the sale
     * @param totalIncVat total price including VAT sum
     */
public class SumDTO {
    public double totalPrice;
    public double totalVAT;
    public double totalIncVat;

    public SumDTO(double totalPrice, double totalVAT){
        this.totalPrice = totalPrice;
        this.totalVAT = totalVAT;
        this.totalIncVat = this.totalPrice + totalVAT;
    }

    public void updateTotIncVat(){
        this.totalIncVat = this.totalPrice + totalVAT;
    }
}
