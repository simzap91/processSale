package se.gows.processsale.model;

import java.time.format.DateTimeFormatter;
import java.util.Locale;
import se.gows.processsale.DTO.RegisteredItemDTO;

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
        System.out.println("Time of Sale: " + receipt.getTimeOfSale().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        System.out.println();
        System.out.println("Item list:");
        for(RegisteredItemDTO regItem : receipt.getItemList() ) {
            System.out.println("* " + regItem.getQuantity() + "st " + regItem.getItemDescription() + " á " + regItem.getPrice() + "kr -> " + (regItem.getTotalPriceOfItemQuantity() + "kr"));
        }
        System.out.println();
        System.out.println("Total price: " + String.format(Locale.US, "%.2f",receipt.getSaleSums().getTotalPrice().getValue()) + "kr");
        System.out.println("Total VAT: " + receipt.getSaleSums().getTotalVAT().getValue() + "kr");
        System.out.println("-------------------------------------");
        System.out.println("Total (incl. VAT): " + String.format(Locale.US, "%.2f", receipt.getSaleSums().getTotalIncVat().getValue()) + " kr");
        System.out.println("-------------------------------------");
        System.out.println("Amount paid: " + receipt.getAmountPaid().toString());
        System.out.println("Amount change: " + receipt.getAmountChange().toString());
        System.out.println("-------------------------------------");
    }
}
