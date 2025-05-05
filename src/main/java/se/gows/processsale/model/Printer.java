package se.gows.processsale.model;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Public method that receives a reciept object and prints its attributes 
 * @param receipt
 */
public class Printer {

    public void printReceipt(Receipt receipt) {
        System.out.println("\r\n" + //
                        " __   ___  __   ___    __  ___ \r\n" + //
                        "|__) |__  /  ` |__  | |__)  |  \r\n" + //
                        "|  \\ |___ \\__, |___ | |     |  \r\n" + //
                        "                               \r\n" + //
                        "");
        System.out.println();
        System.out.println("Time of Sale: " + receipt.timeOfSale.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        System.out.println();
        System.out.println("Item list:");
        for(RegisteredItem regItem : receipt.itemList ) {
            System.out.println("* " + regItem.quantity + "st " + regItem.item.getItemDescription() + " á " + regItem.item.getPrice() + "kr -> " + (regItem.getTotalPriceOfItemQuantity() + "kr"));
        }
        System.out.println();
        System.out.println("Total price: " + String.format(Locale.US, "%.2f",receipt.saleSums.totalPrice) + "kr");
        System.out.println("Total VAT: " + receipt.saleSums.totalVAT + "kr");
        System.out.println("-------------------------------------");
        System.out.println("Total (incl. VAT): " + String.format(Locale.US, "%.2f", receipt.saleSums.totalIncVat) + " kr");
        System.out.println("-------------------------------------");
        System.out.println("Amount paid: " + receipt.amountPaid.toString());
        System.out.println("Amount change: " + receipt.amountChange.toString());
        System.out.println("-------------------------------------");
    }
}
