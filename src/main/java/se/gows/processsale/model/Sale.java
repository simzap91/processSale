package se.gows.processsale.model;

import java.util.ArrayList;
import java.util.List;

import se.gows.processsale.DTO.*;
import se.gows.processsale.utils.RevenueObserver;

/**
 * Sale class that represent the sale. A new instance of this class is created every time a new customer enters the checkout.
 * @param itemList list with all scanned items
 * @param totalPrice running total price of the sale
 * @param totalVAT running total vat (sum) of the sale
 */
public class Sale {
    private ArrayList<RegisteredItemDTO> itemList = new ArrayList<>();
    private double totalPrice;
    private double totalVAT;
    private double totalRevenue = 0;
    private final List<RevenueObserver> observers = new ArrayList<>();

    /**
     * Public method that checks if a scanned item is in itemList
     * @param itemID Unique item identifier
     * @return true if item is present in itemList, else false
     */
    public boolean isItemInItemList(int itemID){
        for (RegisteredItemDTO regItem : itemList){
            if (idsAreEqual(regItem.getItemID(), itemID)){
                return true;
            }
        }
        return false;
    }

    /**
     * Method that compares two itemIDs.
     */
    private boolean idsAreEqual(int itemID1, int itemID2){return itemID1 == itemID2;}

    /**
     * Public method that adds a new item to itemList and its quantity
     * @param item itemDTO contain information about the item
     * @param quantity quantity of a given item
     */
    public void addNewItem(ItemDTO item, int quantity) {
        RegisteredItemDTO newItem = new RegisteredItemDTO(
            item.getItemID(), item.getItemDescription(), item.getPrice(), item.getVatRate(), quantity);
        itemList.add(newItem);
        updateSalePriceAndVat();
    }

    /**
     * Public method that updates the sale when an already registered item is scanned.
     * The method first updates the item quantity and then the total price and vat of sale.
     * @param itemID ID of a given item
     * @param itemQuantity amount of the given item
     */
    public void updateExistingItem(int itemID, int quantity) {
        for (int i = 0; i < this.itemList.size(); i++) {
            if (idsAreEqual(itemList.get(i).getItemID(), itemID)) {
                int quantityBeforeUpdate = itemList.get(i).getQuantity();
                RegisteredItemDTO updatedItem = new RegisteredItemDTO(
                    itemID, itemList.get(i).getItemDescription(), itemList.get(i).getPrice(), itemList.get(i).getVatRate(), quantityBeforeUpdate + quantity);
                itemList.set(i, updatedItem);
                break;
            }
        }
        updateSalePriceAndVat();
    }

    /**
     * Method that updates the local attributes totalPrice and totalVAT.
     */
    private void updateSalePriceAndVat(){
        totalPrice = 0;
        totalVAT = 0;
        for (RegisteredItemDTO regItem : itemList) {
            totalPrice += regItem.getTotalPriceOfItemQuantity();
            totalVAT += regItem.getTotalVatOfItemQuantity();
        }
    }

    /**
     * Method that calculates running total including VAT.
     */
    private double calculateRunningTotalIncVat() {
        return this.totalPrice + this.totalVAT;
    }

    /**
     * Public method that creates a new ViewDTO
     * @param itemID identifier of the item that the returned ViewDTO will hold
     * @return a ViewDTO holding the provided item and running total (inc. vat)
     */
    public ViewDTO createViewDTO(int itemID) {
        RegisteredItemDTO regItem = fetchRegisteredItem(itemID);
        double runningTotIncVat = calculateRunningTotalIncVat();
        ViewDTO viewDTO = new ViewDTO(regItem, runningTotIncVat, null);
        return viewDTO;
    }

    /**
     * Method that fetches item from itemList.
     */
    private RegisteredItemDTO fetchRegisteredItem(int itemID){
        for (RegisteredItemDTO regItem : itemList){
            if (idsAreEqual(regItem.getItemID(), itemID)){
                return regItem;
            }
        }
        return null;
    }

    public void addRevenueObserver(RevenueObserver obs) {
        observers.add(obs);
    }

    private void notifyObservers() {
        for (RevenueObserver obs : observers) {
            obs.update(totalRevenue);
        }
    }

    /**
     * Public method that ends the current sale and returns a SaleDTO containing the final information about the registered items and their total price plus Vat
     * @return SaleDTO
     */
    public SaleDTO endSale(){
        RegisteredItemDTO[] itemListArray = itemList.toArray(new RegisteredItemDTO[0]);
        SaleDTO saleDTO = new SaleDTO(totalPrice, totalVAT, itemListArray);
        totalRevenue += totalPrice;
        notifyObservers();

        return saleDTO;
    }
}
