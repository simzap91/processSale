package se.gows.processsale.controller;

import se.gows.processsale.DTO.DiscountDTO;
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
     * Passes the scanned item to Sale object.
     * Also checks if item is registered in the sale. If not, it passes it to inventory handler (which create a new itemDTO and sends back).
     * @param itemID item identifier of scanned item
     * @param quantity quantity of scanned item
     * @return  viewDTO that shows the last scanned item and running total (inc VAT)
     */
    public ViewDTO scanItem(int itemID, int quantity) {
        boolean itemRegistered;
        ViewDTO viewDTO;
        ItemDTO scannedItem = null;

        itemRegistered = currentSale.checkItemList(itemID);

        if (!itemRegistered) {
            scannedItem = invHandler.fetchItemFromDB(itemID);

            if (scannedItem != null) {
                currentSale.addItem(scannedItem, quantity);
            }
        } else {
            currentSale.updateSale(itemID, quantity);
        }
        viewDTO = currentSale.createViewDTO(scannedItem);
        return viewDTO;
    }

    /**
     * Calls endSale()-method in sale object.
     * Returns a summary of the sale (to be displayed in View).
     * @return summaryDTO that contains time of sale, total price, total VAT, total price inc VAT, list of purchased items
     */
    public SaleDTO endSale() {
        SaleDTO saleDTO = currentSale.endSale();
        currentSaleDTO = saleDTO;
        invHandler.updateInventoryDB(currentSaleDTO.itemList);
        return currentSaleDTO;
    }

    /**
     * Takes a discount request from customer and passes to Discount DB handler
     * @param customerID customerID to check if customer is member
     * @param currentSaleDTO summary of the ended sale
     * @param discTypes 
     * @return summaryDTO that contains the updated sale summary after discount
     */
    public SaleDTO requestDiscount(int customerID, SaleDTO currentSaleDTO, int[] discTypes){
            DiscountDTO discount = discHandler.fetchDiscount(discTypes, customerID, currentSaleDTO.itemList, currentSaleDTO.saleSums.totalIncVat);
            currentSaleDTO = currentSale.calculateDiscount(currentSaleDTO, discount);
        return currentSaleDTO;
    }

    /**
     * Creates new transaction object from payed amount
     * @param payment payment payed by customer
     */
    public void registerPayment(Amount payment){
        Transaction trans = new Transaction(payment, currentSaleDTO.saleSums.totalIncVat);
        Receipt receipt = createReceipt(currentSaleDTO, trans);
        cashRegister = new CashRegister(trans, receipt);
        accHandler.updateAccountBalance(cashRegister.receipt);
    }

    /**
     * Creates new receipt
     * @param saleDTO summary of the sale
     * @param trans object that holds the transaction
     * @return new receipt of the sale
     */
    public Receipt createReceipt(SaleDTO saleDTO, Transaction trans) {
        Receipt receipt = new Receipt(saleDTO, trans);
        return receipt;
    }

    public void printReceipt(){
        cashRegister.printReceipt();
    }
}
