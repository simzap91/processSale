package se.gows.processsale.integration;

/**
 * Specific exception class thrown when the inventory database is not running.
 */
public class DatabaseNotRunningException extends Exception {
    /**
     * Creates a new instance of the class together with a message clarifying the specific problem.
     */
    public DatabaseNotRunningException() {
        super("Error 404: Database not running.");
    }
}
