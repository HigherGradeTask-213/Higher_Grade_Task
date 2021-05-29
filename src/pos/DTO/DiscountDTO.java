
package pos.DTO;

    /**
 * DiscountDTO is a class that holds data about the disscounts and is used to transfer data between classes.
 */
public class DiscountDTO {
    private double amount;
    private final String type;
    private String idOfDiscountedItem;
    private double minRequired;
    
    /**
     * @param amount refers to the discount amount
     * @param id refers to the identifier of the discounted item
     * 
     */
    public DiscountDTO(double amount,String id){
        this.amount=amount;
        this.type="item";
        this.idOfDiscountedItem=id;

    }
    /**
     * 
     * @param amount refter to the discount amount
     * @param min minimum requiered total price for the discount to be viable.
     */
    public DiscountDTO(double amount,double min){
        this.amount=amount;
        this.type="sale";
        this.minRequired = min;
    }
     /**
     * Return the amount the customer paid with
     * @return double amount paid
     */
    public double getAmount(){
        return amount;
    }
     /**
     * Return the Currency the customer payed with.
     * @return String name of currency
     */
    public String getType(){
        return type;
    }
    /**
     * Return the id of the discounted item.
     * @return String of the item identifier
     */
    public String getIdOfDiscountedItem(){
        return idOfDiscountedItem;
    }
    /**
     * Return the minimum requiered total price of the sale.
     * @return double minimum requiered total price
     */
    public double getMinRequiered(){
        return minRequired;
    }
}
