package com.baobab.htc.presentation.forms;

import com.baobab.htc.presentation.midlets.*;
import com.baobab.htc.business.utils.*;
import javax.microedition.lcdui.*;

/**
 * This is the Login form for the HtcMidlet midlet
 *
 * @author Yamiko J. Msosa
 * @version 1.0
 * Date written: 04th May 2010
 */
public class Login extends Form implements CommandListener, ItemStateListener {

    private final HtcMidlet htcMidlet; // Login form for the midlet
    Image[] choices = null; // Null image array for ChoiceGroup objects' constructors
    String[] testingTypeTypes = {"Static", "Mobile Van", "Outreach - Car", "Outreach - Motorbike", "Door-to-door", "Moonlight"};

    /* Fields for the form */
    private TextField userPIN = new TextField("PIN", "", 10, TextField.PASSWORD |TextField.NUMERIC);
    private TextField siteCode = new TextField("Site", "", 10, TextField.NUMERIC);
    private ChoiceGroup testingType = new ChoiceGroup("Testing Type", ChoiceGroup.EXCLUSIVE, testingTypeTypes, choices);
    private TextField lotNumber1 = new TextField("Lot # 1", "", 10, TextField.ANY);
    private TextField lotNumber2 = new TextField("Lot # 2", "", 10, TextField.ANY);
    private TextField lotNumber3 = new TextField("Lot # 3", "", 10, TextField.ANY);

    /* Commands the login form */
    private Command exitCommand = new Command("Exit", Command.EXIT, 1);
    private Command loginCommand = new Command("login", Command.OK, 4);

    /**
     * Constructor for the form
     *
     * @param htcMidlet The containing midlet for the form
     */
    public Login(HtcMidlet htcMidlet) {
        super("Login");
        this.htcMidlet = htcMidlet;

        /* Setup the login screen */
        append(userPIN);
        append(siteCode);
        append(testingType);
        append(lotNumber1);
        append(lotNumber2);
        append(lotNumber3);

        addCommand(exitCommand);
        addCommand(loginCommand);

        /* Event listeners for the class */
        setCommandListener(this);
        setItemStateListener(this);
    }

    /**
     * Event handler for all command buttons for the form
     *
     * @param command The event trigger
     * @param displayable The displayable object
     */
    public void commandAction(Command command, Displayable displayable) {
        if (command == exitCommand) {
            htcMidlet.destroyApp(false);
            htcMidlet.notifyDestroyed();
        } else if (command == loginCommand) {
            boolean validLoginDetails = validateUser();
            String errorMessage = null;
            Item nextItem = null;

            if(validLoginDetails){
                if(lotNumber1.getString().trim().equals("")){
                    validLoginDetails = false;
                    errorMessage = "Missing Lot Number 1";
                    nextItem = lotNumber1;
                }

                if(validLoginDetails && lotNumber2.getString().trim().equals("")){
                    validLoginDetails = false;
                    errorMessage = "Missing Lot Number 2";
                    nextItem = lotNumber2;
                }

                if(validLoginDetails && lotNumber3.getString().trim().equals("")){
                    validLoginDetails = false;
                    errorMessage = "Missing Lot Number 3";
                    nextItem = lotNumber3;
                }

            }else{
                errorMessage = "Invalid Login Credentials!";
                nextItem = userPIN;
            }

            // Load the HTC Form if a valid user has logged at a valid site with Reagent Lot #s
            if (validLoginDetails) {
                htcMidlet.setForm(ConstantHelper.TRANSMISSION_FORM);
            } else {
                ErrorAlert.getInstance().displayAlert(errorMessage, this, htcMidlet, nextItem);
            }
        }
    }

    /**
     * Method will listen to state changes for the various form items and navigate to next item if applicable
     *
     * @param item The Item object being listened to
     */
    public void itemStateChanged(Item item) {
        if (item == testingType) {
            Display.getDisplay(htcMidlet).setCurrentItem(lotNumber1);
        }
    }

    /**
     * User authentication procedure
     *
     * @return Login status
     */
    private boolean validateUser() {
        boolean valid = true;
        String userPINString = userPIN.getString().trim();

        valid = Security.logUser(userPINString);

        if (!Security.validateSite(siteCode.getString().trim())) {
            valid = false;
        }
        return valid;
    }
    /**
     * Getter method for userName attribute
     *
     * @return User name TextField object
     */
    public TextField getUserPIN() {
        return userPIN;
    }

    /**
     * Getter method for siteCode attribute
     *
     * @return Site Code TextField object
     */
    public TextField getSiteCode() {
        return siteCode;
    }

    /**
     * Getter method for the testingType attribute
     *
     * @return Testing Type ChoiceGroup object
     */
    public ChoiceGroup getTestingType() {
        return testingType;
    }

    /**111
     * Getter method for the lotNumber1 attribute
     *
     * @return Reagent Lot Number 1 TextField object
     */
    public TextField getLotNumber1() {
        return lotNumber1;
    }


    /**
     * Getter method for the lotNumber2 attribute
     *
     * @return Reagent Lot Number 2 TextField object
     */
    public TextField getLotNumber2() {
        return lotNumber2;
    }

    /**
     * Getter method for the lotNumber3 attribute
     *
     * @return Reagent Lot Number 3 TextField object
     */
    public TextField getLotNumber3() {
        return lotNumber3;
    }
}
