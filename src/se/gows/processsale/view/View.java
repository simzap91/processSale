package se.gows.processsale.view;
import se.gows.processsale.controller.*;

public class View {
    private Controller ctrl;

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

    }
}