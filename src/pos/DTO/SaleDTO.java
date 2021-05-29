/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.DTO;

import java.time.LocalTime;
import java.util.ArrayList;
import pos.model.Item;
import pos.model.Sale;

/**
 * SaleDTO is a class that holds information about a complete sale and is used to transfer a collection of data between classes.
 * saleTime refers to the time the sale was completed
 * items refers to the list of items the customer is buying
 * totalPrice is the price the customer has to pay for all items, including VAT
 * totalVat is the total VAT amount for all the items the customer is paying for.
 * SaleDTO is created by Sale in the pay function in Controller.
 */
public class SaleDTO {
    private LocalTime saleTime;
    private ArrayList <Item> items;
    private double totalPrice;
    private double totalVAT;
    /**
     * 
     * @param sale refers to the class holding the information about the current sale.
     */
    public SaleDTO(Sale sale){
        this.saleTime = sale.getSaleTime();
        this.items = sale.getItems();
        this.totalPrice = sale.getTotalPrice();
        this.totalVAT = sale.getTotalVAT();
    }
    /**
     * Returns a the toal price of all the items bought.
     * @return the total price of the purchase.
     */
    public double getTotalPrice(){
        return totalPrice;
    }
    /**
     * Returns the total VAT of all the items
     * @return the total VAT 
     */
    public double getTotalVAT(){
        return totalVAT;
    }
    /**
     * Return a duplicate list of items, this list is made up of duplicates of the Item objects in items.
     * publicItems refers to a duplicate list of items.
     * @return a list of Item objects
     */
    public ArrayList<Item> getItems(){
        ArrayList<Item> publicItems = new ArrayList<Item>();
        
        for(Item item:items){
            publicItems.add(new Item(item));
        }
        return publicItems;
    }
    /**
     * Returns the time when the sale is completed.
     * @return the time of sale
     */
    public LocalTime getSaleTime(){
        return saleTime;
    }
}
