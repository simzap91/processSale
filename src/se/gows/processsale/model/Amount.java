package se.gows.processsale.model;

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
        return String.format("%.2f kr", getValue());
    }
}