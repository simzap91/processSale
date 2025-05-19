package se.gows.processsale.controller;

import se.gows.processsale.DTO.DiscountRequestDTO;
import se.gows.processsale.DTO.ItemDTO;
import se.gows.processsale.DTO.SaleDTO;
import se.gows.processsale.DTO.ViewDTO;
import se.gows.processsale.integration.*;
import se.gows.processsale.model.*;
import se.gows.processsale.utils.DiscountTypes;
import se.gows.processsale.utils.FileLogger;
import se.gows.processsale.utils.ObserversList;

/**
 * This is the application's only controller. All calls to the model pass through this class.
 */
public class Controller {
    private InventoryDBHandler invHandler;
    private AccountingDBHandler accHandler;
    private DiscountDBHandler discHandler;
    private Sale currentSale;
    private CashRegister cashRegister;
    private FileLogger logger;
    private ObserversList obsList;

    public Controller(InventoryDBHandler invHandler, 
                        AccountingDBHandler accHandler, 
                        DiscountDBHandler discHandler,
                        ObserversList obsList)
    {
        this.invHandler = invHandler;
        this.accHandler = accHandler;
        this.discHandler = discHandler;
        this.logger = new FileLogger();
        this.obsList = obsList;
    }

    /**
     * Starts a new sale. This method must be called first in the process.
     * This method also initializes a cash register that handles the payment and receipt of the sale.
     */
    public void startSale() {
        currentSale = new Sale();
        cashRegister = new CashRegister(obsList);
    }

    /**
     * This method first checks if item is registered in the sale. If not, it creates a new ItemDTO and sends to the Sale object.
     * If item already in the Sale object, it updates the sale with new item quantity and total price/Vat.
     * @param itemID item identifier of scanned item
     * @param quantity quantity of scanned item
     * @return ViewDTO, which contains the last scanned item and running total (inc VAT)
     */
    public ViewDTO scanItem(int itemID, int quantity) throws ItemIdNotFoundException, DatabaseFailureException { 

        try {
            boolean itemIsRegistered = currentSale.isItemInItemList(itemID);
            if (!itemIsRegistered) {
                ItemDTO newItem = invHandler.fetchItemFromInventory(itemID);
                currentSale.addNewItem(newItem, quantity);
            } else {
                currentSale.updateExistingItem(itemID, quantity);
            }
            return currentSale.createViewDTO(itemID);
        } catch (DatabaseNotRunningException e) {
            logger.log(e.getMessage());
            throw new DatabaseFailureException("Unable to connect to database.", e);
        }
    }

    /**
     * This method ends the sale, creates a SaleDTO and then updates the inventory.
     * @return SaleDTO, which contains information about the sale.
     */
    public SaleDTO endSale() {
        invHandler.updateInventory(currentSale.endSale().getItemList());
        return currentSale.endSale();
    }

    /**
     * This method passes a discount request to the discount handler.
     * @param customerID used to check if customer is member
     * @param currentSaleDTO contains information about the current sale
     * @param requestedDiscountTypes contains the requested discount types
     * @return SaleDTO with updated information about the sale (after the discount)
     */
    public SaleDTO requestDiscount(CustomerId customerId, SaleDTO currentSaleDTO, DiscountTypes[] requestedDiscountTypes){
        DiscountRequestDTO discRequestDTO = new DiscountRequestDTO(customerId, currentSaleDTO.getItemList(), currentSaleDTO.getSaleSums().getTotalPrice());
        Amount discountedTotalPrice = discHandler.getDiscountedPrice(requestedDiscountTypes, discRequestDTO);
        SaleDTO discountedSaleDTO = new SaleDTO(discountedTotalPrice, currentSaleDTO.getSaleSums().getTotalVAT(), currentSaleDTO.getItemList());
        return discountedSaleDTO;
    }

    /**
     * Takes a paid amount and creates a new transaction and a new receipt. The method then register these objects in the cash register
     * and updates the Accounting DB.
     * @param payment sale payment
     * @param currentSaleDTO saleDTO
     */
    public void registerPayment(Amount payment, SaleDTO currentSaleDTO){
        cashRegister.registerPayment(payment, currentSaleDTO);
        accHandler.updateAccountBalance(cashRegister.getReceipt());
    }

}
