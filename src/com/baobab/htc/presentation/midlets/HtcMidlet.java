package com.baobab.htc.presentation.midlets;

import com.baobab.htc.presentation.forms.*;
import com.baobab.htc.business.beans.*;
import com.baobab.htc.business.utils.*;
import com.baobab.htc.data.factories.*;
import com.baobab.htc.data.files.*;
import java.util.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import net.sf.microlog.core.Logger;
import net.sf.microlog.core.LoggerFactory;

/**
 * This is the main Midlet for the mobile application
 *
 * @author Yamiko J. Msosa
 * @version 1.0
 * Date written: 04th May 2010
 */
public class HtcMidlet extends MIDlet {

    //protected final Logger log = LoggerFactory.getLogger(getClass());
    //private FileAppender appender;

    /* Memory variables */
    StringBuffer couplesReference = new StringBuffer();

    /* Forms/screens for the App */
    private Login loginForm;
    private Transmission transmissionForm;
    private SessionType sessionTypeForm;
    private Session sessionForm;

    /* Control structure flags */
    private int sessionStatus = -1;

    /**
     * Constructor for the class
     */
    public HtcMidlet() {

        /* Initialise Midlet and create Login and HTC Forms */
        loginForm = new Login(this);
        transmissionForm = new Transmission(this);
        sessionTypeForm = new SessionType(this);
        sessionForm = new Session(this);

        //PropertyConfigurator.configure();// Configure MicroLog
        /*appender = new FileAppender();
        appender.setFileName("htc.log" );
        PatternFormatter formatter = new PatternFormatter();
        formatter.setPattern("%d{ISO8601} %P %c{1} - %m");
        appender.setFormatter(formatter);*/
        //log.addAppender(LoggingUtil.getFileAppender());
        //log.setLevel(ConstantHelper.LOGGING_LEVEL);
    }

    /**
     * This mandatory method is executed everytime the application is started
     */
    public void startApp() {
        //log.info("Starting App");
        Display.getDisplay(this).setCurrent(loginForm);
    }

    /**
     * This mandatory method is executed everytime the application is paused
     */
    public void pauseApp() {
    }

    /**
     * This mandatory method is executed everytime the application is closed
     */
    public void destroyApp(boolean unconditional) {
        //LoggerFactory.shutdown();// Shutdown MicroLog gracefully...
    }

    /**
     * Method to activate a particular form on display given a form identifier
     *
     * @param form The integer form identifier
     */
    public void setForm(int form) {
        //log.info("Setting form " + form);
        if (form == ConstantHelper.LOGIN_FORM) {
            Display.getDisplay(this).setCurrent(loginForm);
        } else if (form == ConstantHelper.TRANSMISSION_FORM) {
            transmissionForm = new Transmission(this);
            Display.getDisplay(this).setCurrent(transmissionForm);
        } else if (form == ConstantHelper.SESSION_TYPE_FORM) {
            Display.getDisplay(this).setCurrent(sessionTypeForm);
        } else if (form == ConstantHelper.SESSION_FORM) {
            Display.getDisplay(this).setCurrent(sessionForm);
        }
    }

