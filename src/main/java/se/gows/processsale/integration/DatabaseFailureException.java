package se.gows.processsale.integration;

public class DatabaseFailureException extends Exception {
    public DatabaseFailureException() {
        super("Database can not be called.");
    }
}
