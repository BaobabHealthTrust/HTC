package com.baobab.htc.business.utils;

import java.util.*;

/**
 * This class provides string manipulation methods
 *
 * @author Yamiko J. Msosa
 * @version 1.0
 * Date written: 15th April 2010
 */
public class StringUtil {
    /**
     * This methos splits a given String into a String Array given a delimeter
     *
     * @param str The String to be split
     * @param c The delimeter for the given String
     * @return The resulting String Array
     */
    public static String[] split(String str, String c) {
        String r[] = null;
        if (str != null) {
            int lenght = str.length();
            int first = 0;
            Vector lista = new Vector();
            if (str.indexOf(c) != -1) {
                for (int i = 0; i < lenght; i++) {
                    if (i + c.length() <= lenght) {
                        if (str.substring(i, i + c.length()).equals(c)) {
                            lista.addElement(str.substring(first, i));
                            first = i + c.length();
                        }
                    }
                }
                if (!str.endsWith(c)) {
                    lista.addElement(str.substring(first, lenght));
                }
            } else {
                lista.addElement(str);
            }
            r = new String[lista.size()];
            for (int i = 0; i < lista.size(); i++) {
                r[i] = lista.elementAt(i).toString();
            }
        }
        return r;
    }
}