    /**
     * Method to save form data to HTC Form Bean
     *
     * @return Boolean flag to indicate the save status of a particular HTC Session
     */
    public boolean saveData() {
        boolean isValid;
        HtcBean htcBean = new HtcBean();

        /* Site and Session Attributes */
        htcBean.setSessionType(sessionTypeForm.getSessionType().getSelectedIndex());
        try {
            htcBean.setSiteCode(Integer.parseInt(loginForm.getSiteCode().getString()));
        } catch (Exception ex) {
            //log.error("HtcMidlet Error - " + ex.toString());
            htcBean.setSiteCode(-1);
        }
        htcBean.setTerminalId(ConstantHelper.getInstance().getTerminalId());
        htcBean.setCounsellorCode(Security.getCounsellorCode(loginForm.getUserPIN().getString()));
        htcBean.setTestingType(loginForm.getTestingType().getSelectedIndex());
        htcBean.setLotNumber1(loginForm.getLotNumber1().getString());
        htcBean.setLotNumber2(loginForm.getLotNumber2().getString());
        htcBean.setLotNumber3(loginForm.getLotNumber3().getString());
        htcBean.setTraditionalAuthority(sessionForm.getTraditionalAuthority().getString());
        htcBean.setSessionDate(new Date());

        /* Generating couplesReference*/
        if (sessionStatus == ConstantHelper.FIRST_FORM) {
            couplesReference = new StringBuffer();
            couplesReference.append(generateCouplesReference());
        } else if (sessionStatus == ConstantHelper.SINGLE_FORM) {
            couplesReference = new StringBuffer();
        }

        htcBean.setCouplesReference(couplesReference.toString());

        /* Demographic Information */
        htcBean.setSex(sessionForm.getSex().getSelectedIndex() - 1);
        htcBean.setDateOfBirth(sessionForm.getDateOfBirth().getDate());
        htcBean.setCurrentOccupation(sessionForm.getOccupation().getSelectedIndex() - 1);
        htcBean.setHighestEducation(sessionForm.getHighestEducation().getSelectedIndex() - 1);
        htcBean.setMaritalStatus(sessionForm.getMaritalStatus().getSelectedIndex() - 1);

        /* Referrals and risk Reduction */
        htcBean.setMostImportantReason(sessionForm.getMostImportantReason().getSelectedIndex() - 1);

        /* History of HIV Testing and Risk */
        htcBean.setEverHivTestedBefore(sessionForm.getEverHivTestedBefore().getSelectedIndex() - 1);
        //if (htcBean.getEverHivTestedBefore() == 1 && sessionForm.getOftenGetTested().getSelectedIndex() == 1) {
        //    htcBean.setOftenGetTested(-1); // Set option to not applicable if one has never been tested before
        //} else {
        htcBean.setOftenGetTested(sessionForm.getOftenGetTested().getSelectedIndex() - 1);
        //}

        htcBean.setOftenDrinksAlcohol(sessionForm.getOftenDrinksAlcohol().getSelectedIndex() - 1);
        htcBean.setSexualPartners(sessionForm.getSexualPartners().getSelectedIndex() - 1);

        htcBean.setSexualRelationships(convertToIntArray(sessionForm.getSexualRelationships()));

        //if (htcBean.getSexualPartners() == 0 && sessionForm.getStatusOfAnyPartners().getSelectedIndex() == 0) {
        //    htcBean.setStatusOfAnyPartners(-1); //Set status of any partners option to N/A if no sexual partners option was selected
        // } else {
        htcBean.setStatusOfAnyPartners(sessionForm.getStatusOfAnyPartners().getSelectedIndex() - 1);
        //}

        //if (htcBean.getSexualPartners() == 0 && sessionForm.getStatusOfAllPartners().getSelectedIndex() == 0) {
        //    htcBean.setStatusOfAllPartners(-1); //Set status of all partners option to N/A if no sexual partners option was selected
        //} else {
        htcBean.setStatusOfAllPartners(sessionForm.getStatusOfAllPartners().getSelectedIndex() - 1);
        //}

        //if (htcBean.getSexualPartners() == 0 && sessionForm.getFrequentlyUseCondom().getSelectedIndex() == 0) {
        //    htcBean.setFrequentlyUseCondom(-1); //Set condom usage frequency option to N/A if no sexual partners option was selected
        //} else {
        htcBean.setFrequentlyUseCondom(sessionForm.getFrequentlyUseCondom().getSelectedIndex() - 1);
        //}

        //if (htcBean.getSexualPartners() == 0 && sessionForm.getUseCondomWithSpouse().getSelectedIndex() == 0) {
        //    htcBean.setUseCondomWithSpouse(-1); //Set condom usage with spouse option to N/A if no sexual partners option was selected
        //} else {
        htcBean.setUseCondomWithSpouse(sessionForm.getUseCondomWithSpouse().getSelectedIndex() - 1);
        //}

        /* Pregnancy and Family Planning */
        htcBean.setUsingFpMethod(sessionForm.getUsingFpMethod().getSelectedIndex() - 1);

        /* HIV Testing */
        htcBean.setTestResult1(sessionForm.getTestResult1().getSelectedIndex() - 1);
        htcBean.setTestResult2(sessionForm.getTestResult2().getSelectedIndex() - 1);
        htcBean.setTestResult3(sessionForm.getTestResult3().getSelectedIndex() - 1);
        htcBean.setFinalResult(sessionForm.getFinalResult().getSelectedIndex() - 1);

        /* Referrals and risk Reduction */
        htcBean.setHivSymptoms(convertToIntArray(sessionForm.getHivSymptoms()));
        htcBean.setRiskReductionPlans(convertToIntArray(sessionForm.getRiskReductionPlans()));

        try {
            htcBean.setMaleCondomsGiven(Integer.parseInt(sessionForm.getMaleCondomsGiven().getString()));
        } catch (Exception ex) {
            // TODO: Log Error in system log
            htcBean.setMaleCondomsGiven(0);
        }

        try {
            htcBean.setFemaleCondomsGiven(Integer.parseInt(sessionForm.getFemaleCondomsGiven().getString()));
        } catch (Exception ex) {
            // TODO: Log Error in system log
            htcBean.setFemaleCondomsGiven(0);
        }

        isValid = validateBean(htcBean);
        if (isValid) {
            /* Write To File */
            StringBuffer fileName = new StringBuffer();
            String fileReference = generateFileName(ConstantHelper.XML_EXTENSION);

            fileName.append(ConstantHelper.getInstance().getRoot());
            fileName.append(fileReference);
            htcBean.setReference(fileReference);

            XmlFactory xmlFactory = new XmlFactory();
            boolean fileCreated = FileWriter.createFile(fileName.toString());
            //System.out.println(fileCreated);
            boolean isWritten = FileWriter.writeToFile(fileName.toString(), xmlFactory.htcBeanToXml(htcBean));
            //System.out.println(isWritten);
        }
        return isValid;
    }

