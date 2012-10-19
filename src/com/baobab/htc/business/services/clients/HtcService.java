package com.baobab.htc.business.services.clients;
import javax.xml.namespace.QName;

/**
 * This is the HtcService Web Service Interface
 *
 * @author Yamiko J. Msosa
 * @version 1.0
 * Date written: 04th May 2010
 */
public interface HtcService extends java.rmi.Remote {

    /**
     * The web service stub method to submit an XML string
     *
     * @param XmlDocument The XML Document string to be submitted
     * @return Status of the submit operation as integer
     * @throws java.rmi.RemoteException
     */
    public Integer submitXmlDocument(String XmlDocument) throws java.rmi.RemoteException;

}
