package com.baobab.htc.presentation.forms;

import com.baobab.htc.presentation.midlets.*;
import com.baobab.htc.business.utils.*;
import javax.microedition.lcdui.*;

/**
 * This is the Session Type selection form for the HtcMidlet midlet
 *
 * @author Yamiko J. Msosa
 * @version 1.0
 * Date written: 04th May 2010
 */
public class SessionType extends Form implements CommandListener, ItemStateListener {

    /* The Midlet activating this Form */
    private final HtcMidlet htcMidlet;

    Image[] choices = null; // Null image array for ChoiceGroup objects' constructors

    /* Command Buttons for the session type form */
    private Command selectCommand = new Command("Select", Command.OK, 1);
    private Command sendCommand = new Command("Send", Command.OK, 1);
    private Command quitCommand = new Command("Exit", Command.EXIT, 3);

    /* Arrays for the choice group objects */
    String[] sessionTypes = {"Individual", "Comes with partner"};

    /* Site and session Information */
    private ChoiceGroup sessionType = new ChoiceGroup("Session Type", ChoiceGroup.EXCLUSIVE, sessionTypes, choices);

    /**
     * Constructor for the form
     *
     * @param htcMidlet The containing midlet for the form
     */
    public SessionType(HtcMidlet htcMidlet){
        super("Session Type");
        this.htcMidlet = htcMidlet;

            /* Setup the session type form */
            append(sessionType);
            addCommand(selectCommand);
            addCommand(sendCommand);
            addCommand(quitCommand);

            setCommandListener(this);
            setItemStateListener(this);
    }

    /**
     * Event handler for command buttons
     *
     * @param command Event trigger for the event handler
     * @param displayable Displayable object for the event handler
     */
    public void commandAction(Command command, Displayable displayable) {
        if (command == selectCommand) {
            processSessionTypeForm();
        } else if (command == sendCommand) {
            htcMidlet.setForm(ConstantHelper.TRANSMISSION_FORM);
        } else if (command == quitCommand) {
            htcMidlet.destroyApp(false);
            htcMidlet.notifyDestroyed();
        }
    }

    /**
     * Method will listen to state changes for the various form items and navigate to next item if
     * applicable
     *
     * @param item Item object for the event listener
     */
    public void itemStateChanged(Item item) {
        if (item == sessionType) {
            processSessionTypeForm();
        }
    }

    /**
     * Method to set session type selection in the containing HtcMidlet accordingly
     */
    private void processSessionTypeForm() {
        if (sessionType.getSelectedIndex() == ConstantHelper.COMES_WITH_PARTNER) {
            htcMidlet.setSessionStatus(ConstantHelper.FIRST_FORM);
        } else {
            htcMidlet.setSessionStatus(ConstantHelper.SINGLE_FORM);
        }
        htcMidlet.setForm(ConstantHelper.SESSION_FORM);
    }

    /**
     * Getter method for the SessionType ChoiceGroup object
     *
     * @return SessionType ChoiceGroup object
     */
    public ChoiceGroup getSessionType() {
        return sessionType;
    }
}