    /**
     * Method validates an HtcBean and displays corresponding error messages accordingly
     *
     * @param htcBean The HtcBean to be validated
     * @return The validation result status
     */
    private boolean validateBean(HtcBean htcBean) {
        boolean isValid = true;

        boolean riskReductionPlansSelected = false,
                sexualRelationshipsSelected = false;

        for (int i = 0; i < htcBean.getRiskReductionPlans().length; i++) {
            if (htcBean.getRiskReductionPlans()[i] > 0) {
                riskReductionPlansSelected = true;
            }
        }

        for (int i = 0; i < htcBean.getSexualRelationships().length; i++) {
            if (htcBean.getSexualRelationships()[i] > 0) {
                sexualRelationshipsSelected = true;
            }
        }

        if (htcBean.getTraditionalAuthority().trim().equals("")) {
            ErrorAlert.getInstance().displayAlert("T/A is Blank!", sessionForm, this, sessionForm.getTraditionalAuthority());
            isValid = false;
        } else if (!riskReductionPlansSelected) {
            ErrorAlert.getInstance().displayAlert("Please Select at Least One Risk Reduction Plan!", sessionForm, this, sessionForm.getRiskReductionPlans());
            isValid = false;
        } else if (!sexualRelationshipsSelected) {
            ErrorAlert.getInstance().displayAlert("Please Select at Least One Type of Sexual Relationship!", sessionForm, this, sessionForm.getSexualRelationships());
            isValid = false;
        } else if (htcBean.getDateOfBirth() == null) {
            ErrorAlert.getInstance().displayAlert("Invalid Date of Birth!", sessionForm, this, sessionForm.getDateOfBirth());
            isValid = false;
        } else if (htcBean.getDateOfBirth().getTime() > (new Date()).getTime()) {
            ErrorAlert.getInstance().displayAlert("Invalid Date of Birth!", sessionForm, this, sessionForm.getDateOfBirth());
            isValid = false;
        } else if (htcBean.getSex() == 0 && htcBean.getMostImportantReason() == 6) {
            ErrorAlert.getInstance().displayAlert("Invalid Reason for Testing - Male Can not be pregnant!", sessionForm, this, sessionForm.getMostImportantReason());
            isValid = false;
        } else if (htcBean.getEverHivTestedBefore() == 0 && htcBean.getOftenGetTested() > 0) {
            ErrorAlert.getInstance().displayAlert("Inconsistent Testing History!", sessionForm, this, sessionForm.getEverHivTestedBefore());
            isValid = false;
        } else if (htcBean.getSexualPartners() == 0 && (htcBean.getSexualRelationships()[1] + htcBean.getSexualRelationships()[2]
                + htcBean.getSexualRelationships()[3] + htcBean.getSexualRelationships()[4]) > 0) {
            ErrorAlert.getInstance().displayAlert("Inconsistent Relationship Types to # of Partners Selected!", sessionForm, this, sessionForm.getSexualRelationships());
            isValid = false;
        } else if (htcBean.getSexualPartners() == 0 && htcBean.getFrequentlyUseCondom() > -1) {
            ErrorAlert.getInstance().displayAlert("Inconsistent Condom Frequency Usage to # of Partners Selected!", sessionForm, this, sessionForm.getFrequentlyUseCondom());
            isValid = false;
        } else if (htcBean.getSexualPartners() == 0 && htcBean.getStatusOfAnyPartners() > -1) {
            ErrorAlert.getInstance().displayAlert("Inconsistent HIV Status of Any of one's Partners to # of Partners Selected!", sessionForm, this, sessionForm.getStatusOfAnyPartners());
            isValid = false;
        } else if (htcBean.getSexualPartners() == 0 && htcBean.getStatusOfAllPartners() > -1) {
            ErrorAlert.getInstance().displayAlert("Inconsistent HIV Status of All of one's Partners to # of Partners Selected!", sessionForm, this, sessionForm.getStatusOfAllPartners());
            isValid = false;
        } else if (htcBean.getSexualPartners() == 0 && htcBean.getUseCondomWithSpouse() > -1) {
            ErrorAlert.getInstance().displayAlert("Inconsistent condom usage with spouse to # of Partners Selected!", sessionForm, this, sessionForm.getUseCondomWithSpouse());
            isValid = false;
        }
        if (isValid) {
            if (htcBean.getSex() == -1) {
                ErrorAlert.getInstance().displayAlert("Sex Option Not Selected!", sessionForm, this, sessionForm.getSex());
                isValid = false;
            } else if (htcBean.getCurrentOccupation() == -1) {
                ErrorAlert.getInstance().displayAlert("Current Occupation Option Not Selected!", sessionForm, this, sessionForm.getOccupation());
                isValid = false;
            } else if (htcBean.getHighestEducation() == -1) {
                ErrorAlert.getInstance().displayAlert("Highest Educaton Option Not Selected!", sessionForm, this, sessionForm.getHighestEducation());
                isValid = false;
            } else if (htcBean.getMaritalStatus() == -1) {
                ErrorAlert.getInstance().displayAlert("Marital Status Option Not Selected!", sessionForm, this, sessionForm.getMaritalStatus());
                isValid = false;
            } else if (htcBean.getMostImportantReason() == -1) {
                ErrorAlert.getInstance().displayAlert("Most Important Reason Option Not Selected!", sessionForm, this, sessionForm.getMostImportantReason());
                isValid = false;
            } else if (htcBean.getEverHivTestedBefore() == -1) {
                ErrorAlert.getInstance().displayAlert("Ever HIV Tested Before Option Not Selected!", sessionForm, this, sessionForm.getEverHivTestedBefore());
                isValid = false;
            } else if (htcBean.getOftenDrinksAlcohol() == -1) {
                ErrorAlert.getInstance().displayAlert("Alcohol Drinking Frequency Option Not Selected!", sessionForm, this, sessionForm.getOftenDrinksAlcohol());
                isValid = false;
            } else if (htcBean.getSexualPartners() == -1) {
                ErrorAlert.getInstance().displayAlert("Sexual Partners Option Not Selected!", sessionForm, this, sessionForm.getSexualPartners());
                isValid = false;
            } else if (htcBean.getSexualPartners() > 0 && htcBean.getStatusOfAnyPartners() == -1) {
                ErrorAlert.getInstance().displayAlert("Status of ANY Partners Option Not Selected!", sessionForm, this, sessionForm.getStatusOfAnyPartners());
                isValid = false;
            } else if (htcBean.getSexualPartners() > 0 && htcBean.getStatusOfAllPartners() == -1) {
                ErrorAlert.getInstance().displayAlert("Status of ALL Partners Option Not Selected!", sessionForm, this, sessionForm.getStatusOfAllPartners());
                isValid = false;
            } else if (htcBean.getEverHivTestedBefore() > 0 && htcBean.getOftenGetTested() == -1) {
                ErrorAlert.getInstance().displayAlert("Testing Frequency Not Selected!", sessionForm, this, sessionForm.getOftenGetTested());
                isValid = false;
            } else if (htcBean.getTestResult1() == -1) {
                ErrorAlert.getInstance().displayAlert("Test Result 1 Option Not Selected!", sessionForm, this, sessionForm.getTestResult1());
                isValid = false;
            } else if (htcBean.getTestResult1() > 0 && htcBean.getTestResult2() == -1) {
                ErrorAlert.getInstance().displayAlert("Second Test is Missing!", sessionForm, this, sessionForm.getTestResult2());
                isValid = false;
            } else if (htcBean.getTestResult1() > 0 && htcBean.getTestResult2() == 0 && htcBean.getTestResult3() == -1) {
                ErrorAlert.getInstance().displayAlert("Third Test is Missing!", sessionForm, this, sessionForm.getTestResult3());
                isValid = false;
            } else if (htcBean.getTestResult1() > 0 && htcBean.getTestResult2() > 0 && htcBean.getFinalResult() <= 0) {
                ErrorAlert.getInstance().displayAlert("Inconsistent Final Result!", sessionForm, this, sessionForm.getFinalResult());
                isValid = false;
            } else if (htcBean.getTestResult1() == 0 && (htcBean.getTestResult2() >= 0 || htcBean.getTestResult3() >= 0)) {
                ErrorAlert.getInstance().displayAlert("Inconsistent 2nd or 3rd Test with 1st Test!", sessionForm, this, sessionForm.getTestResult1());
                isValid = false;
            } else if (htcBean.getFinalResult() > 0 && (!(htcBean.getTestResult1() > 0 || htcBean.getTestResult2() > 0 || htcBean.getTestResult3() > 0))) {
                ErrorAlert.getInstance().displayAlert("Inconsistent Final Result with 1/2/3 Test!", sessionForm, this, sessionForm.getFinalResult());
                isValid = false;
            } else if (htcBean.getFinalResult() == -1) {
                ErrorAlert.getInstance().displayAlert("Final Result Option Not Selected!", sessionForm, this, sessionForm.getFinalResult());
                isValid = false;
            }
        }
        return isValid;
    }

