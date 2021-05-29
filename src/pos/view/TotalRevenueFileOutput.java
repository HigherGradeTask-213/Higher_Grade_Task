package pos.view;

import java.io.IOException;
import pos.util.PrintWriterComposition;
import pos.util.PrintWriterInhertance;

/*
 * TotalRevenueFileOutput This class writes the total income from the register since the program was started.
 */

class TotalRevenueFileOutput extends TotalRevenueDisplay {
    private PrintWriterComposition logFileComp;
    private PrintWriterInhertance logFileInherit;

    /**
     * Genreates a new instance of the TotalRevenueFileOutput class
     */
    TotalRevenueFileOutput() {
        try {
            logFileComp = new PrintWriterComposition();
            logFileInherit = new PrintWriterInhertance();
        } catch (IOException e) {
            System.out.println("[FOR DEVELOPER]: Could not create logger.");
            e.printStackTrace();
        }
    }

    @Override
    protected void doShowTotalIncome(double totalRevenue) throws Exception {
        logFileComp.println("Total revenue: " + totalRevenue);
        logFileInherit.println("Total revenue: " + totalRevenue);
    }

    @Override
    protected void handleErrors(Exception e) {
        System.err.println("[FOR DEVELOPER]: Something went wrong when writing to the file " + e.getMessage());
        e.printStackTrace();
    }
}