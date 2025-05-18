package se.gows.processsale.integration;
/**
 * Thrown when program is unable to contact the database.
 */
public class DatabaseFailureException extends Exception {
/**
 * Constructs a new exception with a message specifying that the database cannot be reached.
 */
    public DatabaseFailureException() {
        super("Problem when trying to connect to inventory database.");
    }
}
