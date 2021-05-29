package pos.DTO;

/**
 * PaymentDTO is a class that holds data about the payment made and is used to transfer data between classes.
 * It is created in the pay function in Controller.
 */
public class PaymentDTO {
    private double amountPaid;
    private String currency;
    
    /**
     * @param amountPaid refers to the amount the customer paid
     * @param currency refers to the currency of the amount the customer paid.
     */
    public PaymentDTO(double amountPaid,String currency){
        this.amountPaid=amountPaid;
        this.currency=currency;
    }
     /**
     * Return the amount the customer paid with
     * @return double amount paid
     */
    public double getAmountPaid(){
        return amountPaid;
    }
     /**
     * Return the Currency the customer payed with.
     * @return String name of currency
     */
    public String getCurrency(){
        return currency;
    }
}
