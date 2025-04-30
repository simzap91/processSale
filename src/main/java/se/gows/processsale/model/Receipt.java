package se.gows.processsale.model;

import java.time.LocalTime;

import se.gows.processsale.DTO.SaleDTO;

public class Receipt {
    public LocalTime timeOfSale;
    public double totalPrice;
    public double totalVAT;
    public double totalIncVat;
    public RegisteredItem[] itemList;
    public Amount amountPaid;
    public Amount amountChange;
    

    public Receipt(SaleDTO saleDTO, Transaction trans) {
        this.timeOfSale = saleDTO.timeOfSale;
        this.totalPrice = saleDTO.totalPrice;
        this.totalVAT = saleDTO.totalVAT;
        this.totalIncVat = saleDTO.totalIncVat;
        this.itemList = saleDTO.itemList;
        this.amountPaid = trans.amountPaid;
        this.amountChange = trans.amountChange;
    }
}
