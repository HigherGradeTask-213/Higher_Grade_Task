/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.integration;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalTime;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import pos.DTO.PaymentDTO;
import pos.DTO.SaleDTO;
import pos.model.Item;


public class EASHandlerTest {
    private EASHandler instance;
    private ByteArrayOutputStream printOutBuffer;
    private PrintStream originalSysOut;

    @BeforeEach
    public void setUp() {
        printOutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printOutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
        
        instance = new EASHandler();
    }

    @AfterEach
    public void tearDown() {
        printOutBuffer = null;
        System.setOut(originalSysOut);

        instance = null;
    }

    @Test
    public void testEASHandlerLoggingCorrectly() {
        instance.registerPayment(new PaymentDTO(100, "USD"));
        String printOut = this.printOutBuffer.toString();
        String expectedOutput = "Payment logged";
        assertTrue(printOut.contains(expectedOutput),"EASHandler did not log correctly");
    }

}
