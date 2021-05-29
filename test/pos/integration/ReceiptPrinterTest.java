/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.integration;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import pos.DTO.PaymentDTO;
import pos.DTO.SaleDTO;
import pos.model.Receipt;
import pos.model.Sale;


public class ReceiptPrinterTest {
    private ReceiptPrinter instance;
    private ByteArrayOutputStream printOutBuffer;
    private PrintStream originalSysOut;

    @BeforeEach
    public void setUp() {
        printOutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printOutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
        
        instance = new ReceiptPrinter();
    }

    @AfterEach
    public void tearDown() {
        printOutBuffer = null;
        System.setOut(originalSysOut);

        instance = null;
    }
    @Test
    public void testPrinter() {
        Sale sale = new Sale();
        Receipt receipt = new Receipt(new SaleDTO(sale),new PaymentDTO(100, "USD"));
        instance.printReceiptToCustomer(receipt);
        String printOut = this.printOutBuffer.toString();
        String expectedOutput = "Receipt printed";
        assertTrue(printOut.contains(expectedOutput),"Printer did not log correctly");
    }
    
}
