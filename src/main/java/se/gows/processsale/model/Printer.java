package se.gows.processsale.model;

public class Printer {

    public void printReceipt(Receipt receipt) {
        System.out.println("\r\n" + //
                        " __   ___  __   ___    __  ___ \r\n" + //
                        "|__) |__  /  ` |__  | |__)  |  \r\n" + //
                        "|  \\ |___ \\__, |___ | |     |  \r\n" + //
                        "                               \r\n" + //
                        "");
        System.out.println();
        System.out.println("Time of Sale " + receipt.timeOfSale);
        System.out.println();
        System.out.println("Item list:");
        for(RegisteredItem regItem : receipt.itemList ) {
            System.out.println("* " + regItem.quantity + " " + regItem.item.itemDescription + " á " + regItem.item.price + "kr -> " + (regItem.quantity * regItem.item.price + "kr"));
        }
        System.out.println();
        System.out.println("Total price: " + String.format("%.2f",receipt.totalPrice) + "kr");
        System.out.println("Total VAT: " + receipt.totalVAT + "kr");
        System.out.println("-------------------------------------");
        System.out.println("Total (incl. VAT): " + String.format("%.2f", receipt.totalIncVat) + " kr");
        System.out.println("-------------------------------------");
        System.out.println("Amount paid: " + receipt.amountPaid.toString());
        System.out.println("Amount change: " + receipt.amountChange.toString());
        System.out.println("-------------------------------------");
    }
}
