/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.model;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import pos.DTO.ItemDTO;
import pos.DTO.PaymentDTO;
import pos.DTO.SaleDTO;


public class ReceiptTest {
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;

    @BeforeEach
    public void setUp() {        
        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
      
    }
    
    @AfterEach
    public void tearDown() {
        printoutBuffer = null;
        System.setOut(originalSysOut);
    }

    @Test
    public void testGetReceiptSummary() {
        System.out.println("getReceiptSummary");
        Sale sale = new Sale();
        
        ItemDTO firstItem = new ItemDTO("Tomato",12,56,"Tasty","1");
        ItemDTO secondItem = new ItemDTO("Peach",12,2,"Sweet","2");
        sale.addItem(firstItem);
        sale.addItem(secondItem);
        
        SaleDTO saleDTO = new SaleDTO(sale);
        PaymentDTO paymentDTO = new PaymentDTO(100,"USD");
        Receipt instance = new Receipt(saleDTO,paymentDTO);
        
        String expResult = "One Infinite Loop1, 1\nItems: \nTomato 1 56.0 USD\nPeach 1 2.0 USD\nTotal: 64.96 USD\nVAT: 6.96 USD\nAmount paid: 100 USD";
        String result = instance.getReceiptSummary();
        
        assertEquals(expResult, result);
    }

    
}
