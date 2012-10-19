package com.baobab.htc.business.utils;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * This Singleton class displays given error alerts for the application
 *
 * @author Yamiko J. Msosa
 * @version 1.0
 * Date written: 04th May 2010
 */
public class ErrorAlert {

    /* Members of this class */
    private Image image;
    private Alert alert;
    private static ErrorAlert errorAlert = new ErrorAlert();
    private String message = null;
    private Ticker ticker;

    /**
     * Private constructor for the Singleton class
     */
    private ErrorAlert() {
        image = null;
        alert = new Alert("Error", "", image, AlertType.ERROR);
        ticker = null;
    }

    /**
     * This method returns the same instance of this Singleton everytime
     *
     * @return An instance of this class
     */
    public static ErrorAlert getInstance() {
        return errorAlert;
    }

    /**
     * Method displays an error alert given a message
     *
     * @param errorMessage The error message String to be displayed on the midlet
     * @param form The containing Form for the error message
     * @param midlet The containing Midlet for the Error message panel and nextForm Form
     */
    public void displayAlert(String errorMessage, Form form, MIDlet midlet) {
        /* Set ticker to error message if not null, otherwise nullfy ticker */
        message = errorMessage;
        if (errorMessage != null) {
            ticker = new Ticker(message);
        } else {
            ticker = null;
        }
        form.setTicker(ticker);
    }

    /**
     * Method displays an error alert given a message
     *
     * @param errorMessage The error message String to be displayed on the midlet
     * @param form The containing Form for the error message
     * @param midlet The containing Midlet for the Error message panel and nextForm Form
     * @param focusItem The next item to set focus to on the next form after error message display
     */
    public void displayAlert(String errorMessage, Form form, MIDlet midlet, Item focusItem) {
        /* Set ticker to error message if not null, otherwise nullfy ticker */
        message = errorMessage;
        if (errorMessage != null) {
            ticker = new Ticker(message);
        } else {
            ticker = null;
        }
        form.setTicker(ticker);
        Display.getDisplay(midlet).setCurrentItem(focusItem);

        /*image = null;

        alert.setString(errorMessage);
        alert.setTimeout(ConstantHelper.ALERT_TIMEOUT);

        Display.getDisplay(midlet).setCurrent(alert, nextForm);

        Display.getDisplay(midlet).setCurrent(alert, nextForm);

        boolean attemptDisplay = false;
        while (!attemptDisplay) {
        if (Display.getDisplay(midlet).getCurrent() == alert) {
        attemptDisplay = true;
        }
        if (attemptDisplay) {
        try {
        Thread.sleep(ConstantHelper.ALERT_TIMEOUT * 3);
        } catch (InterruptedException ex) {
        // TODO: Log Error to file
        }
        Display.getDisplay(midlet).setCurrentItem(focusItem);
        attemptDisplay = false;
        }
        }*/
    }

    private void displayMessage(String errorMessage, Form nextForm, MIDlet midlet) {
        image = null;
        alert.setString(errorMessage);
        alert.setTimeout(ConstantHelper.ALERT_TIMEOUT);

        Display.getDisplay(midlet).setCurrent(alert, nextForm);

    }
}
