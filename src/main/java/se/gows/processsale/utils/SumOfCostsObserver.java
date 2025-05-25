package se.gows.processsale.utils;

/**
 * An interface for objects which observes the total sum of costs of all sales made during a program run.
 */
public interface SumOfCostsObserver {
    /**
     * Invoked when a payment has been paid.
     * @param costOfNewSale The cost of the sale that was paid for.
     */
    void newSaleWasMade(double costOfNewSale);
}