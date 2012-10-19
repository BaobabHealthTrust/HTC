package com.baobab.htc.business.utils;

import com.baobab.htc.business.utils.security.*;
import com.baobab.htc.data.files.*;
import java.util.*;

/**
 * This class provides methods for dealing with security/encryption issues
 *
 * @author Yamiko J. Msosa
 * @version 1.0
 * Date written: 15th April 2010
 */
public class Security {

    /**
     * Method to return an MD5 Hex Digest String given a raw String
     *
     * @param string The String to be converted
     * @return The resulting MD5 Digest String
     */
    public static String toMD5(String string) {
        //convert plaintext into bytes
        byte plain[] = string.trim().getBytes();

        // create MD5 object
        MD5 md5 = new MD5(plain);

        //get the resulting hashed byte
        byte[] result = md5.doFinal();

        //convert the hashed byte into hexadecimal character for display
        String hashResult = md5.toHex(result);

        return hashResult;
    }

    /**
     * Method returns a hexadecimal representation of a given string
     *
     * @param string The String to be Converted to Hex
     * @return The converted Hex String
     */
    public static String toHexString(String string) {
        char[] chars = string.trim().toCharArray();
        StringBuffer output = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            output.append(Integer.toHexString((int) chars[i]));
        }
        return output.toString();
    }

    /**
     * This is a user authenticating method given a User ID and password
     *
     * @param userPIN The user PIN to be verified
     * @return The authenticating procedure result status
     */
    public static boolean logUser(String userPIN) {
        boolean validUser = false;
        Hashtable users = new Hashtable(0);
        String usersString = FileReader.read(ConstantHelper.getInstance().getUsersFileLocation());
        String[] usersArray = StringUtil.split(usersString, "~");

        for (int i = 0; i < usersArray.length; i++) {
            String[] userArray = StringUtil.split(usersArray[i], "$");
            users.put(userArray[1], userArray[0]);
        }

        String storedPIN = (String) users.get(toMD5(userPIN));
        System.out.println("MD5 PIN  " + toMD5(userPIN));

        System.out.println("PIN  " + storedPIN);
        if (storedPIN != null) {
            validUser = true;
        }

        return validUser;
    }

    /**
     * This is a user authenticating method given a user ID and password
     *
     * @param userPIN The User PIN for the desired Counsellor Code
     * @return The Counsellor Code corresponding to the given User PIN
     */
    public static String getCounsellorCode(String userPIN) {
        String counsellorCode = null;
        Hashtable users = new Hashtable(0);

        String usersString = FileReader.read(ConstantHelper.getInstance().getUsersFileLocation());
        String[] usersArray = StringUtil.split(usersString, "~");

        for (int i = 0; i < usersArray.length; i++) {
            String[] userArray = StringUtil.split(usersArray[i], "$");
            users.put(userArray[1], userArray[0]);
        }

        counsellorCode = (String) users.get(toMD5(userPIN));

        if (counsellorCode != null) {
            counsellorCode = counsellorCode.trim();
        }

        return counsellorCode;
    }



    /**
     * This method validates a given site code
     *
     * @param siteCode The site code to be validated
     * @return The validation result status
     */
    public static boolean validateSite(String siteCode) {
        boolean validSite = false;
        Hashtable sites = new Hashtable(0);
        String sitesString = FileReader.read(ConstantHelper.getInstance().getSitesFileLocation());
        String[] sitesArray = StringUtil.split(sitesString, "~");

        for (int i = 0; i < sitesArray.length; i++) {
            sites.put(sitesArray[i], sitesArray[i]);
        }

        String storedSite = (String) sites.get(siteCode);

        if (storedSite != null) {
            validSite = true;
        }

        return validSite;
    }
}
