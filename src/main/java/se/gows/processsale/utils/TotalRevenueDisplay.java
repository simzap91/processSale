package se.gows.processsale.utils;

/**
 * Superclass of all classes that implements SumOfCostsObserver.
 */
public abstract class TotalRevenueDisplay implements SumOfCostsObserver {
    private double sumOfCosts;

    /**
     * Creates a new instance with sumOfCosts set to 0.
     */
    protected TotalRevenueDisplay(){
        this.sumOfCosts = 0;
    }

    /**
     * Called when a new cost is observed. Updates total revenue and prints it in the console.
     * 
     * @param sumOfCost The new cost amount to be added.
     */
    @Override
    public void newSaleWasMade(double costOfNewSale){
        addNewCost(costOfNewSale);
        showTotalIncome();
    }

    private void addNewCost(double costOfNewSale){
        sumOfCosts += costOfNewSale;
    }

    private void showTotalIncome() {
        try {
            doShowTotalIncome(this.sumOfCosts);
        } catch (Exception e) {
            handleErrors(e);
        }
    }

    /**
     * Displays the total revenue from all sales during a program run.
     * 
     * @param sumOfCosts Current total revenue from all sales.
     */
    protected abstract void doShowTotalIncome(double sumOfCosts) throws Exception;

    /**
     * Handles the exceptions that is thrown by the doShowTotalIncome-method in the subclass.
     * 
     * @param e Exception that causes the error.
     */
    protected abstract void handleErrors(Exception e);
}
