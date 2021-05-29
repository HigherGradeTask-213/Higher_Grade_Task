package pos.view;

import pos.DTO.SaleInformationDTO;
import pos.controller.Controller;
import pos.integration.DataBaseUnacessibleException;
import pos.integration.ItemNotFoundException;

/**
 * Placeholder for the real view. Hardcoded with 
 * calls to all system operations in the controller.
 */
public class View {
    private Controller contr;
    /**
     * Creates a new instance.
     * @param contr , The controller used for all calls to other layers.
     */
    public View(Controller contr){
        this.contr = contr;
        contr.addSaleObserver(new TotalRevenueView());
        contr.addSaleObserver(new TotalRevenueFileOutput());
    }
    /**
     * Preforms a fake sale, by calling all system operaitions in the controller.
     * Change itemIdentifier to 10 to throw DataBaseUnacessibleExeption
     */
    public void runFakeExe(){
        
        double amountPaid = 80;
        String currency = "kr";
        String itemIdentifier = "1";
        contr.startSale();
        System.out.println("Sale has started");
        try{
            SaleInformationDTO addedItem = contr.enterItem("2");
            System.out.println(addedItem.getCurrentItemName()+" has been added");

            contr.enterItem("2");

            SaleInformationDTO secondItem = contr.enterItem(itemIdentifier);
            
            System.out.println(secondItem.getCurrentItemName()+" has been added"); 
            double totalPrice = contr.applyDiscount();
            System.out.println("Discount added.");
            System.out.println("TotalPrice: "+totalPrice+" "+currency);
            System.out.println("Customer pays "+amountPaid+" "+currency);

            double change = contr.pay(amountPaid, currency);
            String receipt = contr.completeSale();
            
            System.out.println("________________");
            System.out.println("Receipt \n"+receipt);
            System.out.println("Change: "+change+" "+currency);
        }
        catch(ItemNotFoundException e){
            System.err.println("User: Couldnt find item in system");
        }
        catch(DataBaseUnacessibleException ex){
            System.err.println("User: Server is down");
        }
        catch(Exception e){
            System.err.println("Something went wrong");
        }
    }
}
