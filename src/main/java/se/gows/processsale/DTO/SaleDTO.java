package se.gows.processsale.DTO;

import java.time.LocalTime;

import se.gows.processsale.model.RegisteredItem;

public class SaleDTO {
    public LocalTime timeOfSale;
    public double totalPrice;
    public double totalVAT;
    public double totalIncVat;
    public RegisteredItem[] itemList;

    public SaleDTO (LocalTime timeOfSale, double totalPrice, double totalVAT, double totalIncVat, RegisteredItem[] itemList){
        this.timeOfSale = timeOfSale;
        this.totalPrice = totalPrice;
        this.totalVAT = totalVAT;
        this.totalIncVat = totalIncVat;
        this.itemList = itemList;
    }
}
