package se.gows.processsale.DTO;

public class SumDTO {
    public double totalPrice;
    public double totalVAT;
    public double totalIncVat;

    public SumDTO(double totalPrice, double totalVAT){
        this.totalPrice = totalPrice;
        this.totalVAT = totalVAT;
        this.totalIncVat = this.totalPrice + totalVAT;
    }
}
