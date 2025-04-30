package se.gows.processsale.view;
import se.gows.processsale.DTO.SaleDTO;
import se.gows.processsale.DTO.ViewDTO;
import se.gows.processsale.controller.*;
import se.gows.processsale.model.*;

public class View {
    private Controller ctrl;
    boolean itemsLeft = true;

    // Contrs
    /**
     * Creates a new instance, that uses the specified controller for all calls to other layers.
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
        System.out.println("A new sale has been started.");
        System.out.println();

        // scanItem
        while (itemsLeft){
            int itemId = 2;
            int quantity = 2;
            // Create ViewDTO from scanned item
            ViewDTO viewDTO = ctrl.scanItem(itemId, quantity);

            if (viewDTO.regItem == null)
                System.out.println("Invalid identifier: " + itemId);
            else 
                System.out.println(" * " + viewDTO.regItem.quantity + " " + viewDTO.regItem.item.itemDescription + " á " + viewDTO.regItem.item.price);
            System.out.println("Running total (inc. VAT): " + viewDTO.runningTotalIncVat);

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
        System.out.println("Total; " + currentSaleDTO.saleSums.totalPrice);
        System.out.println("Total (inc. VAT); " + currentSaleDTO.saleSums.totalIncVat);

        // registerPayment
        Amount payment = new Amount(100);
        ctrl.registerPayment(payment);

        // Receipt
        ctrl.printReceipt();
    }
}