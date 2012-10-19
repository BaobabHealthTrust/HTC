package com.baobab.htc.data.files;

import javax.microedition.io.*;
import javax.microedition.io.file.*;
import java.io.*;

/**
 * This class encapsulates helper methods for file writing
 *
 * @author Yamiko J. Msosa
 * @version 1.0
 * Date written: 15th April 2010
 */
public class FileWriter {

    /**
     * Method to write a specified string to file
     *
     * @param url The URL for the file to be written to
     * @param contents The String to be written to file
     * @return The writing operation result status
     */
    public static boolean writeToFile(String url, String contents) {
        boolean isWritten = false;
        try {
            FileConnection filecon = (FileConnection) Connector.open(url);
            // Always check whether the file or directory exists.
            // Create the file if it doesn't exist.
            if (filecon.exists()) {
                OutputStream dataOutputStream = filecon.openOutputStream();
                dataOutputStream.write(contents.getBytes());
                dataOutputStream.close();
                isWritten = true;
            }
            filecon.close();
            filecon = null;
        } catch (IOException ioe) {
            // TODO: Log the error in system log
        }
        return isWritten;
    }

    /**
     * Method to delete a specified file
     *
     * @param url The URL of the file to be deleted
     * @return The delete operation result status
     */
    public static boolean deleteFile(String url) {
        boolean isDeleted = false;
        try {
            FileConnection filecon = (FileConnection) Connector.open(url);
            // Always check whether the file or directory exists.
            // Create the file if it doesn't exist.
            if (filecon.exists()) {
                filecon.delete();
                isDeleted = true;
            }
            filecon.close();
            filecon = null;
        } catch (IOException ioe) {
            // TODO: Log the error in system log
        }
        return isDeleted;
    }

    /**
     * Method to create a specified file on the given resource
     *
     * @param url The URL of the file to be created
     * @return The create operation result status
     */
    public static boolean createFile(String url) {
        boolean isCreated = false;
        try {
            FileConnection filecon = (FileConnection) Connector.open(url);
            // Always check whether the file or directory exists.
            // Create the file if it doesn't exist.
            if (!filecon.exists()) {
                filecon.create();
                isCreated = true;
            }
            filecon.close();
        } catch (IOException ioe) {
            // TODO: Log the error in system log
        }
        return isCreated;
    }
}
