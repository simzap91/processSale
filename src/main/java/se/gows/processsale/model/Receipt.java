package se.gows.processsale.model;

import java.time.LocalTime;

import se.gows.processsale.DTO.SummaryDTO;
import se.gows.processsale.model.Transaction;


public class Receipt {
    private LocalTime timeOfSale;
    private double totalPrice;
    private double totalVAT;
    private double totalIncVat;
    private RegisteredItem[] itemList;
    private Amount amountPaid;
    private Amount amountChange;
    

    public Receipt(SummaryDTO summaryDTO, Transaction trans) {
        this.LocalTime = summaryDTO.timeOfSale;
        this.totalPrice = summaryDTO.totalPrice;
        this.totalVAT = summaryDTO.totalVAT;
        this.totalIncVat = summaryDTO.totalIncVat;
        this.itemList = summaryDTO.itemList;
        this.amountPaid = trans.amountPaid;
        this.amountChange = trans.amountChange;
    }
}
