package se.gows.processsale.utils;

import se.gows.processsale.model.Amount;

public interface SumOfCostsObserver {
    /**
     * Invoked when a payment has been paid.
     * @param sumOfCost The cost of the sale that was paid for.
     */
    void newSumOfCost(Amount sumOfCost);
}