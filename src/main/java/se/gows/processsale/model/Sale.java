package se.gows.processsale.model;

import java.time.LocalTime;
import java.util.ArrayList;

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
            
        // Create a new RegisteredItem
        RegisteredItem registeredItem = new RegisteredItem(item, quantity);

        // Add registered item to list
        itemList.add(registeredItem);

        // Update running total
        totalPrice += item.price * quantity;
        // Update total VAT
        totalVAT += item.price * item.vatRate * quantity;
    }

    public void updateItem(int itemID, int quantity) {

        // If item already in list, update item quantity
        for (RegisteredItem regItem : itemList) {

            // Step thorugh the list until item is found
            if (itemIdsAreEqual(regItem, itemID)) { // Eventuellt göra om till metod
                // Update quantity
                regItem.setQuantity(regItem.quantity + quantity);

                // Update running total
                totalPrice += regItem.item.price * quantity;
                // Update total VAT
                totalVAT += regItem.item.price * regItem.item.vatRate * quantity;

                // Break loop
                break;
            }
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

        // calculate total inc VAT
        double runningTotIncVat = calculateRunningTotalIncVat();

        // Fetch Registered item from list
        RegisteredItem regItem = getRegisteredItem(itemID);

        // Create view DTO
        ViewDTO viewDTO = new ViewDTO(regItem, runningTotIncVat);

        return viewDTO;
    }

    /**
     * public method that fetch an RegisteredItem from itemList
     * @param itemID
     * @return
     */
    public RegisteredItem getRegisteredItem(int itemID){
        for (RegisteredItem regItem : itemList){
            if (itemIdsAreEqual(regItem, itemID)) {
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

        // Convert itemList to array
        RegisteredItem[] itemListArray = itemList.toArray(new RegisteredItem[0]);

        // Calculate total inc VAT
        double totalIncVat = calculateRunningTotalIncVat();

        // Create summaryDTO
        SummaryDTO sumDTO = new SummaryDTO(timeOfSale, totalPrice, totalVAT, totalIncVat, itemListArray);

        // Update inventoryDB
        invHandler.updateInventoryDB(itemList);

        return sumDTO;
    }
}
