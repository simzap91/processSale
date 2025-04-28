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
       
        //add

    }
}
