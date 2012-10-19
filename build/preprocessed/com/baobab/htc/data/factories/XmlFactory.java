package com.baobab.htc.data.factories;

import com.baobab.htc.business.beans.*;
import com.baobab.htc.business.utils.*;
import org.kxml2.kdom.*;

import java.util.*;

/**
 * This is the main XML factory for the necessary business beans
 *
 * @author Yamiko J. Msosa
 * @version 1.0
 *          Date written: 15th April 2010
 */
public class XmlFactory {

    /* Constants for the class */
    final int ERROR = -1;

    public static void main(String args[]) {
        XmlFactory xmlFactory = new XmlFactory();
        HtcBean htcBean = new HtcBean();
        String tempString = xmlFactory.htcBeanToXml(htcBean);
        System.out.println(tempString);
    }

    /**
     * Method generates an XML string from an HtcBean
     *
     * @param htcBean The HtcBean to be converted to XML String
     * @return The resulting XML String
     */
    public String htcBeanToXml(HtcBean htcBean) {
        Document document = new Document();
        Vector elements = new Vector(0);
        Element root = new Element();
        root.setName("HtcForm");

        /* Site and session details */
        createElement("FileReference", htcBean.getReference(), elements);
        createElement("SessionType", String.valueOf(htcBean.getSessionType()), elements);
        createElement("TestingType", String.valueOf(htcBean.getTestingType()), elements);
        createElement("CounsellorCode", htcBean.getCounsellorCode(), elements);
        createElement("SiteCode", String.valueOf(htcBean.getSiteCode()), elements);
        createElement("TerminalId", htcBean.getTerminalId(), elements);

        if (htcBean.getSessionDate() != null) {
            createElement("SessionDate", String.valueOf(htcBean.getSessionDate().getTime()), elements);
        } else {
            createElement("SessionDate", String.valueOf(ERROR), elements);
        }
        createElement("CouplesReference", htcBean.getCouplesReference(), elements);
        createElement("LotNumber1", htcBean.getLotNumber1(), elements);
        createElement("LotNumber2", htcBean.getLotNumber2(), elements);
        createElement("LotNumber3", htcBean.getLotNumber3(), elements);

        /* Demographics */
        createElement("TraditionalAuthority", htcBean.getTraditionalAuthority(), elements);
        createElement("Sex", String.valueOf(htcBean.getSex()), elements);
        if (htcBean.getDateOfBirth() != null) {
            createElement("DateOfBirth", String.valueOf(htcBean.getDateOfBirth().getTime()), elements);
        } else {
            createElement("DateOfBirth", String.valueOf(ERROR), elements);
        }
        createElement("CurrentOccupation", String.valueOf(htcBean.getCurrentOccupation()), elements);
        createElement("HighestEducation", String.valueOf(htcBean.getHighestEducation()), elements);
        createElement("MaritalStatus", String.valueOf(htcBean.getMaritalStatus()), elements);
        createElement("MostImportantReason", String.valueOf(htcBean.getMostImportantReason()), elements);

        /* History of HIV Testing and Risk */
        createElement("EverHivTestedBefore", String.valueOf(htcBean.getEverHivTestedBefore()), elements);
        createElement("OftenGetTested", String.valueOf(htcBean.getOftenGetTested()), elements);
        createElement("OftenDrinksAlcohol", String.valueOf(htcBean.getOftenDrinksAlcohol()), elements);
        createElement("SexualPartners", String.valueOf(htcBean.getSexualPartners()), elements);
        createElement("SexualRelationships", htcBean.getSexualRelationships(), elements);
        createElement("StatusOfAnyPartners", String.valueOf(htcBean.getStatusOfAnyPartners()), elements);
        createElement("StatusOfAllPartners", String.valueOf(htcBean.getStatusOfAllPartners()), elements);
        createElement("FrequentlyUseCondom", String.valueOf(htcBean.getFrequentlyUseCondom()), elements);
        createElement("UseCondomWithSpouse", String.valueOf(htcBean.getUseCondomWithSpouse()), elements);

        /* Pregnancy and Planning */
        createElement("UsingFpMethod", String.valueOf(htcBean.getUsingFpMethod()), elements);

        /* HIV Testing */
        createElement("TestResult1", String.valueOf(htcBean.getTestResult1()), elements);
        createElement("TestResult2", String.valueOf(htcBean.getTestResult2()), elements);
        createElement("TestResult3", String.valueOf(htcBean.getTestResult3()), elements);
        createElement("FinalResult", String.valueOf(htcBean.getFinalResult()), elements);

        /* Referrals and Risk Reduction */
        createElement("HivSymptoms", htcBean.getHivSymptoms(), elements);
        createElement("RiskReductionPlans", htcBean.getRiskReductionPlans(), elements);

        createElement("MaleCondomsGiven", String.valueOf(htcBean.getMaleCondomsGiven()), elements);
        createElement("FemaleCondomsGiven", String.valueOf(htcBean.getFemaleCondomsGiven()), elements);

        Object[] elementsArray = new Object[elements.size()];
        elements.copyInto(elementsArray);
        addElements(elementsArray, root);

        document.addChild(Element.ELEMENT, root);

        String xmlFile = XmlUtil.writeToString(document);

        return xmlFile;
    }

    /**
     * Method creates a full element given a Name and a String Value and a Collection of Elements to be added to
     *
     * @param elementName The name of the Element
     * @param stringValue The value of the named element
     * @param elements    The collection of Elements the  constructed Elements is to be added to
     */
    private void createElement(String elementName, String stringValue, Vector elements) {
        /*if (stringValue == null) {
            stringValue = "";
        } */
        Element element = new Element();
        element.setName(elementName);
        if(stringValue != null){
            element.addChild(Element.TEXT, stringValue);
        }
        elements.addElement(element);
    }


    /**
     * Method adds elements from an array of elements  to a root element
     *
     * @param elements    The collection of Elements to be added to a root Element
     * @param rootElement The containing root element
     * @return The resulting combination of nested set of elements
     */
    private Element addElements(Object[] elements, Element rootElement) {
        for (int i = 0; i < elements.length; i++) {
            Object element = elements[i];
            rootElement.addChild(Element.ELEMENT, element);
        }
        return rootElement;
    }

    /**
     * Method creates a named nested element from an integer array
     * <p/>
     * <p>
     * The named nested Element is created with child Elements in the form flagX where X is (0..n)
     * </p>
     *
     * @param elementName
     * @param flags
     * @param elements
     */
    private void createElement(String elementName, int[] flags, Vector elements) {
        Vector newElements = new Vector(0);
        Element element = new Element();
        element.setName(elementName);
        if (flags != null) {
            int counter = 0;
            for (int i = 0; i < flags.length; i++) {
                int flag = flags[i];
                counter++;
                Element childElement = new Element();
                StringBuffer flagName = new StringBuffer("Flag");
                flagName.append(String.valueOf(counter).trim());

                childElement.setName(flagName.toString());

                childElement.addChild(Element.TEXT, String.valueOf(flag));
                newElements.addElement(childElement);
            }
        }

        Object[] newElementsArray = new Object[newElements.size()];
        newElements.copyInto(newElementsArray);

        addElements(newElementsArray, element);
        elements.addElement(element);
    }
}
