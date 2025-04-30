package se.gows.processsale.model;

import java.time.LocalTime;
import java.util.ArrayList;

import se.gows.processsale.DTO.DiscountDTO;
import se.gows.processsale.DTO.ItemDTO;
import se.gows.processsale.DTO.SummaryDTO;
import se.gows.processsale.DTO.ViewDTO;
import se.gows.processsale.integration.InventoryDBHandler;

public class Sale {
    private LocalTime timeOfSale;
    private ArrayList<RegisteredItem> itemList = new ArrayList<>();
    private double totalPrice;
    private double totalVAT;
    public InventoryDBHandler invHandler;

    /**
     * 
     */
    public Sale(InventoryDBHandler invHandler) {
        timeOfSale = LocalTime.now(); //setTimeOfSale
        this.invHandler = invHandler; // set invHandler
    }

    /**
     * Public method that checks if a scanned item is in itemList
     * Method uses the itemID (int) provided by the controller
     * @param itemID
     * @return
     */
    public boolean checkItemList(int itemID)
    {
        // Step through items in itemList
        for (RegisteredItem regItem : itemList){

            if (itemIdsAreEqual(regItem, itemID)) {
                // ItemID found in itemList -> return true
                return true;
            }
        }
        // Else, itemID not in itemList -> return false
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
        updateSalePriceAndVat(scannedItem.getPriceOfMultipleItems(quantity), scannedItem.getVatOfMultipleItems(quantity));
    }

    public void updateItem(int itemID, int quantity) {
        for (RegisteredItem regItem : itemList) {
            if (regItem.idsAreEqual(itemID)){
                regItem.setQuantity(quantity);
                updateSalePriceAndVat(regItem.getPriceOfMultipleItems(quantity), regItem.getVatOfMultipleItems(quantity));
                break;
            }
        }
    }

    private void updateSalePriceAndVat(double sumPrice, double sumVat){
        totalPrice += sumPrice;
        totalVAT += sumVat;
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
    public ViewDTO createViewDTO(ItemDTO scannedItem) {

        // calculate total inc VAT
        double runningTotIncVat = calculateRunningTotalIncVat();

        // Fetch Registered item from list
        RegisteredItem regItem = getRegisteredItem(scannedItem);

        // Create view DTO
        ViewDTO viewDTO = new ViewDTO(regItem, runningTotIncVat);

        return viewDTO;
    }

    /**
     * public method that fetch an RegisteredItem from itemList
     * @param itemID
     * @return
     */
    public RegisteredItem getRegisteredItem(ItemDTO scannedItem){
        if (scannedItem != null)
            for (RegisteredItem regItem : itemList){
                if (itemIdsAreEqual(regItem, scannedItem.itemID)) {
                    return regItem;
                }
            }
        return null;
    }

    private boolean itemIdsAreEqual(RegisteredItem regItem, int itemID){
        if (regItem.item.itemID == itemID) {
            return true;
        }
        return false;
    }

    /**
     * public method that end sale and update InventoryDB
     * @param itemID
     * @return
     */
    public SummaryDTO endSale(){
        RegisteredItem[] itemListArray = itemList.toArray(new RegisteredItem[0]);

        double totalIncVat = calculateRunningTotalIncVat();

        SummaryDTO sumDTO = new SummaryDTO(timeOfSale, totalPrice, totalVAT, totalIncVat, itemListArray);

        invHandler.updateInventoryDB(itemList);

        return sumDTO;
    }

    public SummaryDTO calculateDiscount(SummaryDTO currentSaleSumDTO, DiscountDTO discount){

        double discountSumTypeOne = discount.discountSumTypeOne;
        double discountRateTypeTwo = 1.0 - discount.discountRateTypeTwo;
        double discountRateTypeThree = 1.0 - discount.discountRateTypeThree;

        if(currentSaleSumDTO.totalIncVat - discountSumTypeOne > 0){

            totalPrice = (currentSaleSumDTO.totalPrice - discountSumTypeOne) * discountRateTypeTwo * discountRateTypeThree;
            currentSaleSumDTO.totalPrice = totalPrice;
            
            currentSaleSumDTO.totalIncVat = calculateRunningTotalIncVat();

        }
        return currentSaleSumDTO;
    }
}
