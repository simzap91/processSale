package se.gows.processsale.model;

import java.time.LocalTime;
import se.gows.processsale.DTO.itemDTO;

public class Sale {
    private LocalTime timeOfSale;
    private itemDTO addedItems;
    private double totalPrice;
    private int totalVAT;

    /**
     * 
     */
    public Sale sale() {
        setTimeOfSale();
    }

    private void setTimeOfSale(){
        timeOfSale = LocalTime.now(); //setTimeOfSale
    }

    public ViewDTO addItem(scannedItem, quantity){
        
    }
}
