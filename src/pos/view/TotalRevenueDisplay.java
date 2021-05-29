
package pos.view;

import se.kth.IV1350.pos.model.SaleObserver;

/**
 * The template pattern has been implemented, TotalRevenueFileOutput and TotalRevenueView overrides the doShowTotalIncome
 * depending on the desiered logging of the total revenue.
 */
public abstract class TotalRevenueDisplay implements SaleObserver {
    private double totalRevenue;

    protected TotalRevenueDisplay() {
        totalRevenue = 0;
    }

    @Override
    public void newSale(double priceOfTheSale) {
        calculateTotalIncome(priceOfTheSale);

        showTotalIncome();
    }

    private void showTotalIncome() {
        try {
            doShowTotalIncome(totalRevenue);
        } catch(Exception e) {
            handleErrors(e);
        }
    }

    private void calculateTotalIncome(double priceOfTheSale) {
        totalRevenue += priceOfTheSale;
    }

    protected abstract void doShowTotalIncome(double totalRevenue) throws Exception;

    protected abstract void handleErrors(Exception e);
}
