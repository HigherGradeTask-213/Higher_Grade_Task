/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.integration;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CashRegisterTest {
    private CashRegister instance;
    
    
    @BeforeEach
    public void setUp() {
         instance = CashRegister.getInstance();
    }
    
    @Test
    public void testCountAmountInRegister() {
        double expResult = instance.getAmountInRegister()+10;
        instance.updateAmountInRegister(10);
        double result = instance.getAmountInRegister();
        assertEquals(expResult, result, 0.0);
    }
   
   
}
