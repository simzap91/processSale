package se.gows.processsale.DTO;

import se.gows.processsale.model.Amount;
/**
 * View DTO. Holds information about last scanned item and running total of the sale.
 * @param regItemDTO last scanned item
 * @param runningTotalIncVat current running total inc vat
 * @param errMsg If item is not in inventoryDB
 */
public class ViewDTO {
    private final RegisteredItemDTO regItemDTO;
    private final Amount runningTotalIncVat;
    private final String errorMessage;

    public ViewDTO(RegisteredItemDTO regItemDTO, Amount runningTotIncVat, String errMsg) {
        this.regItemDTO = regItemDTO;
        this.runningTotalIncVat = runningTotIncVat;
        this.errorMessage = errMsg;
    }
    public RegisteredItemDTO getRegItem() {
        return regItemDTO;
    }
    public Amount getRunningTotalIncVat() {
        return runningTotalIncVat;
    }
    public boolean hasError(){
        return this.errorMessage != null;
    }
    public String getErrorMessage() {
        return this.errorMessage;
    }
}
