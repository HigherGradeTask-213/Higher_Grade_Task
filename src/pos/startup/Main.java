package pos.startup;

import pos.controller.Controller;
import pos.integration.DCHandler;
import pos.integration.DataBaseUnacessibleException;
import pos.integration.EASHandler;
import pos.integration.EISHandler;
import pos.integration.ReceiptPrinter;
import pos.integration.ItemNotFoundException;
import pos.view.View;

/**
 * This is the start sequence of the entire application.
 */
public class Main {
    /**
     * The main method starts the entire application
     * @param args 
     */
    public static void main(String[] args) throws ItemNotFoundException, DataBaseUnacessibleException{
        EASHandler eas = new EASHandler();
        EISHandler eis = new EISHandler();
        ReceiptPrinter printer = new ReceiptPrinter();
        DCHandler dc = new DCHandler();
        Controller contr = new Controller(eis,eas,dc);
        View view = new View(contr);
        view.runFakeExe();
    }
}
