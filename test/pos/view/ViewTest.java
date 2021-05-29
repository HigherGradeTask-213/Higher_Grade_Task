package pos.view;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import pos.controller.Controller;
import pos.integration.DCHandler;
import pos.integration.DataBaseUnacessibleException;
import pos.integration.EASHandler;
import pos.integration.EISHandler;
import pos.integration.ItemNotFoundException;



public class ViewTest {
    private View instanceToTest;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;
    
    private EISHandler eis;
    private EASHandler eas;
    private DCHandler dc;
    @BeforeEach
    public void setUp(){
      
        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
        
        eis = new EISHandler();
        eas = new EASHandler();
        dc  =new DCHandler();
        Controller contr = new Controller(eis,eas,dc);
        instanceToTest = new View(contr);
    }
    @AfterEach
    public void tearDown(){
        instanceToTest = null;
        
        printoutBuffer = null;
        System.setOut(originalSysOut);
    }
    @Test
    public void testRunFakeExe() throws ItemNotFoundException, DataBaseUnacessibleException {
       instanceToTest.runFakeExe();
       String printout = printoutBuffer.toString();
       String expectedOutput = "Sale has started";
       assertTrue(printout.contains(expectedOutput),"UI did not start correctly");
    } 
}
