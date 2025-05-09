package se.gows.processsale.DTO;

public class RegisteredItemDTO {
    private int itemID;
    private String itemDescription;
    private double price;
    private double vatRate;
    private int quantity;

    public RegisteredItemDTO(int itemID, String itemDescription, double price, double vatRate, int quantity) {
        this.itemID = itemID;
        this.itemDescription = itemDescription;
        this.price = price;
        this.vatRate = vatRate;
        this.quantity = quantity;
    }

    public int getItemID() {return this.itemID;}
    public String getItemDescription() {return this.itemDescription;}
    public double getPrice() {return this.price;}
    public double getVatRate() {return this.vatRate;}
    public int getQuantity() {return this.quantity;}
    public double getTotalPriceOfItemQuantity() {return this.price * this.quantity;}
    public double getTotalVatOfItemQuantity() {return this.price * this.quantity * this.vatRate;}
}
