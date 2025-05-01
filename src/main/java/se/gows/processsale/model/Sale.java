package se.gows.processsale.model;

import java.time.LocalTime;
import java.util.ArrayList;

import se.gows.processsale.DTO.DiscountDTO;
import se.gows.processsale.DTO.ItemDTO;
import se.gows.processsale.DTO.SaleDTO;
import se.gows.processsale.DTO.ViewDTO;

public class Sale {
    private LocalTime timeOfSale;
    private ArrayList<RegisteredItem> itemList = new ArrayList<>();
    private double totalPrice;
    private double totalVAT;

    /**
     * 
     */
    public Sale() {
        timeOfSale = LocalTime.now();
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
     * Private method that adds a new item to itemList
     * @param
     * @return
     */
    public void addItem(ItemDTO item, int quantity) {
        RegisteredItem scannedItem = new RegisteredItem(item, quantity);
        itemList.add(scannedItem);
        updateSalePriceAndVat();
    }

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
     * Private method that create new ViewDTO
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
     * public method that fetch an RegisteredItem from itemList
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
     * public method that end sale and update InventoryDB
     * @param itemID
     * @return
     */
    public SaleDTO endSale(){
        RegisteredItem[] itemListArray = itemList.toArray(new RegisteredItem[0]);
        SaleDTO saleDTO = new SaleDTO(timeOfSale, totalPrice, totalVAT, itemListArray);
        return saleDTO;
    }

    public SaleDTO calculateDiscount(SaleDTO currentSaleDTO, DiscountDTO discount){
        double discountSumTypeOne = discount.discountSumTypeOne;
        double discountRateTypeTwo = 1.0 - discount.discountRateTypeTwo;
        double discountRateTypeThree = 1.0 - discount.discountRateTypeThree;

        if (currentSaleDTO.saleSums.totalIncVat - discountSumTypeOne > 0){
            this.totalPrice = (currentSaleDTO.saleSums.totalPrice - discountSumTypeOne) * discountRateTypeTwo * discountRateTypeThree;
            currentSaleDTO.saleSums.totalPrice = this.totalPrice;
            currentSaleDTO.saleSums.totalIncVat = calculateRunningTotalIncVat();
        }
        return currentSaleDTO;
    }
}
