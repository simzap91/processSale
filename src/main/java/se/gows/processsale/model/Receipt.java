package se.gows.processsale.model;

import java.time.LocalTime;

import se.gows.processsale.DTO.SummaryDTO;
import se.gows.processsale.DTO.SumDTO;

public class Receipt {
    public LocalTime timeOfSale;
    public SumDTO saleSums;
    public RegisteredItem[] itemList;
    public Amount amountPaid;
    public Amount amountChange;
    

    public Receipt(SummaryDTO summaryDTO, Transaction trans) {
        this.timeOfSale = summaryDTO.timeOfSale;
        this.saleSums = summaryDTO.saleSums;
        this.itemList = summaryDTO.itemList;
        this.amountPaid = trans.amountPaid;
        this.amountChange = trans.amountChange;
    }
}
