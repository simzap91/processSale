package se.gows.processsale.DTO;

/**
 * View DTO. Holds information about last scanned item and running total of the sale.
 * @param regItemDTO last scanned item
 * @param runningTotalIncVat current running total inc vat
 */
public class ViewDTO {
    private final RegisteredItemDTO regItemDTO;
    private final double runningTotalIncVat;
    private final String errorMessage;

    public ViewDTO(RegisteredItemDTO regItemDTO, double runningTotIncVat, String errMsg) {
        this.regItemDTO = regItemDTO;
        this.runningTotalIncVat = runningTotIncVat;
        this.errorMessage = errMsg;
    }
    public RegisteredItemDTO getRegItem() {
        return regItemDTO;
    }
    public double getRunningTotalIncVat() {
        return runningTotalIncVat;
    }
    public boolean hasError(){
        return this.errorMessage != null;
    }
    public String getErrorMessage() {
        return this.errorMessage;
    }
}
