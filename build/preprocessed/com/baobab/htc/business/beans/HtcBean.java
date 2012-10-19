package com.baobab.htc.business.beans;

import java.util.*;

/**
 * This is the form bean for the HTC Session
 *
 * @author Yamiko J. Msosa
 * @version 1.0
 * Date written: 04th May 2010
 */
public class HtcBean {
    /*
     * Getter and Setters for the class
     */
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getSessionType() {
        return sessionType;
    }

    public void setSessionType(int sessionType) {
        this.sessionType = sessionType;
    }

    public int getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(int siteCode) {
        this.siteCode = siteCode;
    }

    public int getTestingType() {
        return testingType;
    }

    public void setTestingType(int testingType) {
        this.testingType = testingType;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getCounsellorCode() {
        return counsellorCode;
    }

    public void setCounsellorCode(String counsellorCode) {
        this.counsellorCode = counsellorCode;
    }

    public String getTraditionalAuthority() {
        return traditionalAuthority;
    }

    public void setTraditionalAuthority(String traditionalAuthority) {
        this.traditionalAuthority = traditionalAuthority;
    }

    public Date getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(Date sessionDate) {
        this.sessionDate = sessionDate;
    }

    public String getCouplesReference() {
        return couplesReference;
    }

    public void setCouplesReference(String couplesReference) {
        this.couplesReference = couplesReference;
    }

    public String getLotNumber1() {
        return lotNumber1;
    }

    public void setLotNumber1(String lotNumber1) {
        this.lotNumber1 = lotNumber1;
    }

    public String getLotNumber2() {
        return lotNumber2;
    }

    public void setLotNumber2(String lotNumber2) {
        this.lotNumber2 = lotNumber2;
    }

    public String getLotNumber3() {
        return lotNumber3;
    }

    public void setLotNumber3(String lotNumber3) {
        this.lotNumber3 = lotNumber3;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getCurrentOccupation() {
        return currentOccupation;
    }

    public void setCurrentOccupation(int currentOccupation) {
        this.currentOccupation = currentOccupation;
    }

    public int getHighestEducation() {
        return highestEducation;
    }

    public void setHighestEducation(int highestEducation) {
        this.highestEducation = highestEducation;
    }

    public int getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(int maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public int getMostImportantReason() {
        return mostImportantReason;
    }

    public void setMostImportantReason(int mostImportantReason) {
        this.mostImportantReason = mostImportantReason;
    }

    public int getEverHivTestedBefore() {
        return everHivTestedBefore;
    }

    public void setEverHivTestedBefore(int everHivTestedBefore) {
        this.everHivTestedBefore = everHivTestedBefore;
    }

    public int getOftenGetTested() {
        return oftenGetTested;
    }

    public void setOftenGetTested(int oftenGetTested) {
        this.oftenGetTested = oftenGetTested;
    }

    public int getOftenDrinksAlcohol() {
        return oftenDrinksAlcohol;
    }

    public void setOftenDrinksAlcohol(int oftenDrinksAlcohol) {
        this.oftenDrinksAlcohol = oftenDrinksAlcohol;
    }

    public int getSexualPartners() {
        return sexualPartners;
    }

    public void setSexualPartners(int sexualPartners) {
        this.sexualPartners = sexualPartners;
    }

    public int[] getSexualRelationships() {
        return sexualRelationships;
    }

    public void setSexualRelationships(int[] sexualRelationships) {
        this.sexualRelationships = sexualRelationships;
    }

    public int getStatusOfAnyPartners() {
        return statusOfAnyPartners;
    }

    public void setStatusOfAnyPartners(int statusOfAnyPartners) {
        this.statusOfAnyPartners = statusOfAnyPartners;
    }

    public int getStatusOfAllPartners() {
        return statusOfAllPartners;
    }

    public void setStatusOfAllPartners(int statusOfAllPartners) {
        this.statusOfAllPartners = statusOfAllPartners;
    }

    public int getFrequentlyUseCondom() {
        return frequentlyUseCondom;
    }

    public void setFrequentlyUseCondom(int frequentlyUseCondom) {
        this.frequentlyUseCondom = frequentlyUseCondom;
    }

    public int getUseCondomWithSpouse() {
        return useCondomWithSpouse;
    }

    public void setUseCondomWithSpouse(int useCondomWithSpouse) {
        this.useCondomWithSpouse = useCondomWithSpouse;
    }

    public int getUsingFpMethod() {
        return usingFpMethod;
    }

    public void setUsingFpMethod(int usingFpMethod) {
        this.usingFpMethod = usingFpMethod;
    }

    public int getTestResult1() {
        return testResult1;
    }

    public void setTestResult1(int testResult1) {
        this.testResult1 = testResult1;
    }

    public int getTestResult2() {
        return testResult2;
    }

    public void setTestResult2(int testResult2) {
        this.testResult2 = testResult2;
    }

    public int getTestResult3() {
        return testResult3;
    }

    public void setTestResult3(int testResult3) {
        this.testResult3 = testResult3;
    }

    public int getFinalResult() {
        return finalResult;
    }

    public void setFinalResult(int finalResult) {
        this.finalResult = finalResult;
    }

    public int[] getHivSymptoms() {
        return hivSymptoms;
    }

    public void setHivSymptoms(int[] hivSymptoms) {
        this.hivSymptoms = hivSymptoms;
    }

    public int[] getRiskReductionPlans() {
        return riskReductionPlans;
    }

    public void setRiskReductionPlans(int[] riskReductionPlans) {
        this.riskReductionPlans = riskReductionPlans;
    }

    public int getMaleCondomsGiven() {
        return maleCondomsGiven;
    }

    public void setMaleCondomsGiven(int maleCondomsGiven) {
        this.maleCondomsGiven = maleCondomsGiven;
    }

    public int getFemaleCondomsGiven() {
        return femaleCondomsGiven;
    }

    public void setFemaleCondomsGiven(int femaleCondomsGiven) {
        this.femaleCondomsGiven = femaleCondomsGiven;
    }

    /* Reference for the form */
    String reference;

    /*
     * Site and session attributes
     */
    private int sessionType = -1;
    private int siteCode = -1;
    private int testingType = -1;
    private String terminalId;
    private String counsellorCode;
    private Date sessionDate;
    private String couplesReference;
    private String lotNumber1;
    private String lotNumber2;
    private String lotNumber3;

    /*
     * Demographic Information
     */
    private String traditionalAuthority;
    private int sex = -1;
    private Date dateOfBirth;
    private int currentOccupation = -1;
    private int highestEducation = -1;
    private int maritalStatus = -1;
    private int mostImportantReason;

    /*
     * History of HIV Testing and Risk
     */
    private int everHivTestedBefore = -1;
    private int oftenGetTested = -1;
    private int oftenDrinksAlcohol = -1;
    private int sexualPartners = -1;
    private int[] sexualRelationships;
    private int statusOfAnyPartners = -1;
    private int statusOfAllPartners = -1;
    private int frequentlyUseCondom = -1;
    private int useCondomWithSpouse = -1;

    /*
     * Pregnancy and family planning
     */
    private int usingFpMethod = -1;

    /*
     * HIV Testing
     */
    private int testResult1 = -1;
    private int testResult2 = -1;
    private int testResult3 = -1;
    private int finalResult = -1;

    /*
     * Referrals and Risk Reduction
     */
    private int[] hivSymptoms;
    private int[] riskReductionPlans;
    private int maleCondomsGiven = -1;
    private int femaleCondomsGiven = -1;
}
