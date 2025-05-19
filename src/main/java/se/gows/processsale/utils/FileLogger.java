package se.gows.processsale.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
* Prints log messages to a file. The log file will be in the
* current directory and will be called log.txt.
*/
public class FileLogger {
     private PrintWriter logStream;

    /**
    * Creates a new instance and also creates a new log file.
    * An existing log file will be deleted.
    */
    public FileLogger(){
    try {
        logStream = new PrintWriter(new FileWriter("log.txt"), true);
    } catch (IOException ioe){
            System.out.println("Unable to write to log.txt.");
            ioe.printStackTrace();
        }
    }
    
    /**
    * Prints the specified string to the log file.
    * @param message The string that will be printed to the logfile.
    */
    public void log(String message){
        logStream.println(getTime() + " Exception thrown: " + message);
    }

    private String getTime(){
        LocalDateTime time = LocalDateTime.now();
        String timeString = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        return timeString;
    }
}

