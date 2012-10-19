package com.baobab.htc.presentation.forms;

import com.baobab.htc.presentation.midlets.*;
import com.baobab.htc.business.utils.*;
import com.baobab.htc.business.services.clients.*;
import com.baobab.htc.data.files.*;
import javax.microedition.lcdui.*;
import java.util.*;
import java.rmi.*;
import javax.xml.rpc.*;

/**
 * This is the Transmission options form for the HtcMidlet midlet
 *
 * @author Yamiko J. Msosa
 * @version 1.0
 * Date written: 04th May 2010
 */
public class Transmission extends Form implements CommandListener {
    /* The Midlet activating this Form */

    private final HtcMidlet htcMidlet;

    /* Commands for the transmission form */
    private Command sendCommand = new Command("Send", Command.OK, 1);
    private Command continueCommand = new Command("Continue", Command.EXIT, 3);

    /**
     * Constructor for the Transmission form object
     * 
     * @param htcMidlet The containing Midlet for the form
     */
    public Transmission(HtcMidlet htcMidlet) {
        super("Transmission Form");
        this.htcMidlet = htcMidlet;

        /* Setup transmission form */
        deleteAll();
        append(getNumberOfXmlDocuments());

        addCommand(sendCommand);
        addCommand(continueCommand);
        setCommandListener(this);
    }

    /**
     * Event handler for command buttons
     *
     * @param command The Command object for the event handler
     * @param displayable The Displayable object for the event handler
     */
    public void commandAction(Command command, Displayable displayable) {
        String errorMessage = null;
        if (command == sendCommand) {
            ErrorAlert.getInstance().displayAlert("Sending. Please wait...", this, htcMidlet);
            errorMessage = sendXmlDocuments();
        }

        if (errorMessage == null) {
            htcMidlet.setForm(ConstantHelper.SESSION_TYPE_FORM);
        } else {
            ErrorAlert.getInstance().displayAlert(errorMessage, this, htcMidlet);
        }
    }

    /**
     * Method to count the number of xml documents in the outbox
     *
     * @return The number of XML Documents on the file system as string
     */
    private String getNumberOfXmlDocuments() {
        StringBuffer label = new StringBuffer();
        System.out.println("Getting number of XML Docs...");
        System.out.println(ConstantHelper.getInstance().getRoot());
        int numberOfDocuments = FileReader.getNumberOfDocuments(ConstantHelper.getInstance().getRoot(), ConstantHelper.XML_EXTENSION);

        label.append("(");
        label.append(String.valueOf(numberOfDocuments));
        label.append(") Outbox");

        return label.toString();
    }

    /**
     * Method to send all XML Documents on disk/storage
     *
     */
    private String sendXmlDocuments() {
        /* Get file names and store them in a vector */
        Vector listOfFiles = FileReader.getFileNames(ConstantHelper.getInstance().getRoot(), ConstantHelper.XML_EXTENSION);
        Object[] listOfFilesArray = new Object[listOfFiles.size()];
        listOfFiles.copyInto(listOfFilesArray);

        /* Error Message */
        String errorMessage = null;

        HtcService htcService = new HtcService_Stub(ConstantHelper.getInstance().getHtcServiceUrl()); // Initialise the webservice stub

        /* Send each file in turn from the list */
        for (int i = 0; i < listOfFilesArray.length; i++) {
            System.out.println("Sending File");
            int sentStatus = ConstantHelper.ERROR;
            String currentFile = (String) listOfFilesArray[i];
            StringBuffer fileName = new StringBuffer();
            fileName.append(ConstantHelper.getInstance().getRoot());
            fileName.append(currentFile);
            String contents = FileReader.read(fileName.toString());
            try {
                sentStatus = htcService.submitXmlDocument(contents).intValue(); // Send the file contents via a webservice
            } catch (RemoteException re) {
                sentStatus = ConstantHelper.ERROR;
                System.out.println("Error " + re);
                errorMessage = re.toString();
            } catch (JAXRPCException jre) {
                sentStatus = ConstantHelper.ERROR;
                System.out.println("Error " + jre);
                errorMessage = jre.toString();
            }
            if (sentStatus != ConstantHelper.ERROR) {
                FileWriter.deleteFile(fileName.toString());
            }
        }
        return errorMessage;
    }
}
