package se.gows.processsale.view;
import java.util.Locale;

import se.gows.processsale.DTO.SaleDTO;
import se.gows.processsale.DTO.ViewDTO;
import se.gows.processsale.controller.*;
import se.gows.processsale.integration.ItemIdNotFoundException;
import se.gows.processsale.model.*;
import se.gows.processsale.utils.FileLogger;
import se.gows.processsale.utils.TotalRevenueFileOutput;
import se.gows.processsale.utils.DiscountTypes;

/**
 * View class that represents the user (cashier) display. 
 * This class also declares test items, a test customerId and a test discount request to the sale simulation.
 */
public class View {
    private Controller ctrl;
    private FileLogger logger;

    /**
     * Creates a new instance that uses the specified controller for all calls to other layers.
     * @param ctrl The controller to use for all calls to other layers
     */
    public View(Controller ctrl){
        this.ctrl = ctrl;
        ctrl.addSumOfCostObserver(new TotalRevenueView());
        ctrl.addSumOfCostObserver(new TotalRevenueFileOutput("revenueFileOutput.txt"));
        this.logger = new FileLogger();
    }

    /**
     * Runs simulation of sale process.
     */
    public void runFakeExecution() {
   
        ctrl.startSale();
        
        System.out.println();
        System.out.println("-------------------------------------");
        System.out.println("A new sale has been started.");
        System.out.println();

            int[][] arrItemsToBeScanned = {{753753959,1},{404,2},{1,3},{1,5}};
            int itemsScannedCount = 0;
            while (itemsScannedCount < arrItemsToBeScanned.length){

                int itemId = arrItemsToBeScanned[itemsScannedCount][0];
                int quantity = arrItemsToBeScanned[itemsScannedCount][1];

                try {
                    ViewDTO viewDTO = ctrl.scanItem(itemId, quantity);
                    if (viewDTO.getErrorMessage() != null) {
                        logger.log(viewDTO.getErrorMessage());
                        System.out.println(viewDTO.getErrorMessage());
                    } else {
                        System.out.println("Add " + quantity + " item with itemId: " + itemId);
                        System.out.println("Item ID: " + viewDTO.getRegItem().getItemID());
                        System.out.println("Item name: " + viewDTO.getRegItem().getItemDescription());
                        System.out.println("Item cost: " + viewDTO.getRegItem().getPrice() + "kr");
                        System.out.println("VAT: " + (int)(100 * viewDTO.getRegItem().getVatRate()) + "%");
                        System.out.println();
                        System.out.println("Running total (inc. VAT): " + String.format(Locale.US, "%.2f",viewDTO.getRunningTotalIncVat().getValue()) + "kr");
                        System.out.println();
                    }
                } catch (ItemIdNotFoundException exc) {
                    System.out.println("Error: " + exc.getMessage());
                    logger.log(exc.getMessage());
                }
                itemsScannedCount ++;
            }

        SaleDTO currentSaleDTO = ctrl.endSale();
        System.out.println("-------------------------------------");
        System.out.println("Sale ended");
        System.out.println();
        System.out.println("Total (inc. VAT): " + String.format(Locale.US, "%.2f",currentSaleDTO.getSaleSums().getTotalIncVat().getValue()) + "kr");

        CustomerId customerId = new CustomerId(1);
        DiscountTypes[] requestedDiscounts = {DiscountTypes.ITEMS, DiscountTypes.SALE, DiscountTypes.CUSTOMER};
        System.out.println("-------------------------------------");
        System.out.println("Discount requested.");
        System.out.println("Customer ID: " + customerId);
        System.out.print("Discount types: ");
        for (DiscountTypes type : requestedDiscounts) {
            System.out.print(type + ", ");
        }
        System.out.println();
 
        currentSaleDTO = ctrl.requestDiscount(customerId, currentSaleDTO, requestedDiscounts);
       
        System.out.println("-------------------------------------");
        System.out.println("Total (inc. VAT) after discount: " + String.format(Locale.US, "%.2f",currentSaleDTO.getSaleSums().getTotalIncVat().getValue()) + "kr");
        System.out.println("-------------------------------------");
        System.out.println();

        Amount payment = new Amount(200);
        ctrl.registerPayment(payment, currentSaleDTO);
    }

    public void runFakeExecutionAgain() {
   
        ctrl.startSale();
        
        System.out.println();
        System.out.println("-------------------------------------");
        System.out.println("A new sale has been started.");
        System.out.println();

            int[][] arrItemsToBeScanned = {{1,1},{2,1},{3,5}};
            int itemsScannedCount = 0;
            while (itemsScannedCount < arrItemsToBeScanned.length){

                int itemId = arrItemsToBeScanned[itemsScannedCount][0];
                int quantity = arrItemsToBeScanned[itemsScannedCount][1];

                try {
                    ViewDTO viewDTO = ctrl.scanItem(itemId, quantity);
                    if (viewDTO.getErrorMessage() != null) {
                        System.out.println(viewDTO.getErrorMessage());
                        logger.log(viewDTO.getErrorMessage());
                    } else {
                        System.out.println("Add " + quantity + " item with itemId: " + itemId);
                        System.out.println("Item ID: " + viewDTO.getRegItem().getItemID());
                        System.out.println("Item name: " + viewDTO.getRegItem().getItemDescription());
                        System.out.println("Item cost: " + viewDTO.getRegItem().getPrice() + "kr");
                        System.out.println("VAT: " + (int)(100 * viewDTO.getRegItem().getVatRate()) + "%");
                        System.out.println();
                        System.out.println("Running total (inc. VAT): " + String.format(Locale.US, "%.2f",viewDTO.getRunningTotalIncVat().getValue()) + "kr");
                        System.out.println();
                    }
                } catch (ItemIdNotFoundException exc) {
                    System.out.println("Error: " + exc.getMessage());
                    logger.log(exc.getMessage());
                }
                itemsScannedCount ++;
            }

        SaleDTO currentSaleDTO = ctrl.endSale();
        System.out.println("-------------------------------------");
        System.out.println("Sale ended");
        System.out.println();
        System.out.println("Total (inc. VAT): " + String.format(Locale.US, "%.2f",currentSaleDTO.getSaleSums().getTotalIncVat().getValue()) + "kr");

        CustomerId customerId = new CustomerId(1);
        DiscountTypes[] requestedDiscounts = {DiscountTypes.ITEMS, DiscountTypes.SALE, DiscountTypes.CUSTOMER};
        System.out.println("-------------------------------------");
        System.out.println("Discount requested.");
        System.out.println("Customer ID: " + customerId);
        System.out.print("Discount types: ");
        for (DiscountTypes type : requestedDiscounts) {
            System.out.print(type + ", ");
        }
        System.out.println();
 
        currentSaleDTO = ctrl.requestDiscount(customerId, currentSaleDTO, requestedDiscounts);
       
        System.out.println("-------------------------------------");
        System.out.println("Total (inc. VAT) after discount: " + String.format(Locale.US, "%.2f",currentSaleDTO.getSaleSums().getTotalIncVat().getValue()) + "kr");
        System.out.println("-------------------------------------");
        System.out.println();

        Amount payment = new Amount(200);
        ctrl.registerPayment(payment, currentSaleDTO);
    }

    
}