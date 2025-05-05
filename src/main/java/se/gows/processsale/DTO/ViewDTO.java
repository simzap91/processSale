package se.gows.processsale.DTO;

import se.gows.processsale.model.RegisteredItem;

/**
 * View DTO. Holds information about last scanned item and running total of the sale.
 * @param regItem last scanned item
 * @param runningTotalIncVat current running total inc vat
 */
public class ViewDTO {
    public RegisteredItem regItem;
    public double runningTotalIncVat;

    public ViewDTO(RegisteredItem regItem, double runningTotIncVat) {
        this.regItem = regItem;
        this.runningTotalIncVat = runningTotIncVat;
    }
}
