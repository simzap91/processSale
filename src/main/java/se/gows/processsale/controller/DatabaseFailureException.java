package se.gows.processsale.controller;
/**
 * Generic exception class thrown to higher layers when any issues with database arises.
 */
public class DatabaseFailureException extends Exception {
    /**
    * Constructs a new exception with the message and cause passed to the constructor.
    */
    public DatabaseFailureException(String msg, Exception cause) {
        super(msg, cause);
    }
}
