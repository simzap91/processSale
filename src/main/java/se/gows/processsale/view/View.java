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
 * This class also declares test items, test customerIds and requested test discount types to the sale simulation.
 * The class includes one method - testRun - that simulates two sales.
 * The class catches the only two exceptions that can reach this level: ItemidNotFoundException and DatabaseFailureException.
 */
public class View {
    private Controller ctrl;

    /**
     * Creates a new instance that uses the specified controller for all calls to other layers.
     * 
     * @param ctrl The controller to use for all calls to other layers
     */
    public View(Controller ctrl){
        this.ctrl = ctrl;
    }

    /**
     * Some values used to simulate a test run with two sales/customers.
     */
    int[][][] shoppingCarts = {{{20,1},{404,2},{1,2}},{{1,1},{2,1},{3,5}}};
    int[] customerIds = {1, 2};
    DiscountTypes[] discountRequests = {DiscountTypes.ITEMS, DiscountTypes.SALE, DiscountTypes.CUSTOMER};
    Amount[] payments = {new Amount(100), new Amount(200)};

    /**
     * Runs simulation of sale process.
     */
    public void runTestWithTwoCustomers() {

        int customersInQueue = 2;

        while (customersInQueue > 0) {

            ctrl.startSale();
        
            System.out.println();
            System.out.println("-------------------------------------");
            System.out.println("A new sale has been started.");
            System.out.println();

            int[][] shoppingCart = shoppingCarts[2 - customersInQueue];
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
                    System.out.println("Error: Invalid item id." + "\n");
                } catch (DatabaseFailureException e) {
                    System.out.println("Error: Not able to connect to database. Try again later." + "\n");
                }
                scannedItemsCount ++;
            }

            SaleDTO saleSummary = ctrl.endSale();

            System.out.println("-------------------------------------");
            System.out.println("Sale ended");
            System.out.println();
            System.out.println("Total (inc. VAT): " + String.format(Locale.US, "%.2f",saleSummary.getSaleSums().getTotalIncVat().getValue()) + "kr");

            int customerId = customerIds[2 - customersInQueue];
            DiscountTypes[] requestedDiscountTypes = discountRequests;

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

            Amount payment = payments[2 - customersInQueue];
            ctrl.registerPayment(payment, saleSummary);

            customersInQueue --;
        }
    }
}