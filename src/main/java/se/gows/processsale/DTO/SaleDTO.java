package se.gows.processsale.DTO;

import java.time.LocalTime;

import se.gows.processsale.model.RegisteredItem;

public class SaleDTO {
    public LocalTime timeOfSale;
    public SumDTO saleSums;
    public RegisteredItem[] itemList;

    public SaleDTO (LocalTime timeOfSale, double totalPrice, double totalVAT, double totalIncVat, RegisteredItem[] itemList){
        this.timeOfSale = timeOfSale;
        this.saleSums = new SumDTO(totalPrice, totalVAT);
        this.itemList = itemList;
    }
}
