package pos.integration;

/**
 * Thrown when trying to retrive item information from the database when its identifier is not in the system..
 */
public class ItemNotFoundException extends Exception {
    private String itemNotInSystemDatabase;

    /**
     * Creates a new instance with a message specifying for which identifier isnt in the system database.
     *
     * @param itemNotInSystemDatabase The item identifier that is not in the database.
     */
    public ItemNotFoundException(String itemNotInSystemDatabase) {
        super("Unable to find " + itemNotInSystemDatabase + ", since it is not in the inventory database");
        this.itemNotInSystemDatabase = itemNotInSystemDatabase;
    }

}