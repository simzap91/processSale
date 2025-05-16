package se.gows.processsale.model;

import java.util.Locale;

/**
 * Amount object containing a stored value as a double.
 */
public class Amount {
    private double amount;

    public Amount (double amount) {
        this.amount = amount;
    }

    public void setAmount(double newAmount) {this.amount = newAmount;}
    public double getValue() {return amount;}

    /**
     * Method that formats the default toString-method to use dots instead of commas in decimal numbers
     * @return amount as string
     */
    @Override
    public String toString() {
        return String.format(Locale.US, "%.2f kr", getValue());
    }
}