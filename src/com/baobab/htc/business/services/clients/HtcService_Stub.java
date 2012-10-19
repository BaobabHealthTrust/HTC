package com.baobab.htc.business.services.clients;

import javax.xml.rpc.JAXRPCException;
import javax.xml.namespace.QName;
import javax.microedition.xml.rpc.Operation;
import javax.microedition.xml.rpc.Type;
import javax.microedition.xml.rpc.ComplexType;
import javax.microedition.xml.rpc.Element;

/**
 * This is the HtcService Interface Implementation
 *
 * @author Yamiko J. Msosa
 * @version 1.0
 * Date written: 04th May 2010
 */
public class HtcService_Stub implements HtcService, javax.xml.rpc.Stub {

    private String[] _propertyNames;
    private Object[] _propertyValues;

    /**
     * Constructor for the class
     *
     * @param url URL Parameter depicting the endpoint address for the web service
     */
    public HtcService_Stub(String url) {
        _propertyNames = new String[] { ENDPOINT_ADDRESS_PROPERTY };
        _propertyValues = new Object[] { url };
    }

    /**
     * Default constructor for the class
     */
    public HtcService_Stub() {
        _propertyNames = new String[] { ENDPOINT_ADDRESS_PROPERTY };
        _propertyValues = new Object[] { "http://localhost:8080/axis2/services/HtcService.HtcServiceHttpSoap11Endpoint/" };
    }

    /**
     * This method sets property values
     *
     * @param name The name of the property to be set
     * @param value The value to be set
     */
    public void _setProperty( String name, Object value ) {
        int size = _propertyNames.length;
        for (int i = 0; i < size; ++i) {
            if( _propertyNames[i].equals( name )) {
                _propertyValues[i] = value;
                return;
            }
        }
        String[] newPropNames = new String[size + 1];
        System.arraycopy(_propertyNames, 0, newPropNames, 0, size);
        _propertyNames = newPropNames;
        Object[] newPropValues = new Object[size + 1];
        System.arraycopy(_propertyValues, 0, newPropValues, 0, size);
        _propertyValues = newPropValues;

        _propertyNames[size] = name;
        _propertyValues[size] = value;
    }

    /**
     * Generic getter method for a given property
     *
     * @param name The name of the property to be returned
     * @return The return value of the given property
     */
    public Object _getProperty(String name) {
        for (int i = 0; i < _propertyNames.length; ++i) {
            if (_propertyNames[i].equals(name)) {
                return _propertyValues[i];
            }
        }
        if (ENDPOINT_ADDRESS_PROPERTY.equals(name) || USERNAME_PROPERTY.equals(name) || PASSWORD_PROPERTY.equals(name)) {
            return null;
        }
        if (SESSION_MAINTAIN_PROPERTY.equals(name)) {
            return new Boolean(false);
        }
        StringBuffer error = new StringBuffer();
        error.append("Stub does not recognize property: ");
        error.append(name);
        throw new JAXRPCException(error.toString());
    }

    /**
     * Method prepares a given operation with applicable properties
     *
     * @param op The operation to be prepared
     */
    protected void _prepOperation(Operation op) {
        for (int i = 0; i < _propertyNames.length; ++i) {
            op.setProperty(_propertyNames[i], _propertyValues[i].toString());
        }
    }

    /**
     * This method submiths an XML String to a web service
     *
     * @param XmlDocument The String to be submitted
     * @return Submit operation status as integer
     * @throws java.rmi.RemoteException
     */
    public Integer submitXmlDocument(String XmlDocument) throws java.rmi.RemoteException {
        Object inputObject[] = new Object[] {
            XmlDocument
        };

        Operation op = Operation.newInstance( _qname_operation_submitXmlDocument, _type_submitXmlDocument, _type_submitXmlDocumentResponse );
        _prepOperation( op );
        op.setProperty( Operation.SOAPACTION_URI_PROPERTY, "urn:submitXmlDocument" );
        Object resultObj;
        try {
            resultObj = op.invoke( inputObject );
        } catch( JAXRPCException e ) {
            Throwable cause = e.getLinkedCause();
            if( cause instanceof java.rmi.RemoteException ) {
                throw (java.rmi.RemoteException) cause;
            }
            throw e;
        }

        return (Integer )((Object[])resultObj)[0];
    }

    protected static final QName _qname_operation_submitXmlDocument = new QName( "http://webservices.business.htc.baobab.com", "submitXmlDocument" );
    protected static final QName _qname_submitXmlDocumentResponse = new QName( "http://webservices.business.htc.baobab.com", "submitXmlDocumentResponse" );
    protected static final QName _qname_submitXmlDocument = new QName( "http://webservices.business.htc.baobab.com", "submitXmlDocument" );
    protected static final Element _type_submitXmlDocumentResponse;
    protected static final Element _type_submitXmlDocument;

    static {
        _type_submitXmlDocument = new Element( _qname_submitXmlDocument, _complexType( new Element[] {
            new Element( new QName( "http://webservices.business.htc.baobab.com", "XmlDocument" ), Type.STRING, 0, 1, true )}), 1, 1, false );
        _type_submitXmlDocumentResponse = new Element( _qname_submitXmlDocumentResponse, _complexType( new Element[] {
            new Element( new QName( "http://webservices.business.htc.baobab.com", "return" ), Type.INT, 0, 1, false )}), 1, 1, false );
    }

    /**
     * Method returns an individial Complex Type given an Array of elements
     *
     * @param elements The Element Array to be transformed into a Complex Type
     * @return The resulting Complex Type
     */
    private static ComplexType _complexType( Element[] elements ) {
        ComplexType result = new ComplexType();
        result.elements = elements;
        return result;
    }
}
