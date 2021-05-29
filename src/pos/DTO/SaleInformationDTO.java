
package pos.DTO;

import pos.model.Sale;

/**
 *SaleInformaitionDTO is a class that holds information about the latest item added and the current running total.
 * And is used to transfer the data between classes.
 * currentItemDescription refers to the most recent item added description
 * currentItemName refers to the most recent item added name
 * currentItemQuantity refers to the most recent item added quantity
 * currentItemPrice refers to the most recent item added price
 * runningTotal refers to the current total amount for the purchase.
 */
public class SaleInformationDTO {
    
    private String currentItemDescription;
    private double currentItemPrice;
    private int currentItemQuantity;
    private String currentItemName;
    private double runningTotal;
/**
 * 
 * @param addedItem refers to the item most recently added to sale
 * @param sale refers to the current sale object.
 */
    public SaleInformationDTO(ItemDTO addedItem,Sale sale){
        this.currentItemDescription = addedItem.getDescription();
        this.currentItemPrice = addedItem.getPrice();
        this.currentItemName = addedItem.getName();
        this.currentItemQuantity = sale.getQuantityOfItem(addedItem.getItemIdentifier());
        this.runningTotal = sale.getTotalPrice();
    }
    /**
     * Returns the most recent item added name
     * @return the name of most recent item added
     */
    public String getCurrentItemName(){
        return currentItemName;
    }
    /**
     * Returns the most recent item added quantity
     * @return the quantity of most recent item added
     */
    public int getCurrentItemQuantity(){
        return currentItemQuantity;
    }
    /**
     * Returns the most recent item added description
     * @return the most recent item added description
     */
    public String getCurrentItemDescription(){
        return currentItemDescription;
    }
    /**
     * Returns the most recent item added price
     * @return the most recent item added price
     */
    public double getCurrentItemPrice(){
        return currentItemPrice;
    }
    /**
     * Returns the current runnning total amount of the purchase.
     * @return the current running total.
     */
    public double getRunningTotal(){
        return runningTotal;
    }    
}
