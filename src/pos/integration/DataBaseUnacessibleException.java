
package pos.integration;


public class DataBaseUnacessibleException extends Exception {
    private String dataBaseNotRunning;

    /**
     * Creates a new instance with a message specifying which database is currently down.
     * Identifier 10 throws this exception
     * @param dataBaseNotRunning The database that wont run.
     */
    public DataBaseUnacessibleException(String dataBaseNotRunning) {
        super("Unable to start database" + dataBaseNotRunning + ", since it wont start");
        this.dataBaseNotRunning = dataBaseNotRunning;
    }


}