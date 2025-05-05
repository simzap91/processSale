package se.gows.processsale.model;

import java.util.Locale;

/**
 * Amount object cotaining a stored value as a double.
 * @param amount
 */
public class Amount {
    public double amount;

    public Amount (double amount) {
        this.amount = amount;
    }

    /**
     * Public method that returns the value within an Amount object
     * @return amount
     */
    public double getValue() {
        return amount;
    }

    /**
     * Method that formats the default toString-method to use dots instead of commas in decimal numbers
     * @return amount as string
     */
    @Override
    public String toString() {
        return String.format(Locale.US, "%.2f kr", getValue());
    }
}