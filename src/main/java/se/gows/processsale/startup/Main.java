package se.gows.processsale.startup;

import se.gows.processsale.controller.*;
import se.gows.processsale.integration.*;
import se.gows.processsale.utils.ObserversList;
import se.gows.processsale.view.*;

/**
 * The only Main class of the program. This class executes the program. Before execution the class initializes handlers to all external data bases,
 * and also the controller and the view that is used throughout the sale process.
 */
public class Main {
    public static void main(String[] args) {
        InventoryDBHandler invHandler = new InventoryDBHandler();
        AccountingDBHandler accHandler = new AccountingDBHandler();
        DiscountDBHandler discHandler = new DiscountDBHandler();

        ObserversList obsList = new ObserversList();

        Controller ctrl = new Controller(invHandler, accHandler, discHandler, obsList);
        View view = new View(ctrl);

        view.runSaleCustomerOne();
        view.runSaleCustomerTwo();
        view.runSaleCustomerThree();
    }
}