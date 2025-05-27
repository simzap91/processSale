package se.gows.processsale.model;

import java.util.ArrayList;

import se.gows.processsale.DTO.*;
import se.gows.processsale.utils.Amount;

/**
 * Sale class that represent the sale. A new instance of this class is created every time a new customer enters the checkout.
 */
public class Sale {
    private ArrayList<RegisteredItemDTO> itemList;
    private Amount totalPrice;
    private Amount totalVAT;

    /**
     * Creates a new Sale object with an empty item list and a total price and total Vat set to zero.
     * 
     * @param itemList list with all scanned items
     * @param totalPrice running total price of the sale
     * @param totalVAT running total vat (sum) of the sale
     */
    public Sale(){
        this.itemList = new ArrayList<>();
        this.totalPrice = new Amount(0);
        this.totalVAT = new Amount(0);
    }

    /**
     * Public method that checks if a scanned item is in itemList.
     * 
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
     * Public method that adds a new item to itemList including its quantity
     * 
     * @param item itemDTO containing information about the item
     * @param quantity quantity of the given item
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
     * 
     * @param itemID ID of a given item
     * @param itemQuantity quantity of the given item
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
        double newTotalPrice = 0;
        double newTotalVAT = 0;
        for (RegisteredItemDTO regItem : itemList) {
            newTotalPrice += regItem.getTotalPriceOfItemQuantity();
            newTotalVAT += regItem.getTotalVatOfItemQuantity();
            this.totalPrice.setAmount(newTotalPrice);
            this.totalVAT.setAmount(newTotalVAT);
        }
    }

    /**
     * Method that calculates running total including VAT.
     */
    private Amount calculateRunningTotalIncVat() {
        return new Amount(this.totalPrice.getValue() + this.totalVAT.getValue());
    }

    /**
     * Public method that creates a new ViewDTO
     * 
     * @param itemID identifier of the item that the returned ViewDTO will hold
     * @return a ViewDTO holding the provided item and running total (inc. vat)
     */
    public ViewDTO createViewDTO(int itemID) {
        RegisteredItemDTO regItem = fetchRegisteredItem(itemID);
        Amount runningTotIncVat = calculateRunningTotalIncVat();
        ViewDTO viewDTO = new ViewDTO(regItem, runningTotIncVat);
        return viewDTO;
    }

    /**
     * Method that fetches an item from itemList.
     * 
     * @param itemID Id of the item that should be fetched from the itemList
     */
    private RegisteredItemDTO fetchRegisteredItem(int itemID){
        for (RegisteredItemDTO regItem : itemList){
            if (idsAreEqual(regItem.getItemID(), itemID)){
                return regItem;
            }
        }
        return null;
    }

    /**
     * Public method that ends the current sale and returns a SaleDTO containing the final information 
     * about the registered items and their total price plus Vat
     * 
     * @return SaleDTO saleSummary.
     */
    public SaleDTO endSale(){
        RegisteredItemDTO[] itemListArray = itemList.toArray(new RegisteredItemDTO[0]);
        SaleDTO saleSummary = new SaleDTO(totalPrice, totalVAT, itemListArray);
        return saleSummary;
    }
}
