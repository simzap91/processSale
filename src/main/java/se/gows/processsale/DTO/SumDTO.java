package se.gows.processsale.DTO;

import se.gows.processsale.utils.Amount;

/**
 * Sum DTO. Holds the sales total sums.
 */
public class SumDTO {
    private Amount totalPrice;
    private Amount totalVAT;
    private Amount totalIncVat;

    /**
     * @param totalPrice Total price of the sale (excl. vat)
     * @param totalVAT Total vat sum of the sale
     */
    public SumDTO(Amount totalPrice, Amount totalVAT){
        this.totalPrice = totalPrice;
        this.totalVAT = totalVAT;
        this.totalIncVat = new Amount(totalPrice.getValue() + totalVAT.getValue());
    }

    public Amount getTotalPrice(){return this.totalPrice;}
    public Amount getTotalVAT(){return this.totalVAT;}
    public Amount getTotalIncVat(){return this.totalIncVat;}
}
