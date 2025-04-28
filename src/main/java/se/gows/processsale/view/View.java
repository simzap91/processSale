package se.gows.processsale.view;
import se.gows.processsale.DTO.SummaryDTO;
import se.gows.processsale.DTO.ViewDTO;
import se.gows.processsale.controller.*;

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

    public void runFakeExecution() {

        // startSale
        ctrl.startSale();
        System.out.println("A new sale has been started.");

        // scanItem
        while (itemsLeft){
            int itemId = 0;
            int quantity = 0;
            // Create ViewDTO from scanned item
            ViewDTO viewDTO = ctrl.scanItem(itemId, quantity);

            // Code that sets itemsLeft to false
            itemsLeft = false;
        }

        // endSale
        SummaryDTO sumDTO = ctrl.endSale();
        System.out.println("Sale ended.\n");
        

    }
}