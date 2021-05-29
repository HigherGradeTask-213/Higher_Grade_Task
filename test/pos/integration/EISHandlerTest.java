
package pos.integration;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import pos.DTO.PaymentDTO;
import pos.DTO.SaleDTO;
import pos.model.Sale;


public class EISHandlerTest {
    private ByteArrayOutputStream printOutBuffer;
    private PrintStream originalSysOut;

    @BeforeEach
    public void setUp() {
        printOutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printOutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
            }

    @AfterEach
    public void tearDown() {
        printOutBuffer = null;
        System.setOut(originalSysOut);
    }
    @Test
    public void testFindItem(){
        try {            
            String identifier = "1";
            EISHandler instance = new EISHandler();
            String expResult = "Tomato";
            String result = instance.findItem(identifier).getName();
            assertEquals(expResult, result);
        } 
        catch (ItemNotFoundException ex) {
            fail("Got exception itemNotFound.");
        } 
        catch (DataBaseUnacessibleException e) {
            fail("Got exception DataBaseUnacessible.");
        } 
    }
    @Test
    public void testItemNotFoundException() throws DataBaseUnacessibleException{
        String nonExistingItem = "4";
        EISHandler instance = new EISHandler();
        try {
            instance.findItem(nonExistingItem);
            fail("Exception ItemNotFound wasnt caught");

        } catch (ItemNotFoundException ex) {
            assertTrue(ex.getMessage().contains("not in the inventory"),"The exception message was wrong");

        } 
    }     
    @Test
    public void testDataBaseUnacessibleException() throws ItemNotFoundException{
        String dataBaseNotStartingIdentifier = "10";
        try {
            EISHandler instance = new EISHandler();
            instance.findItem(dataBaseNotStartingIdentifier);
            fail("Exception DataBaseUnacessible wasnt caught");

        } catch (DataBaseUnacessibleException ex) {
            assertTrue(ex.getMessage().contains("start database"),"The exception message was wrong");
        } 
    }
    @Test
    public void testEISHandlerLoggingCorrectly() {
        EISHandler instance = new EISHandler();
        Sale sale = new Sale();
        instance.updateInventory(sale.createSaleDTO());
        String printOut = this.printOutBuffer.toString();
        String expectedOutput = "Items logged";
        assertTrue(printOut.contains(expectedOutput),"EISHandler did not log correctly");
    }
    
}
