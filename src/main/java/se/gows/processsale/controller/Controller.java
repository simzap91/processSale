package se.gows.processsale.controller;

import se.gows.processsale.DTO.ItemDTO;
import se.gows.processsale.DTO.SummaryDTO;
import se.gows.processsale.DTO.ViewDTO;
import se.gows.processsale.integration.*;
import se.gows.processsale.model.Receipt;
import se.gows.processsale.model.Sale;
import se.gows.processsale.DTO.SummaryDTO;

/**
 * This is the application's only controller. All calls to the model pass through this class.
 */
public class Controller {
    private InventoryDBHandler invHandler;
    private AccountingDBHandler accHandler;
    private DiscountDBHandler discHandler;
    private Sale currentSale;
    private SummaryDTO currentSaleSummaryDTO;

    public Controller(InventoryDBHandler invHandler, 
                        AccountingDBHandler accHandler, 
                        DiscountDBHandler discHandler) 
    {
        this.invHandler = invHandler;
        this.accHandler = accHandler;
        this.discHandler = discHandler;
    }

    /**
     * Starts a new sale. This method must be called first in the process.
     */
    public void startSale() {
        currentSale = new Sale(invHandler);
    }

    /**
     * Takes scanned itemID and passes it further to the Sale object. 
     * Also checks if item is registered in the sale. If not, it passes it to inventory handler (which create an itemDTO and sends back).
     */
    public ViewDTO scanItem(int itemID, int quantity) {
        boolean itemRegistered;
        ViewDTO viewDTO;
        ItemDTO scannedItem;

        // Check if item exist in sale
        itemRegistered = currentSale.checkItemList(itemID);

        // If item not registered, fetch item from DB and add to itemList
        if (!itemRegistered) {
            // Fetch item from DB
            scannedItem = invHandler.fetchItemFromDB(itemID);

            // Add new item to list
            currentSale.addItem(scannedItem, quantity);
        } else {
            // If item already registered, update item quantity in itemList
            currentSale.updateItem(itemID, quantity);
        }

        // Fetch ViewDTO for cashier display
        viewDTO = currentSale.createViewDTO(itemID);
        return viewDTO;
    }

    /**
     * Calls endSale()-method in sale object.
     * Returns a summary of the sale (to be displayed in View).
     */
    public SummaryDTO endSale() {

        // End sale and create sumDTO
        SummaryDTO sumDTO = currentSale.endSale();

        // Set local attribute
        currentSaleSummaryDTO = sumDTO;

        return currentSaleSummaryDTO;
    }


    
    public Receipt createReceipt(SummaryDTO summaryDTO, Transaction trans) {
        Receipt receipt = new Receipt(summaryDTO, trans);
        return receipt;
    }

}
