package se.gows.processsale.controller;

import se.gows.processsale.DTO.ItemDTO;
import se.gows.processsale.DTO.SaleDTO;
import se.gows.processsale.DTO.ViewDTO;
import se.gows.processsale.integration.*;
import se.gows.processsale.model.*;

/**
 * This is the application's only controller. All calls to the model pass through this class.
 */
public class Controller {
    private InventoryDBHandler invHandler;
    private AccountingDBHandler accHandler;
    private DiscountDBHandler discHandler;
    private Sale currentSale;
    private SaleDTO currentSaleDTO;
    private CashRegister cashRegister;

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
        currentSale = new Sale();
    }

    /**
     * This method first checks if item is registered in the sale. If not, it creates a new ItemDTO and sends to the Sale object.
     * If item already in the Sale object, it updates the sale with new item quantity and total price/Vat.
     * @param itemID item identifier of scanned item
     * @param quantity quantity of scanned item
     * @return  ViewDTO, which contains the last scanned item and running total (inc VAT)
     */
    public ViewDTO scanItem(int itemID, int quantity) {
        boolean itemRegistered;

        itemRegistered = currentSale.checkItemList(itemID);

        if (!itemRegistered) {
            fetchNewItemDTOAndSendToSale(itemID, quantity);
        } else {
            currentSale.updateSale(itemID, quantity);
        }
        
        ViewDTO viewDTO = currentSale.createViewDTO(itemID);
        return viewDTO;
    }

    /**
     * This method fetch a new ItemDTO from Inventory handler. Then it sends it to the Sale object.
     * @param itemID item identifier of scanned item
     * @param quantity quantity of scanned item
     */
    private void fetchNewItemDTOAndSendToSale(int itemID, int quantity){
        ItemDTO scannedItem = invHandler.fetchItemFromDB(itemID);
        if (scannedItem != null) {
            currentSale.addItem(scannedItem, quantity);
        }
    }

    /**
     * This method ends the sale, creates a SaleDTO and then updates the inventory.
     * @return SaleDTO, which contains information about the sale.
     */
    public SaleDTO endSale() {
        SaleDTO saleDTO = currentSale.endSale();
        currentSaleDTO = saleDTO;
        invHandler.updateInventoryDB(currentSaleDTO.getItemList());
        return currentSaleDTO;
    }

    /**
     * This method passes a discount request to the discount handler.
     * @param customerID used to check if customer is member
     * @param currentSaleDTO contains information about the current sale
     * @param discTypes contains the requested discount types
     * @return SaleDTO with updated information about the sale (after the discount)
     */
    public SaleDTO requestDiscount(int customerID, SaleDTO currentSaleDTO, int[] discTypes){
        double discountedTotalPrice = discHandler.getDiscountedPrice(discTypes, customerID, currentSaleDTO.getItemList(), currentSaleDTO.getSaleSums().totalPrice);
        currentSaleDTO.getSaleSums().totalPrice = discountedTotalPrice;
        currentSaleDTO.getSaleSums().updateTotIncVat();
        return currentSaleDTO;
    }

    /**
     * Takes a paid amount and creates a new transaction and a new receipt. The method then register these objects in the cash register
     * and updates the Accounting DB.
     * @param payment sale payment
     */
    public void registerPayment(Amount payment){
        Transaction trans = new Transaction(payment, currentSaleDTO.getSaleSums().totalIncVat);
        Receipt receipt = createReceipt(currentSaleDTO, trans);
        cashRegister = new CashRegister(trans, receipt);
        accHandler.updateAccountBalance(cashRegister.receipt);
    }

    /**
     * Creates new receipt.
     * @param saleDTO sale data
     * @param trans object that holds the transaction
     * @return receipt
     */
    private Receipt createReceipt(SaleDTO saleDTO, Transaction trans) {
        Receipt receipt = new Receipt(saleDTO, trans);
        return receipt;
    }

    /**
     * Prints receipt to console.
     */
    public void printReceipt(){
        cashRegister.printReceipt();
    }
}
