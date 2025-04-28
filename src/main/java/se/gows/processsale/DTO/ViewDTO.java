package se.gows.processsale.DTO;

import se.gows.processsale.model.RegisteredItem;

public class ViewDTO {
    public RegisteredItem regItem;
    public double runningTotalIncVat;

    public ViewDTO(RegisteredItem regItem, double runningTotIncVat) {
        this.regItem = regItem;
        this.runningTotalIncVat = runningTotIncVat;
    }
}
