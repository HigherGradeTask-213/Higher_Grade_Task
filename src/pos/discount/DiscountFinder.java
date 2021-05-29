
package pos.discount;

import java.util.List;
import pos.DTO.DiscountDTO;
import pos.DTO.SaleDTO;


public interface DiscountFinder{

    /**
     *Searches the avalible discounts for the discounts that matches the criteria for the items in sale.
     * @param saleDTO A object holding information about the sale
     * @param availableDiscounts a list conatining all avalible discounts
     *
     */
    List<DiscountDTO> findDiscount(SaleDTO saleDTO, List<DiscountDTO> availableDiscounts);
}
