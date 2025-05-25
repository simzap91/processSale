package se.gows.processsale.view;
import java.util.Locale;

import se.gows.processsale.DTO.SaleDTO;
import se.gows.processsale.DTO.ViewDTO;
import se.gows.processsale.controller.*;
import se.gows.processsale.integration.ItemIdNotFoundException;
import se.gows.processsale.utils.DiscountTypes;
import se.gows.processsale.utils.Amount;

/**
 * View class that represents the user (cashier) display. 
 * This class also declares test items, a test customerId and requested test discount types to the sale simulation.
 * The class includes two methods that simulates sales - runSaleCustomerOne and runSaleCustomerTwo.
 * The class catches the only two exceptions that can reach this level: ItemidNotFoundException and DatabaseFailureException.
 */
public class View {
    private Controller ctrl;

    /**
     * Creates a new instance that uses the specified controller for all calls to other layers.
     * @param ctrl The controller to use for all calls to other layers
     */
    public View(Controller ctrl){
        this.ctrl = ctrl;
    }

    /**
     * Runs simulation of sale process.
     */
    public void runSaleCustomerOne() {
   
        ctrl.startSale();
        
        System.out.println();
        System.out.println("-------------------------------------");
        System.out.println("A new sale has been started.");
        System.out.println();

            int[][] shoppingCart = {{753753959,1},{404,2},{1,3}};
            int scannedItemsCount = 0;
            while (scannedItemsCount < shoppingCart.length){

                int itemId = shoppingCart[scannedItemsCount][0];
                int quantity = shoppingCart[scannedItemsCount][1];

                try {
                    ViewDTO viewDTO = ctrl.scanItem(itemId, quantity);
                    System.out.println("Add " + quantity + " item with itemId: " + itemId);
                    System.out.println("Item ID: " + viewDTO.getRegItem().getItemID());
                    System.out.println("Item name: " + viewDTO.getRegItem().getItemDescription());
                    System.out.println("Item cost: " + viewDTO.getRegItem().getPrice() + "kr");
                    System.out.println("VAT: " + (int)(100 * viewDTO.getRegItem().getVatRate()) + "%");
                    System.out.println();
                    System.out.println("Running total (inc. VAT): " + String.format(Locale.US, "%.2f",viewDTO.getRunningTotalIncVat().getValue()) + "kr");
                    System.out.println();
                } catch (ItemIdNotFoundException e) {
                    System.out.println("Error: " + e.getMessage() + "\n");
                } catch (DatabaseFailureException e) {
                    System.out.println("Error: " + e.getMessage() + "\n");
                }
                scannedItemsCount ++;
            }

        SaleDTO saleSummary = ctrl.endSale();
        System.out.println("-------------------------------------");
        System.out.println("Sale ended");
        System.out.println();
        System.out.println("Total (inc. VAT): " + String.format(Locale.US, "%.2f",saleSummary.getSaleSums().getTotalIncVat().getValue()) + "kr");

        int customerId = 1;
        DiscountTypes[] requestedDiscountTypes = {DiscountTypes.ITEMS, DiscountTypes.SALE, DiscountTypes.CUSTOMER};
        System.out.println("-------------------------------------");
        System.out.println("Discount requested.");
        System.out.println("Customer ID: " + customerId);
        System.out.print("Discount types: ");
        for (DiscountTypes type : requestedDiscountTypes) {
            System.out.print(type + ", ");
        }
        System.out.println();
        saleSummary = ctrl.requestDiscount(customerId, saleSummary, requestedDiscountTypes);
       
        System.out.println("-------------------------------------");
        System.out.println("Total (inc. VAT) after discount: " + String.format(Locale.US, "%.2f",saleSummary.getSaleSums().getTotalIncVat().getValue()) + "kr");
        System.out.println("-------------------------------------");
        System.out.println();

        Amount payment = new Amount(100);
        ctrl.registerPayment(payment, saleSummary);
    }

    public void runSaleCustomerTwo() {
   
        ctrl.startSale();
        
        System.out.println();
        System.out.println("-------------------------------------");
        System.out.println("A new sale has been started.");
        System.out.println();

            int[][] shoppingCart = {{1,1},{2,1},{3,5}};
            int scannedItemsCount = 0;
            while (scannedItemsCount < shoppingCart.length){

                int itemId = shoppingCart[scannedItemsCount][0];
                int quantity = shoppingCart[scannedItemsCount][1];

                try {
                    ViewDTO viewDTO = ctrl.scanItem(itemId, quantity);
                    System.out.println("Add " + quantity + " item with itemId: " + itemId);
                    System.out.println("Item ID: " + viewDTO.getRegItem().getItemID());
                    System.out.println("Item name: " + viewDTO.getRegItem().getItemDescription());
                    System.out.println("Item cost: " + viewDTO.getRegItem().getPrice() + "kr");
                    System.out.println("VAT: " + (int)(100 * viewDTO.getRegItem().getVatRate()) + "%");
                    System.out.println();
                    System.out.println("Running total (inc. VAT): " + String.format(Locale.US, "%.2f",viewDTO.getRunningTotalIncVat().getValue()) + "kr");
                    System.out.println();
                } catch (ItemIdNotFoundException e) {
                    System.out.println("Error: " + e.getMessage() + "\n");
                } catch (DatabaseFailureException e) {
                    System.out.println("Error: " + e.getMessage() + "\n");
                }
                scannedItemsCount ++;
            }

        SaleDTO saleSummary = ctrl.endSale();
        System.out.println("-------------------------------------");
        System.out.println("Sale ended");
        System.out.println();
        System.out.println("Total (inc. VAT): " + String.format(Locale.US, "%.2f",saleSummary.getSaleSums().getTotalIncVat().getValue()) + "kr");

        int customerId = 2;
        DiscountTypes[] requestedDiscountTypes = {DiscountTypes.ITEMS, DiscountTypes.SALE, DiscountTypes.CUSTOMER};
        System.out.println("-------------------------------------");
        System.out.println("Discount requested.");
        System.out.println("Customer ID: " + customerId);
        System.out.print("Discount types: ");
        for (DiscountTypes type : requestedDiscountTypes) {
            System.out.print(type + ", ");
        }
        System.out.println();
        saleSummary = ctrl.requestDiscount(customerId, saleSummary, requestedDiscountTypes);
       
        System.out.println("-------------------------------------");
        System.out.println("Total (inc. VAT) after discount: " + String.format(Locale.US, "%.2f",saleSummary.getSaleSums().getTotalIncVat().getValue()) + "kr");
        System.out.println("-------------------------------------");
        System.out.println();

        Amount payment = new Amount(200);
        ctrl.registerPayment(payment, saleSummary);
    }
}