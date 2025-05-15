package se.gows.processsale.controller;

import java.util.ArrayList;

import se.gows.processsale.DTO.ItemDTO;
import se.gows.processsale.DTO.SaleDTO;
import se.gows.processsale.DTO.ViewDTO;
import se.gows.processsale.integration.*;
import se.gows.processsale.model.*;
import se.gows.processsale.utils.SumOfCostsObserver;

/**
 * This is the application's only controller. All calls to the model pass through this class.
 */
public class Controller {
    private InventoryDBHandler invHandler;
    private AccountingDBHandler accHandler;
    private DiscountDBHandler discHandler;
    private Sale currentSale;
    private CashRegister cashRegister;
    private ArrayList<SumOfCostsObserver> sumOfCostsObservers;

    public Controller(InventoryDBHandler invHandler, 
                        AccountingDBHandler accHandler, 
                        DiscountDBHandler discHandler)
    {
        this.invHandler = invHandler;
        this.accHandler = accHandler;
        this.discHandler = discHandler;
        this.sumOfCostsObservers = new ArrayList<>();
    }

    /**
     * Starts a new sale. This method must be called first in the process.
     */
    public void startSale() {
        currentSale = new Sale();
        cashRegister = new CashRegister();
    }

    /**
     * This method first checks if item is registered in the sale. If not, it creates a new ItemDTO and sends to the Sale object.
     * If item already in the Sale object, it updates the sale with new item quantity and total price/Vat.
     * @param itemID item identifier of scanned item
     * @param quantity quantity of scanned item
     * @return  ViewDTO, which contains the last scanned item and running total (inc VAT)
     */
    public ViewDTO scanItem(int itemID, int quantity) throws ItemIdNotFoundException { 
        try {
            boolean itemIsRegistered = currentSale.isItemInItemList(itemID);
            if (!itemIsRegistered) {
                ItemDTO newItem = invHandler.fetchItemFromInventory(itemID);
                currentSale.addNewItem(newItem, quantity);
            } else {
                currentSale.updateExistingItem(itemID, quantity);
            }
            return currentSale.createViewDTO(itemID);
        } catch (DatabaseFailureException exc) {
            ViewDTO error = new ViewDTO(null, null, "Problem when calling the inventory.\n");
            return error;
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
     * @param discTypes contains the requested discount types
     * @return SaleDTO with updated information about the sale (after the discount)
     */
    public SaleDTO requestDiscount(int customerID, SaleDTO currentSaleDTO, int[] discTypes){
        Amount discountedTotalPrice = discHandler.getDiscountedPrice(discTypes, customerID, currentSaleDTO.getItemList(), currentSaleDTO.getSaleSums().getTotalPrice());
        SaleDTO updatedSaleDTO = new SaleDTO(discountedTotalPrice, currentSaleDTO.getSaleSums().getTotalVAT(), currentSaleDTO.getItemList());
        return updatedSaleDTO;
    }

    /**
     * Takes a paid amount and creates a new transaction and a new receipt. The method then register these objects in the cash register
     * and updates the Accounting DB.
     * @param payment sale payment
     */
    public void registerPayment(Amount payment, SaleDTO currentSaleDTO){
        cashRegister.registerPayment(payment, currentSaleDTO);
        accHandler.updateAccountBalance(cashRegister.getReceipt());
        notifyObservers(currentSaleDTO);
    }

    public void addSumOfCostObserver(SumOfCostsObserver obs) {
        sumOfCostsObservers.add(obs);
    }

    private void notifyObservers(SaleDTO currentSaleDTO) {
        for (SumOfCostsObserver obs : sumOfCostsObservers) {
            obs.newSumOfCost(currentSaleDTO.getSaleSums().getTotalIncVat());
        }
    }
}
