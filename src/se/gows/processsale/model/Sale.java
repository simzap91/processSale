package se.gows.processsale.model;

import java.time.LocalTime;

public class Sale {
    private LocalTime timeOfSale;

    /**
     * 
     */
    public Sale() {
        timeOfSale = LocalTime.now(); //setTimeOfSale
    }
}
