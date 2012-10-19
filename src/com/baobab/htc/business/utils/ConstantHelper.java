package com.baobab.htc.business.utils;

import net.sf.microlog.core.Level;
import com.baobab.htc.data.files.*;
import java.util.*;

/**
 * This Singleton helper class defines all applicable constants for the application
 *
 * @author Yamiko J. Msosa
 * @version 1.0
 * Date written: 04th May 2010
 */
public class ConstantHelper {

    /* The constants */

    /**
     * Comes with partner flag
     */
    public static final int COMES_WITH_PARTNER = 1;
    
    /**
     * Alert timeout in milliseconds
     */
    public static final int ALERT_TIMEOUT = 4000;

    /**
     * Form selector flag for single form
     */
    public static final int SINGLE_FORM = 0;

    /**
     * Form selector flag for first form for multiple forms
     */
    public static final int FIRST_FORM = 1;

    /**
     * Form selector flag for second form for multiple forms
     */
    public static final int SECOND_FORM = 2;

    /**
     * XML file extension
     */
    public static final String XML_EXTENSION = "xml";

    /**
     * Error flag
     */
    public static final int ERROR = -1;

    /**
     * Login form identifier
     */
    public static final int LOGIN_FORM = 0;

    /**
     * File Transmission form identifier
     */
    public static final int TRANSMISSION_FORM = 1;

    /**
     * Session Type selection form identifier
     */
    public static final int SESSION_TYPE_FORM = 2;

    /**
     * Session Form identifier
     */
    public static final int SESSION_FORM = 3;

    /**
     * Logging level
     */
    public static final Level LOGGING_LEVEL = Level.DEBUG;

    /**
     * Log file for the app
     */
    public static final String LOG_FILE = "htc.log";

    /**
     * Nokia Properties file location
     */
    public final String PROPERTIES_FILE_NOKIA = "file:///e:/BHTServices/HTC.properties";

    /**
     * Alternative Properties file location
     */
    public final String PROPERTIES_FILE_ALTERNATIVE = "file:///root1/BHTServices/HTC.properties";

    /**
     * Blackberry Properties file location
     */
    public final String PROPERTIES_FILE_BLACKBERRY = "file:///SDCard/BHTServices/HTC.properties";

    /* Properties for this class */
    private String root;
    private String fileSeparator;
    private String fileWildCardPrefix;
    private String usersFileLocation;
    private String sitesFileLocation;
    private String htcServiceUrl;
    private String terminalId;
    private static ConstantHelper constantHelper = new ConstantHelper();
    private Hashtable properties = new Hashtable(0);

    /**
     * Private constructor for the Singleton
     */
    private ConstantHelper() {
        /* Get properties from the properties file using Nokia root */
        String propertiesString = FileReader.read(PROPERTIES_FILE_NOKIA);

        if(propertiesString == null){ // Read properties file from alternative root if not found on external drive
            propertiesString = FileReader.read(PROPERTIES_FILE_ALTERNATIVE);
        }

        if(propertiesString == null){ // Read properties file from blackberry SD Card root if not found on external drive
            propertiesString = FileReader.read(PROPERTIES_FILE_BLACKBERRY);
        }

        String[] propertiesArray = StringUtil.split(propertiesString, "~");

        for (int i = 0; i < propertiesArray.length; i++) {
            String[] propertyArray = StringUtil.split(propertiesArray[i], "$");
            properties.put(propertyArray[0], propertyArray[1]);
        }

        this.root = (String) properties.get("Root");
        this.fileSeparator = (String) properties.get("FileSeparator");
        this.fileWildCardPrefix = (String) properties.get("FileWildCardPrefix");
        this.usersFileLocation = (String) properties.get("UsersFileLocation");
        this.sitesFileLocation = (String) properties.get("SitesFileLocation");
        this.htcServiceUrl = (String) properties.get("HtcServiceUrl");
        this.terminalId = (String) properties.get("TerminalId");
    }

    /**
     * The method returns the same single instance of this Singleton everytime
     * 
     * @return The Singleton instance to be returned
     */
    public static ConstantHelper getInstance() {
        return constantHelper;
    }
    
    /**
     * Getter method for the root property
     * 
     * @return The string value of the root property
     */
    public String getRoot() {
        return root;
    }

    /**
     * Getter method for the fileSeparator property
     *
     * @return The string value of the fileSeparator property
     */
    public String getFileSeparator() {
        return fileSeparator;
    }

    /**
     * Getter method for the fileWildCardPrefix property
     *
     * @return The string value of the fileWildCardPrefix property
     */
    public String getFileWildCardPrefix() {
        return fileWildCardPrefix;
    }

    /**
     * Getter method for the usersFileLocation property
     *
     * @return The string value of the usersFileLocation property
     */
    public String getUsersFileLocation() {
        return usersFileLocation;
    }

    /**
     * Getter method for the sitesFileLocation property
     *
     * @return The string value of the sitesFileLocation property
     */
    public String getSitesFileLocation() {
        return sitesFileLocation;
    }

    /**
     * Getter method for the htcServiceUrl property
     *
     * @return The string value of the htcServiceUrl property
     */
    public String getHtcServiceUrl() {
        return htcServiceUrl;
    }

    /**
     * Getter method for the terminalId property
     *
     * @return The string value of the terminalId property
     */
    public String getTerminalId() {
        return terminalId;
    }
}
