package se.gows.processsale.integration;

public class DatabaseNotRunningException extends Exception {
    public DatabaseNotRunningException() {
        super("Error 404: Database not running.");
    }
}
