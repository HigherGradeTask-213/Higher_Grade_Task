/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.view;

import pos.model.SaleObserver;

/**
 * Prints out total revenue when a new sale is completed
 */
class TotalRevenueView extends TotalRevenueDisplay {
    @Override
    protected void doShowTotalIncome(double totalRevenue) throws Exception {
        System.out.println("--- A message from TotalRevenueobserver ---");
        System.out.println("The total revenue since the program started is: " + totalRevenue);
        System.out.println("-------------------------------------------");
        
    }

    @Override
    protected void handleErrors(Exception e) {
        System.out.println("Could not display total revenue :(");
    }
}