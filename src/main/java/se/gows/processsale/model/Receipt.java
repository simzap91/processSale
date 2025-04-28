package se.gows.processsale.model;

import se.gows.processsale.DTO.SummaryDTO;
import se.gows.processsale.model.Transaction;


public class Receipt {
    private SummaryDTO summaryOfSale;
    private Transaction trans;
    

    public Receipt(SummaryDTO summaryDTO, Transaction trans) {
        this.summaryOfSale = summaryDTO;
        this.trans = trans;
    }
}
