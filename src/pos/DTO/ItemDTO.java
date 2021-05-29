package pos.DTO;

/**
 * ItemDTO is a class that hold information about an Item and is used to transfer a collection of data between classes.
 * The class is created in findItem by EISHandler.
 */
public class ItemDTO {
    private String name;
    private double VATRate;
    private double price;
    private String description;
    private String itemIdentifier;
/**
 * 
 * @param name refers to the name of the item
 * @param VATRate refers to the VATRate of the item
 * @param price refers to the price of the item
 * @param description refers to the description of the item
 * @param identifier refers to the identifier of the item
 */
    public ItemDTO(String name, double VATRate,double price, String description, String identifier){
        this.name = name;
        this.VATRate = VATRate;
        this.price = price;
        this.description = description;
        this.itemIdentifier  = identifier;
    }
    /**
     * Return the name of the item.
     * @return String name of tiem
     */
    public String getName(){
        return name;
    }
     /**
     * Return the description of the item.
     * @return String description of tiem
     */
    public String getDescription(){
        return description;
    }
     /**
     * Return the Identifer of the item.
     * @return String Identifer of tiem
     */
    public String getItemIdentifier(){
        return itemIdentifier;
    }
     /**
     * Return the Price of the item.
     * @return Double price of tiem
     */
    public double getPrice(){
        return price;
    }
     /**
     * Return the VAT Rate of the item.
     * @return double VAT rate of tiem
     */
    public double getVATRate(){
        return VATRate;
    }    
}
