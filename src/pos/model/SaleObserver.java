
package pos.model;


/**
 * SaleObserver 
 */
public interface SaleObserver {
    /**
     * newSale This function is called when a sale is completed
     * 
     * @param priceOfPurchase The cost of the sale
     */
    public void newSale(double priceOfPurchase);
    
}
