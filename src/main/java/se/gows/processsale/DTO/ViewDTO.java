package se.gows.processsale.DTO;

import se.gows.processsale.utils.Amount;

/**
 * View DTO. Holds information about last scanned item and running total of the sale.
 * This DTO is used to update the cashier display after each scan.
 */
public class ViewDTO {
    private final RegisteredItemDTO regItemDTO;
    private final Amount runningTotalIncVat;

    /**
     * @param regItemDTO last scanned item
     * @param runningTotalIncVat current running total inc vat
     */
    public ViewDTO(RegisteredItemDTO regItemDTO, Amount runningTotIncVat) {
        this.regItemDTO = regItemDTO;
        this.runningTotalIncVat = runningTotIncVat;
    }
    
    public RegisteredItemDTO getRegItem() {return regItemDTO;}
    public Amount getRunningTotalIncVat() {return runningTotalIncVat;}
}
