package se.gows.processsale.model;

import java.util.Locale;

public class Amount {
    public double amount;

    public Amount (double amount) {
        this.amount = amount;
    }

    public double getValue() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "%.2f kr", getValue());
    }
}