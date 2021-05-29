package pos.integration;

/**
 * CashRegister is a placeholder for an actual cash register, this class holds the current amount of money in the register.
 */
public class CashRegister {
    private double amountInRegister;
    /**
     * When the class is created the constructer counts the current amount of money in the register.
     */
    private CashRegister(){
        this.amountInRegister = countAmountInRegister();
    }
    /**
     * The function counts the amount of money in the register.
     * @return the current amount of money in the register, this value is current hardcoded.
     */
    private double countAmountInRegister(){
        return 1000;
    } 
    
    private static class RegisterHolder{
        private static CashRegister instance = new CashRegister();
    }
    
    /**
     * 
     * @return the only instance of the register, a singleton. 
     */
    
    public static CashRegister getInstance(){
        return RegisterHolder.instance;
    }
    /**
     * The funciton returns the current amount in the cash register.
     * @return the current amount in the register.
     */
    public double getAmountInRegister(){
        return amountInRegister;
    }
    /**
     * Updates the amount of money in the register with the amount the cusomter paid.
     * @param amountPaid  amount of money the customer paid
     */
    public void updateAmountInRegister(double amountPaid){
        amountInRegister += amountPaid;
    }
}
