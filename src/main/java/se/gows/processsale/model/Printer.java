package se.gows.processsale.model;

public class Printer {
    private Receipt receipt;

    public Printer(Receipt receipt) {
        this.receipt = receipt;
    }

    private void printReceipt(Receipt receipt) {
        System.out.println("\r\n" + //
                        " __   ___  __   ___    __  ___ \r\n" + //
                        "|__) |__  /  ` |__  | |__)  |  \r\n" + //
                        "|  \\ |___ \\__, |___ | |     |  \r\n" + //
                        "                               \r\n" + //
                        "");
        System.out.println();
        System.out.println("Time of Sale" + receipt.timeOfSale);
        System.out.println();
        System.out.println("Item list:");
        for(RegisteredItem i : receipt.itemList ) {
            System.out.println("* " + i.item.itemDescription + " " + i.item.price + "kr");
        }
        System.out.println();
        System.out.println("Total price: " + receipt.totalPrice);
        System.out.println("Total VAT: " + receipt.totalVAT);
        System.out.println();
        System.out.println("Total (incl. VAT):" + receipt.totalIncVat);
        //add

    }
}
