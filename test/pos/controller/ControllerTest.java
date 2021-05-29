package pos.controller;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pos.integration.DCHandler;
import pos.integration.EASHandler;
import pos.integration.EISHandler;
import pos.integration.DataBaseUnacessibleException;
import pos.integration.ItemNotFoundException;


public class ControllerTest {
    private Controller instanceToTest;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;
    private String itemIdentifier;
    private EISHandler eis;
    private EASHandler eas;
    private DCHandler dc;
    
    @BeforeEach
    public void setUp() {        
        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
        
        
        eas = new EASHandler();
        eis = new EISHandler();
        dc = new DCHandler();
        
        instanceToTest = new Controller(eis,eas,dc);

    }
    
    @AfterEach
    public void tearDown() {
        instanceToTest =null;
        printoutBuffer = null;
        System.setOut(originalSysOut);
    }

    @Test
    public void testCashRegisterCreation() {
        String printout = printoutBuffer.toString();
        String expectedOutput = "successfully";
        assertTrue(printout.contains(expectedOutput),"UI did not start correctly");
    }
    @Test
    public void testEnterSingleItem() throws Exception{
   
    
    
        try{
            instanceToTest.startSale();
            String identifier = "1";
            String name = instanceToTest.enterItem(identifier).getCurrentItemName();
            assertEquals("Tomato", name,"Enter item is not working,expected Name didnt match with identifers name.");
        }
        catch(ItemNotFoundException e){
            fail("Got exception ItemNotFound");
        }
        catch(DataBaseUnacessibleException ex){
            fail("Got exception ItemNotFound");
        }
        
    }

    @Test
    public void testChangeWithOneItem() throws Exception{
        
    
        try{
            instanceToTest.startSale();
            instanceToTest.enterItem("1");
            double change = instanceToTest.pay(70,"USD");
            assertEquals(7.28, change,0.01,"Calculate change isnt working correctly");  
        }
        catch(ItemNotFoundException e){
            fail("Got exception ItemNotFound");
        }
        catch(DataBaseUnacessibleException ex){
            fail("Got exception ItemNotFound");
        }
        
    }    
 
    @Test
    public void testReceipt() throws Exception{
        try{
            instanceToTest.startSale();
            instanceToTest.enterItem("1");
            instanceToTest.enterItem("1");
            instanceToTest.enterItem("2");
            instanceToTest.pay(140,"USD");
            String receiptString = instanceToTest.completeSale();
            String expectedOutput = "One Infinite Loop, 1\nItems: \nTomato 2 112.0 USD\nPeach 1 2.0 USD\nTotal: 127.68 USD\nVAT: 13.68 USD\nAmount paid: 140 USD";
            assertEquals(expectedOutput,receiptString,"Print receipt isnt working correctly");
        }
        catch(ItemNotFoundException e){
            fail("Got exception ItemNotFound");
        }
        catch(DataBaseUnacessibleException ex){
            fail("Got exception ItemNotFound");
        }

    } 

}
