package se.gows.processsale.view;
import se.gows.processsale.DTO.SaleDTO;
import se.gows.processsale.DTO.ViewDTO;
import se.gows.processsale.controller.*;
import se.gows.processsale.model.*;


/**
     * View class that represents the user (cashier) display. 
     * This class also declares test items, a test customerId and a test discount request to the sale simulation.
     */
public class View {
    private Controller ctrl;
    boolean itemsLeft = true;

    /**
     * Creates a new instance that uses the specified controller for all calls to other layers.
     * @param ctrl The controller to use for all calls to other layers
     */
    public View(Controller ctrl) {
        this.ctrl = ctrl;
    }

    /**
     * Runs simulation of sale process.
     */
    public void runFakeExecution() {

        // startSale
        ctrl.startSale();
        System.out.println();
        System.out.println("-------------------------------------");
        System.out.println("A new sale has been started.");
        System.out.println();

        // scanItem
        while (itemsLeft){
            int[][] arrItemsToBeScanned = {{1,2},{2,1}}; // 2 möljk, 1 smör
            for(int i = 0; i < arrItemsToBeScanned.length;i++){
            int itemId = arrItemsToBeScanned[i][0];
            int quantity = arrItemsToBeScanned[i][1];
            // Create ViewDTO from scanned item
            ViewDTO viewDTO = ctrl.scanItem(itemId, quantity);

            if (viewDTO.regItem == null)
                System.out.println("Invalid identifier: " + itemId);
            else 
                System.out.println(" * " + viewDTO.regItem.quantity + "st " + viewDTO.regItem.item.itemDescription +" (itemID: "+itemId+ ")" +" á " + viewDTO.regItem.item.price);
                System.out.println("Running total (inc. VAT): " + viewDTO.runningTotalIncVat);
            }
            // Code that sets itemsLeft to false
            itemsLeft = false;
        }

        // endSale
        SaleDTO currentSaleDTO = ctrl.endSale();
        System.out.println("Sale ended.\n");
        System.out.println("Total: " + currentSaleDTO.saleSums.totalPrice);
        System.out.println("Total (inc. VAT): " + currentSaleDTO.saleSums.totalIncVat);

        // requestDiscount
        int customerID = 1;
        int[] discTypes = {1,2,3};

        currentSaleDTO = ctrl.requestDiscount(customerID, currentSaleDTO, discTypes);
        System.out.println("After discount:");
        System.out.println("Total: " + currentSaleDTO.saleSums.totalPrice);
        System.out.println("Total (inc. VAT): " + currentSaleDTO.saleSums.totalIncVat);

        // registerPayment
        Amount payment = new Amount(100);
        ctrl.registerPayment(payment);

        // Receipt
        ctrl.printReceipt();
    }
}