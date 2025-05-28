package se.gows.processsale.utils;

/**
 * Superclass of all classes that implements SumOfCostsObserver.
 */
public abstract class TotalIncomePrinter implements SumOfCostsObserver {
    private double totalIncome;

    /**
     * Creates a new instance with totalIncome set to 0.
     */
    protected TotalIncomePrinter(){
        this.totalIncome = 0;
    }

    /**
     * Called when a new cost is observed. Updates total revenue and prints it in the console.
     * 
     * @param totalIncome The new cost amount to be added.
     */
    @Override
    public void newSaleWasMade(double costOfNewSale){
        addNewCost(costOfNewSale);
        showTotalIncome();
    }

    private void addNewCost(double costOfNewSale){
        totalIncome += costOfNewSale;
    }

    private void showTotalIncome() {
        try {
            doShowTotalIncome(this.totalIncome);
        } catch (Exception e) {
            handleErrors(e);
        }
    }

    /**
     * Displays the total revenue from all sales during a program run.
     * 
     * @param totalIncome Current total revenue from all sales.
     */
    protected abstract void doShowTotalIncome(double totalIncome) throws Exception;

    /**
     * Handles the exceptions that is thrown by the doShowTotalIncome-method in the subclass.
     * 
     * @param e Exception that causes the error.
     */
    protected abstract void handleErrors(Exception e);
}
