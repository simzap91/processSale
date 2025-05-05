package se.gows.processsale.DTO;

/**
 * Sum DTO. Holds the sales total sums.
 * @param totalPrice total price of the sale
 * @param totalVAT total VAT sum of the sale
 * @param totalIncVat total price including VAT sum
 */
public class SumDTO {
    private double totalPrice;
    private double totalVAT;
    private double totalIncVat;

    public SumDTO(double totalPrice, double totalVAT){
        this.totalPrice = totalPrice;
        this.totalVAT = totalVAT;
        this.totalIncVat = totalPrice + totalVAT;
    }

    public void updateTotIncVat(){
        this.totalIncVat = this.totalPrice + this.totalVAT;
    }
    public double getTotalPrice(){return this.totalPrice;}
    public double getTotalVAT(){return this.totalVAT;}
    public double getTotalIncVat(){return this.totalIncVat;}
}
