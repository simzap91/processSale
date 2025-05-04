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
                System.out.println("Add " + quantity + " item with itemId: " + itemId);
                System.out.println("Item ID: " + viewDTO.regItem.item.itemID);
                System.out.println("Item name: " + viewDTO.regItem.item.itemDescription);
                System.out.println("Item cost: " + viewDTO.regItem.item.price + "kr");
                System.out.println("VAT: " + (int)(100 * viewDTO.regItem.item.vatRate) + "%");
                System.out.println();
                System.out.println("Running total (inc. VAT): " + viewDTO.runningTotalIncVat + "kr");
                System.out.println();
            }
            // Code that sets itemsLeft to false
            itemsLeft = false;
        }

        // endSale
        SaleDTO currentSaleDTO = ctrl.endSale();
        System.out.println("Sale ended");
        System.out.println();
        System.out.println("Total: " + currentSaleDTO.saleSums.totalPrice + "kr");
        System.out.println("Total (inc. VAT): " + currentSaleDTO.saleSums.totalIncVat + "kr");

        // requestDiscount
        int customerID = 1;
        int[] discTypes = {1,2,3};
 
        currentSaleDTO = ctrl.requestDiscount(customerID, currentSaleDTO, discTypes);
       
        System.out.println("Total (inc. VAT) after discount: " + currentSaleDTO.saleSums.totalIncVat + "kr");
        System.out.println();

        // registerPayment
        Amount payment = new Amount(100);
        ctrl.registerPayment(payment);

        // Receipt
        ctrl.printReceipt();
    }
}