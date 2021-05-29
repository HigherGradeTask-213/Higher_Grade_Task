
package pos.integration;

/**
 *The class is a placeholder for the external database for the inventory system.
 * This is currently hardcoded with values for each item.
 */

import pos.DTO.ItemDTO;
import pos.DTO.SaleDTO;

public class EISHandler {
    /**
     * A function that handles finding the item information that correspond to the items identifer.
     * @param identifier the scanned items identifier,
     * @return a object ItemDTO holding the information about the scanned item or null if itemIdentifer doesnt match any items in system.
     */
    public ItemDTO findItem(String identifier)throws ItemNotFoundException,DataBaseUnacessibleException{
        if(identifier.equals("1"))
            return new ItemDTO("tomato",12,56,"tasty.","1");
        else if(identifier.equals("2"))
            return new ItemDTO("peach",12,2,"sweet.","2");
        else if(identifier.equals("3"))
            return new ItemDTO("banana",12,8,"yellow.","3");
        else if(identifier.equals("10"))
            throw new DataBaseUnacessibleException("External Inventory System");
        else
            throw new ItemNotFoundException(identifier);
     


    }
    /**
     * The function is a placeholder for when the system updates the inventory in the external system.
     * @param saleDTO an object SaleDTO holding the infromaiton about the current sale.
     */
    public void updateInventory(SaleDTO saleDTO){
        System.out.println("[LOG] Items logged.");
    }
}
