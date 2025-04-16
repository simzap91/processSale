package se.gows.processsale.startup;

import se.gows.processsale.controller.*;
import se.gows.processsale.view.*;
import se.gows.processsale.integration.*;

public class Main {
    public static void main(String[] args) {

        InventoryDBHandler invHandler = new InventoryDBHandler();
        AccountingDBHandler accHandler = new AccountingDBHandler();
        DiscountDBHandler discHandler = new DiscountDBHandler();
        Controller ctrl = new Controller(invHandler, accHandler, discHandler);
        View view = new View(ctrl);

        view.runFakeExecution();
    }
}
