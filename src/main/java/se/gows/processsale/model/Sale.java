package se.gows.processsale.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import se.gows.processsale.DTO.ItemDTO;
import se.gows.processsale.DTO.SaleDTO;
import se.gows.processsale.DTO.ViewDTO;

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
     * Method uses the itemID (int) provided by the controller
     * @param itemID
     * @return
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
 * Public method that updates the sale, with a given item ID and the quantity of the item. 
 * Internal methods check if item is already present in the current sale and updates the quantity,
 * or creates a new itemDTO contain information about the given item ID
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
            totalPrice += regItem.quantity * regItem.item.price;
            totalVAT += regItem.quantity * regItem.item.vatRate * regItem.item.price;
        }
    }

    /**
     * Private method that calculate runningTotalIncVat
     * @param itemID
     * @return
     */
    private double calculateRunningTotalIncVat() {
        return this.totalPrice + this.totalVAT;
    }

    /**
     * Public method that creates a new ViewDTO
     * @param itemID
     * @return
     */
    public ViewDTO createViewDTO(int itemID) {
        RegisteredItem regItem = fetchRegisteredItem(itemID);
        double runningTotIncVat = calculateRunningTotalIncVat();
        ViewDTO viewDTO = new ViewDTO(regItem, runningTotIncVat);
        return viewDTO;
    }

    /**
     * public method that fetches an RegisteredItem from itemList
     * @param itemID
     * @return
     */
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
