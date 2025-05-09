package se.gows.processsale.DTO;

/**
 * View DTO. Holds information about last scanned item and running total of the sale.
 * @param regItemDTO last scanned item
 * @param runningTotalIncVat current running total inc vat
 */
public class ViewDTO {
    private RegisteredItemDTO regItemDTO;
    private double runningTotalIncVat;

    public ViewDTO(RegisteredItemDTO regItemDTO, double runningTotIncVat) {
        this.regItemDTO = regItemDTO;
        this.runningTotalIncVat = runningTotIncVat;
    }
    public RegisteredItemDTO getRegItem() {
        return regItemDTO;
    }
    public double getRunningTotalIncVat() {
        return runningTotalIncVat;
    }
}
