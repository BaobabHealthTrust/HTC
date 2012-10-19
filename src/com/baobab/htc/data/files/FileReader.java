package com.baobab.htc.data.files;

import com.baobab.htc.business.utils.*;
import javax.microedition.io.*;
import javax.microedition.io.file.*;
import java.io.*;
import java.util.*;

/**
 * This class encapsulates helper methods for file reading
 *
 * @author Yamiko J. Msosa
 * @version 1.0
 * Date written: 15th April 2010
 */
public class FileReader {

    /**
     * This method return the contents of the file at a given URL as a String
     *
     * @param url The URL for the file to be read
     * @return The resulting String from the read operation
     */
    public static String read(String url) {
        String fileContents = null;
        try {
            FileConnection fileConnection = (FileConnection) Connector.open(url);
            if (!fileConnection.exists()) {
                throw new IOException("File does not exist");
            }
            InputStream inputStream = fileConnection.openInputStream();
            StringBuffer stringBuffer = new StringBuffer();
            int chr, i = 0;

            // Read until the end of the stream
            while ((chr = inputStream.read()) != -1) {
                stringBuffer.append((char) chr);
            }

            fileContents = stringBuffer.toString();

            inputStream.close();
            fileConnection.close();
            fileConnection = null;
        } catch (IOException e) {
            // TODO: Log the error in system log
        }
        return fileContents;
    }

    /**
     * This method returns the number of documents for a given type at a particular root
     *
     * @param root The root to be read
     * @param extension The file extension depicting the type of files to be read
     * @return The resulting number of files at a given root
     */
    public static int getNumberOfDocuments(String root, String extension) {
        int numberOfDocuments = -1;

        try {
            FileConnection currentRoot = (FileConnection) Connector.open(root, Connector.READ);
            StringBuffer wildCard = new StringBuffer();
            wildCard.append(ConstantHelper.getInstance().getFileWildCardPrefix());
            wildCard.append(extension);
            Enumeration listOfFiles = currentRoot.list(wildCard.toString(), false);
            numberOfDocuments = 0;
            while (listOfFiles.hasMoreElements()) {
                String currentFile = (String) listOfFiles.nextElement();
                if (!currentFile.endsWith(ConstantHelper.getInstance().getFileSeparator())) {
                    numberOfDocuments++;
                }
            }

        } catch (IOException ioe) {
            // TODO: Log the error in system log
        }

        return numberOfDocuments;
    }

    /**
     * Method returns a vector/list of file names on a given root/directory and an extension
     *
     * @param root The root to be read
     * @param extension The file extension depicting the type of files to be read
     * @return The resulting vector/list of files at a given root
     */
    public static Vector getFileNames(String root, String extension) {
        Vector listOfFiles = new Vector(0);

        try {
            FileConnection currentRoot = (FileConnection) Connector.open(root, Connector.READ);
            StringBuffer wildCard = new StringBuffer();
            wildCard.append(ConstantHelper.getInstance().getFileWildCardPrefix());
            wildCard.append(extension);

            Enumeration elements = currentRoot.list(wildCard.toString(), false);
            while (elements.hasMoreElements()) {
                String currentFile = (String) elements.nextElement();
                if (!currentFile.endsWith(ConstantHelper.getInstance().getFileSeparator())) {
                    listOfFiles.addElement(currentFile);
                }
            }
        } catch (IOException ioe) {
            // TODO: Log the error in system log
        }

        return listOfFiles;
    }
}
