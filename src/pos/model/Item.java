package pos.model;

import pos.DTO.DiscountDTO;
import pos.DTO.ItemDTO;

/**
 * The class holds the information and operations associated with a specific item.
 * Item is created by addItem in Sale using the ItemDTO from EIS or when a duplicate of a Item object is made when using getItems()
 * name refers to the name of the item
 * VATRate refers to the vat rate of the item
 * price refers to the price of the item
 * description refers to the description of the item.
 * itemIdentifier refers to the items identifer.
 */
public class Item {
    private int quantity;
    private String name;
    private double VATRate;
    private double price;
    private String description;
    private String itemIdentifier;
    
    /**
     * The constructur is used when a duplicate of the item list is created in getItems in Sale.
     * @param item a object of type Item.
     */
    public Item(Item item) {
        this.description = item.getDescription();
        this.VATRate = item.getVATRate();
        this.price = item.getPrice();
        this.name = item.getName();
        this.itemIdentifier = item.getItemIdentifier();
        this.quantity = item.getQuantity();
    }
    /**
     * @param itemDTO a object of ItemDTO holding the informaiton about a item form the EIS handler.
     */
    public Item(ItemDTO itemDTO){
        this.quantity = 1;
        this.name = itemDTO.getName();
        this.VATRate = itemDTO.getVATRate();
        this.price = itemDTO.getPrice();
        this.description = itemDTO.getDescription();
        this.itemIdentifier  = itemDTO.getItemIdentifier();
    }
    
    public void applyDiscounts(DiscountDTO discount){
        if(discount.getAmount()<1){
            price*=discount.getAmount();
        }
        else{
            price-=discount.getAmount();
        }
    }
    
    
    /**
     * Return the name of the item.
     * @return string name of item
     */
    public String getName(){
        return name;
    }
    /**
     * returns the items description
     * @return the items description
     */
    public String getDescription(){
        return description;
    }
    /**
     * returns the items identifer
     * @return a string of the items identifer
     */
    public String getItemIdentifier(){
        return itemIdentifier;
    }
    /**
     * returns the items quantity
     * @return the quantity of the item
     */
    public int getQuantity(){
        return quantity;
    }
    /**
     * returns the price of the item
     * @return the price of the item
     */
    public double getPrice(){
        return price;
    }
    /**
     * return the vat rate of the item
     * @return vat rate of the item
     */
    public double getVATRate(){
        return VATRate;
    }
    /**
     * Returns the calculated vat amount of an item
     * @return vat amount of an item
     */
    public double getVATAmount(){
        return 0.01*VATRate* price;
    }  
    /**
     * Raises the quantity by 1 if a item is entered twice.
     */
    public void raiseQuantity(){
        quantity+=1;
    }  
}