    /**
     * Method generates a file name with the given extension and sets the return value to the couplesReference attribute
     *
     * @param extension Extension for the filename
     * @return The generated filename
     */
    private String generateFileName(String extension) {
        StringBuffer couplesReference = new StringBuffer();
        Date today = new Date();
        couplesReference.append(loginForm.getUserPIN().getString().trim());
        couplesReference.append(loginForm.getSiteCode().getString().trim());
        couplesReference.append(today.getTime());
        couplesReference.append(".");
        couplesReference.append(extension.trim());
        return couplesReference.toString();
    }

    /**
     * Method generates a couples reference from a userName, siteCode and current time in milliseconds
     *
     * @return
     */
    private String generateCouplesReference() {
        StringBuffer couplesReference = new StringBuffer();
        Date today = new Date();
        couplesReference.append(loginForm.getUserPIN().getString().trim());
        couplesReference.append(loginForm.getSiteCode().getString().trim());
        couplesReference.append(today.getTime());
        return couplesReference.toString();
    }

    /**
     * Method converts ChoiceGroup Options to an integer array
     *
     * @param choiceGroup ChoiceGroup object to be converted to an integer array
     * @return The resulting integer array
     */
    private int[] convertToIntArray(ChoiceGroup choiceGroup) {
        boolean[] flags = new boolean[choiceGroup.size()];
        int[] intFlags;

        choiceGroup.getSelectedFlags(flags);
        intFlags = convertToIntArray(flags);

        return intFlags;
    }

    /**
     * Method converts a boolean array to an integer array
     *
     * @param booleanArray Boolean Array to be converted to an integer array
     * @return The resulting integer array
     */
    private int[] convertToIntArray(boolean[] booleanArray) {
        int[] intArray = new int[booleanArray.length];
        for (int i = 0; i < booleanArray.length; i++) {
            if (booleanArray[i]) {
                intArray[i] = 1;
            } else {
                intArray[i] = 0;
            }
        }
        return intArray;
    }

    /**
     * Getter method for the sessionStatus attribute
     *
     * @return The value of the session status attribute
     */
    public int getSessionStatus() {
        return sessionStatus;
    }

    /**
     * Setter method for the sessionStatus attribute
     *
     * @param sessionStatus The desired value for the sessionStatus attribute
     */
    public void setSessionStatus(int sessionStatus) {
        this.sessionStatus = sessionStatus;
    }
}
