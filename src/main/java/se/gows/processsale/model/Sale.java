package se.gows.processsale.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import se.gows.processsale.DTO.ItemDTO;
import se.gows.processsale.DTO.SaleDTO;
import se.gows.processsale.DTO.ViewDTO;

/**
     * Sale class that represent the sale. A new instance of this class is created every time a new customer enters the checkout.
     * @param timeOfSale start time of sale (including date)
     * @param itemList list with all scanned items
     * @param totalPrice running total price of the sale
     * @param totalPrice running total vat (sum) of the sale
     */
public class Sale {
    private LocalDateTime timeOfSale;
    private ArrayList<RegisteredItem> itemList = new ArrayList<>();
    private double totalPrice;
    private double totalVAT;

    public Sale() {
        timeOfSale = LocalDateTime.now();
    }

    /**
     * Public method that checks if a scanned item is in itemList
     * @param itemID Unique item identifier
     * @return true if item is present in itemList, else false
     */
    public boolean checkItemList(int itemID){
        for (RegisteredItem regItem : itemList){
            if (regItem.idsAreEqual(itemID)){
                return true;
            }
        }
        return false;
    }

    /**
     * Public method that adds a new item to itemList and its quantity
     * @param item itemDTO contain information about the item
     * @param quantity quantity of a given item
     */
    public void addItem(ItemDTO item, int quantity) {
        RegisteredItem scannedItem = new RegisteredItem(item, quantity);
        itemList.add(scannedItem);
        updateSalePriceAndVat();
    }
    /**
     * Public method that updates the sale when an already registered item is scanned.
     * The method first updates the item quantity and then the total price and vat of sale.
     * @param itemID ID of a given item
     * @param itemQuantity amount of the given item
     */
    public void updateSale(int itemID, int itemQuantity) {
        updateItem(itemID, itemQuantity);
        updateSalePriceAndVat();
    }

    private void updateItem(int itemID, int quantity) {
        for (RegisteredItem regItem : itemList) {
            if (regItem.idsAreEqual(itemID)){
                regItem.setQuantity(regItem.quantity + quantity);
                break;
            }
        }
    }

    private void updateSalePriceAndVat(){
        totalPrice = 0;
        totalVAT = 0;
        for (RegisteredItem regItem : itemList) {
            totalPrice += regItem.getTotalPriceOfItemQuantity();
            totalVAT += regItem.getTotalVatOfItemQuantity();
        }
    }

    private double calculateRunningTotalIncVat() {
        return this.totalPrice + this.totalVAT;
    }

    /**
     * Public method that creates a new ViewDTO
     * @param itemID identifier of the item that the returned ViewDTO will hold
     * @return a ViewDTO holding the provided item and running total (inc. vat)
     */
    public ViewDTO createViewDTO(int itemID) {
        RegisteredItem regItem = fetchRegisteredItem(itemID);
        double runningTotIncVat = calculateRunningTotalIncVat();
        ViewDTO viewDTO = new ViewDTO(regItem, runningTotIncVat);
        return viewDTO;
    }

    private RegisteredItem fetchRegisteredItem(int itemID){
        for (RegisteredItem regItem : itemList){
            if (regItem.idsAreEqual(itemID)){
                return regItem;
            }
        }
        return null;
    }

    /**
     * Public method that ends the current sale and returns a SaleDTO containing the final information about the registered items and their total price plus Vat
     * @return SaleDTO
     */
    public SaleDTO endSale(){
        RegisteredItem[] itemListArray = itemList.toArray(new RegisteredItem[0]);
        SaleDTO saleDTO = new SaleDTO(timeOfSale, totalPrice, totalVAT, itemListArray);
        return saleDTO;
    }
}
