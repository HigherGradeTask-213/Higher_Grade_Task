package pos.controller;

import java.util.ArrayList;
import java.util.List;
import pos.DTO.DiscountDTO;
import pos.DTO.ItemDTO;
import pos.DTO.PaymentDTO;
import pos.DTO.SaleDTO;
import pos.DTO.SaleInformationDTO;
import pos.discount.ItemDiscount;
import pos.discount.SaleDiscount;
import pos.integration.CashRegister;
import pos.integration.DCHandler;
import pos.integration.EASHandler;
import pos.integration.EISHandler;
import pos.integration.DataBaseUnacessibleException;
import pos.integration.ItemNotFoundException;
import pos.model.Sale;
import pos.model.SaleObserver;


/**
 * This is the applications only controller.
 * All calls passing to the model goes through this class
 * saleDTO refers to the collection of data from the completed sale.
 * paymentDTO refers to the collection of data from the payment.
 */
public class Controller {
    
    private EISHandler eis;
    private EASHandler eas;
    private DCHandler dc;
    
    private SaleDTO saleDTO;
    private PaymentDTO paymentDTO;
    
    private Sale sale;
    private CashRegister cashRegister;
    
    private List<SaleObserver> saleObservers = new ArrayList<>();

  
    /**
     * Starts a new sale. This method must be called before anything else is done during a sale.
     * 
     */
    public void startSale(){
        sale = new Sale();
        for(SaleObserver obs: saleObservers)
            sale.addSaleObserver(obs);
    }
    /**
     * 
     * @param eis handler for all calls to External Inventory System.
     * @param eas handler for all calls to External Accounting System.
     * cashRegister generates a new instance of a register.
     */
    public Controller(EISHandler eis,EASHandler eas,DCHandler dc){
        this.eis = eis;
        this.eas = eas;
        this.dc = dc;
        
        this.cashRegister = CashRegister.getInstance();
        
        System.out.println("Controller was started successfully!");
    }
    /**
     * A function that enters the items identifer into the External Inventory Database and adds the item and its information 
     * to the current sale.
     * @param itemIdentifier refers to the identifier on the item.
     * @return the saleInfrmoation for that specific item and the current total to the cashier. 
     * @throws ItemNotFoundException refers to when an item is scanned but it doesnt match any item in the external inventory database
     */
    public SaleInformationDTO enterItem(String itemIdentifier) throws ItemNotFoundException,DataBaseUnacessibleException,Exception{
        try{
            ItemDTO item = eis.findItem(itemIdentifier);  
            SaleInformationDTO saleInformation = sale.addItem(item);
            return saleInformation;
        }
        catch(ItemNotFoundException itemNotFound){
            System.err.println("Developer: tried to add item with identifer: "+itemIdentifier);
            throw itemNotFound;
        }
        catch(DataBaseUnacessibleException dataBaseNotStarting){
            System.err.println("Developer: Server/Database, External Inventory System is down");
            throw dataBaseNotStarting;
        }
        catch(Exception e){
            System.err.println("Something is wrong.");
            throw e;
        }
    }
    
    /**
     * Method that applies discount to the sale using startegy pattern
     * @return totalprice of the sale after discount has been added
     */
    public double applyDiscount(){
        SaleDTO saleDTO = sale.createSaleDTO();
        List<DiscountDTO> itemDiscounts=dc.findDiscounts(saleDTO,new ItemDiscount());
        List<DiscountDTO> saleDiscounts=dc.findDiscounts(saleDTO,new SaleDiscount());
       
        sale.applyItemDiscounts(itemDiscounts);
        sale.applyDiscounts(saleDiscounts);
        return sale.getTotalPrice();
    }
        
    
    /**
     * A function that handles the customer paying for thier purchase with cash.
     * Change is calculated from the amount the customer paid with the total pice of the purchase subtacted from it.
     * CashRegister is updated with the new amount paid.
     * @param amountPaid amount of money in cash paid by the customer
     * @param currency the currency of the cash paid by the customer.
     * @return the change left over from the purchase.
     */
    public double pay(double amountPaid,String currency){
        
        saleDTO = sale.createSaleDTO();
        paymentDTO = new PaymentDTO(amountPaid,currency);
        double change = Math.round((paymentDTO.getAmountPaid() - saleDTO.getTotalPrice()) * 100.0) / 100.0;

        cashRegister.updateAmountInRegister(amountPaid - change);
        return change;

    }
    /**
     * The function completes the sale by updating the external systems and printing a receipt to the customer.
     * The return is a placeholder for an actual receipt since the hardware of the receipt printer isnt implemented 
     * @return a receipt with the information of the current sale.
     */
    public String completeSale(){
        updateEISAndEAS();
        return sale.printReceipt(paymentDTO);
    }
    /**
    * The function updates the External inventory and External accounting System with the Sale and payment informaiton accordingly.
     * @param saleDTO a class holding the information about the sale
     * @param paymentDTO a class holding the information about the payment.
     */
    private void updateEISAndEAS(){
        eas.registerPayment(paymentDTO);
        eis.updateInventory(saleDTO);
    }
    /**
     * Observer will be notified when a sale has been paid for.
     * @param obs observer to notify
     */
    public void addSaleObserver(SaleObserver obs){
        saleObservers.add(obs);
    }

}
    
