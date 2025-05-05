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
   
        ctrl.startSale();
        
        System.out.println();
        System.out.println("-------------------------------------");
        System.out.println("A new sale has been started.");
        System.out.println();

        int[][] arrItemsToBeScanned = {{1,2},{2,1}};
        int itemsScannedCount = 0;
        while (itemsScannedCount < arrItemsToBeScanned.length){
            
            int itemId = arrItemsToBeScanned[itemsScannedCount][0];
            int quantity = arrItemsToBeScanned[itemsScannedCount][1];

            ViewDTO viewDTO = ctrl.scanItem(itemId, quantity);
            if (viewDTO.regItem == null) {
                System.out.println("Invalid identifier: " + itemId);
            } else {
                System.out.println("Add " + quantity + " item with itemId: " + itemId);
                System.out.println("Item ID: " + viewDTO.regItem.item.getItemID());
                System.out.println("Item name: " + viewDTO.regItem.item.getItemDescription());
                System.out.println("Item cost: " + viewDTO.regItem.item.getPrice() + "kr");
                System.out.println("VAT: " + (int)(100 * viewDTO.regItem.item.getVatRate()) + "%");
                System.out.println();
                System.out.println("Running total (inc. VAT): " + viewDTO.runningTotalIncVat + "kr");
                System.out.println();
            }
            itemsScannedCount ++;
        }

        SaleDTO currentSaleDTO = ctrl.endSale();
        System.out.println("-------------------------------------");
        System.out.println("Sale ended");
        System.out.println();
        System.out.println("Total (inc. VAT): " + currentSaleDTO.getSaleSums().getTotalIncVat() + "kr");
        System.out.println();

        int customerID = 1;
        int[] discTypes = {1,2,3};
        System.out.println("-------------------------------------");
        System.out.println("Discount requested.");
        System.out.println("Customer ID: " + customerID);
        System.out.print("Discount types: ");
        for (int type : discTypes) {
            System.out.print(type + ", ");
        }
        System.out.println();
 
        currentSaleDTO = ctrl.requestDiscount(customerID, currentSaleDTO, discTypes);
       
        System.out.println("-------------------------------------");
        System.out.println();
        System.out.println("Total (inc. VAT) after discount: " + currentSaleDTO.getSaleSums().getTotalIncVat() + "kr");
        System.out.println("-------------------------------------");
        System.out.println();

        Amount payment = new Amount(100);
        ctrl.registerPayment(payment);
        ctrl.printReceipt();
    }
}