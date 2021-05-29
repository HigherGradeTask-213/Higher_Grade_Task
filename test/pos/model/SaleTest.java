/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.model;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pos.DTO.ItemDTO;
import pos.DTO.PaymentDTO;


public class SaleTest {
    @Test
    public void testAddItem() {
        ItemDTO itemDTO = new ItemDTO("Tomato",12,56,"Tasty","1");
        Sale instance = new Sale();
        String expResult = "Tomato";
        String result = instance.addItem(itemDTO).getCurrentItemName();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testDuplicateItem() {
        ItemDTO itemDTO = new ItemDTO("Tomato",12,56,"Tasty","1");
        Sale instance = new Sale();
        int expResult = 2;
        instance.addItem(itemDTO);
        int result = instance.addItem(itemDTO).getCurrentItemQuantity();
        assertEquals(expResult, result);
    }
    @Test
    public void testTotalPriceWithOneItemAdded() {
        ItemDTO itemDTO = new ItemDTO("Tomato",12,56,"Tasty","1");
        Sale instance = new Sale();
        String identifier = "1";
        double totalPrice = instance.addItem(itemDTO).getRunningTotal();
        assertEquals(62.72, totalPrice,"Calculate totalPrice is not working");
    } 

    @Test
    public void testTotalPriceWithTwoItemsAdded() {
        ItemDTO firstItem = new ItemDTO("Tomato",12,56,"Tasty","1");
        ItemDTO secondItem = new ItemDTO("Peach",12,2,"Sweet","2");
        Sale instance = new Sale();
        instance.addItem(firstItem);
        double totalPrice = instance.addItem(secondItem).getRunningTotal();
        assertEquals(64.96, totalPrice,"Calculate totalPrice is not working");
    } 
    
    @Test
    public void testPrintReceipt() {
        Sale instance = new Sale();
        
        ItemDTO firstItem = new ItemDTO("Tomato",12,56,"Hjälper dig att fånga råttor eller nypa dina kompisar i tårna.","1");
        ItemDTO secondItem = new ItemDTO("Peach",12,2,"Sweet","2");
        instance.addItem(firstItem);
        instance.addItem(secondItem);
        
        PaymentDTO paymentDTO = new PaymentDTO(100,"USD");
        
        String expResult = "One Infinite Loop, 1\nItems: \nTomato 1 56.0 USD\nPeach 1 2.0 USD\nTotal: 64.96 USD\nVAT: 6.96 USD\nAmount paid: 100 USD";
        String result = instance.printReceipt(paymentDTO);
        
        assertEquals(expResult, result);
    }

}