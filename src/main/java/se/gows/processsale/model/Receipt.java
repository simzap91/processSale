package se.gows.processsale.model;

import java.time.LocalTime;

import se.gows.processsale.DTO.SummaryDTO;

public class Receipt {
    public LocalTime timeOfSale;
    public double totalPrice;
    public double totalVAT;
    public double totalIncVat;
    public RegisteredItem[] itemList;
    public Amount amountPaid;
    public Amount amountChange;
    

    public Receipt(SummaryDTO summaryDTO, Transaction trans) {
        this.timeOfSale = summaryDTO.timeOfSale;
        this.totalPrice = summaryDTO.totalPrice;
        this.totalVAT = summaryDTO.totalVAT;
        this.totalIncVat = summaryDTO.totalIncVat;
        this.itemList = summaryDTO.itemList;
        this.amountPaid = trans.amountPaid;
        this.amountChange = trans.amountChange;
    }
}
