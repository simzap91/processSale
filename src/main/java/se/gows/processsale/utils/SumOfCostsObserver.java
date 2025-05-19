package se.gows.processsale.utils;

import se.gows.processsale.DTO.SaleDTO;

/**
 * An interface for objects which observes the total sum of costs of all sales made during a program run.
 */
public interface SumOfCostsObserver {
    /**
     * Invoked when a payment has been paid.
     * @param sumOfCost The cost of the sale that was paid for.
     */
    void newSumOfCost(SaleDTO saleSummary);
}