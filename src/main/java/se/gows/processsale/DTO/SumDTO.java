package se.gows.processsale.DTO;

import se.gows.processsale.model.Amount;

/**
 * Sum DTO. Holds the sales total sums.
 * @param totalPrice total price of the sale
 * @param totalVAT total VAT sum of the sale
 * @param totalIncVat total price including VAT sum
 */
public class SumDTO {
    private Amount totalPrice;
    private Amount totalVAT;
    private Amount totalIncVat;

    public SumDTO(Amount totalPrice, Amount totalVAT){
        this.totalPrice = totalPrice;
        this.totalVAT = totalVAT;
        this.totalIncVat = new Amount(totalPrice.getValue() + totalVAT.getValue());
    }

    public void updateTotIncVat(){
        this.totalIncVat.setAmount(this.totalPrice.getValue() + this.totalVAT.getValue());
    }
    public Amount getTotalPrice(){return this.totalPrice;}
    public Amount getTotalVAT(){return this.totalVAT;}
    public Amount getTotalIncVat(){return this.totalIncVat;}
}
