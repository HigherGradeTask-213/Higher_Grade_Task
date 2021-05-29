
package pos.integration;

import pos.model.Receipt;

/**
 * A class that is a placeholder for the Receipt printer. 
 */
public class ReceiptPrinter {
    /**
     * Prints out the receipt of the current sale to the cusotmer.
     * @param receipt a object of the receipt information of the purchase.
     */
    public void printReceiptToCustomer(Receipt receipt){
        System.out.println("[LOG] Receipt printed.");
    }
}
