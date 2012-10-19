package com.baobab.htc.presentation.forms;

import com.baobab.htc.presentation.midlets.*;
import com.baobab.htc.business.utils.*;
import javax.microedition.lcdui.*;

/**
 * This is the Session form for the HtcMidlet midlet
 *
 * @author Yamiko J. Msosa
 * @version 1.0
 * Date written: 04th May 2010
 */
public class Session extends Form implements CommandListener, ItemStateListener {

    /* The Midlet activating this Form */
    private final HtcMidlet htcMidlet;
    Image[] choices = null; // Null image array for ChoiceGroup objects' constructors

    /* Command Buttons for the HTC Form */
    private Command saveCommand = new Command("Save", Command.OK, 3);
    private Command resetCommand = new Command("Reset", Command.CANCEL, 1);
    private Command cancelCommand = new Command("Exit", Command.EXIT, 1);

    /* Arrays for the choice group objects */
    String[] yesNo = {"Not Selected", "Yes", "No"};
    String[] yesNoNotKnown = {"Not Selected", "Yes", "No", "Not Known"};
    String[] yesNoDontRememberNA = {"Not Selected", "Yes", "No", "Don't Remember"};
    String[] sexTypes = {"Not Selected", "Male", "Female - Non-pregnant", "Female - Pregnant"};
    String[] occupations = {"Not Selected", "Not working", "Formally Employed", "Self Employed", "Student", "Armed Forces/Police", "Other"};
    String[] highestEducations = {"Not Selected", "None", "Some primary", "Completed primary", "Secondary", "Tertiary"};
    String[] maritalStatusTypes = {"Not Selected", "Never Married", "Married/Living In - Monogamous", "Married/Living In - Polygamous", "Separated or Divorced", "Widowed"};
    String[] mostImportantReasonOptions = {"Not Selected", "Engaged in Risky Behaviour Recently", "Worried About Own Health",
        "Suspect or Know Partner in Risky Behaviour", "Partner's Health is Concerning/Partner is HIV+",
        "Before Initiating a Sexual Relationship", "Pre-marital Testing(if not yet sexually active)",
        "Pregnant", "2nd Test(confirmation after window period)", "Don't Believe Previous HIV+ Result",
        "Referral from Health Worker", "Referral from Family or Friend", "Other"};
    String[] everHivTestedBeforeOptions = {"Not Selected", "No", "Yes - HIV+", "Yes - HIV-", "Yes - Did Not Get Final Results"};
    String[] oftenGetTestedOptions = {"Not Selected", "Every 3 Months", "Every 6 Months", "Every 12 Months", "Every Other Year", "Other"};
    String[] oftenDrinksAlcoholOptions = {"Not Selected", "Dont Drink", "A Few Nights Each Week", "Once a Week", "2-3 Times a Month", "Every Few Months","Hardly Ever"};
    String[] sexualPartnersOptions = {"Not Selected", "0", "1", "2", "3", "4", "5+"};
    String[] sexualRelationshipsOptions = {"Haven't Had Sex in Last 6 Months", "Spouse or Live-in Partner", "Serious Partner", "Casual Partner", "Commercial"};
    String[] statusOfPartnersOptions = {"Not Selected", "No", "Yes - All Negative", "Yes - At Least One Positive"};
    String[] frequentlyUseCondomOptions = {"Not Selected", "Never", "Sometimes", "Always", "Can't Remember"};
    String[] familyPlanningMethodsOptions = {"Not Selected", "No - Trying to get Pregnant",
        "No - Not Trying to get Pregnant and not Using Family Planning", "Yes"};
    String[] testResultTypes = {"Not Selected", "Non Reactive", "Reactive", "Inconclusive"};
    String[] finalResultTypes = {"Not Selected", "Non Reactive", "Reactive", "Inconclusive 0 - Return in 6 Weeks", "Exposed Infant"};
    String[] symptoms = {"Cough for Three or More Weeks", "Night Sweats", "Unintentional Weight Loss",
        "Previous TB", "Abnormal Genital Discharge", "Sores or Ulcers in Genital Area",
        "Pain on Passing Urine"};
    String[] riskReductionPlansOptions = {"Abstinence", "Client Will Have One Partner", "Client Will Ask Partner to be Monogamous",
        "Client will increase use of Condoms", "Client will reduce number of Partners", "Eliminate high risk partners",
        "Reduce Alcohol/Drug Use", "Retest for HIV", "Test Partner for HIV", "Attend PTC"};

    /* Site and session Information */
    private TextField traditionalAuthority = new TextField("T/A", "", 15, TextField.ANY);

    /* Demographic Information */
    private ChoiceGroup sex = new ChoiceGroup("Sex", ChoiceGroup.EXCLUSIVE, sexTypes, choices);
    private DateField dateOfBirth = new DateField("Date of Birth", DateField.DATE); // Not a text field but a data field
    private ChoiceGroup occupation = new ChoiceGroup("Occupation", ChoiceGroup.EXCLUSIVE, occupations, choices);
    private ChoiceGroup highestEducation = new ChoiceGroup("Highest Education", ChoiceGroup.EXCLUSIVE, highestEducations, choices);
    private ChoiceGroup maritalStatus = new ChoiceGroup("Marital Status", ChoiceGroup.EXCLUSIVE, maritalStatusTypes, choices);
    private ChoiceGroup mostImportantReason = new ChoiceGroup("Reason One Came for Testing Today", ChoiceGroup.EXCLUSIVE, mostImportantReasonOptions, choices);

    /* History of Testing and HIV Risk */
    private ChoiceGroup everHivTestedBefore = new ChoiceGroup("Ever HIV Tested Before?", ChoiceGroup.EXCLUSIVE, everHivTestedBeforeOptions, choices);
    private ChoiceGroup oftenGetTested = new ChoiceGroup("How Often Do You Get Tested?", ChoiceGroup.EXCLUSIVE, oftenGetTestedOptions, choices);
    private ChoiceGroup oftenDrinksAlcohol = new ChoiceGroup("How Often Do You Drink Alcohol(>2)?", ChoiceGroup.EXCLUSIVE, oftenDrinksAlcoholOptions, choices);
    private ChoiceGroup sexualPartners = new ChoiceGroup("How Many Sexual Partners Have You Had in the Last 6 Months?", ChoiceGroup.EXCLUSIVE, sexualPartnersOptions, choices);
    private ChoiceGroup sexualRelationships = new ChoiceGroup("Which Type of Sexual Relationship Have You Had in the Last 6 Months?", ChoiceGroup.MULTIPLE, sexualRelationshipsOptions, choices);

    private ChoiceGroup statusOfAnyPartners = new ChoiceGroup("Know Status of ANY of Your Partners in the Last 6 Months?", ChoiceGroup.EXCLUSIVE, statusOfPartnersOptions, choices);
    private ChoiceGroup statusOfAllPartners = new ChoiceGroup("Know Status of ALL of Your Partners in the Last 6 Months?", ChoiceGroup.EXCLUSIVE, statusOfPartnersOptions, choices);
    private ChoiceGroup frequentlyUseCondom = new ChoiceGroup("In the Last 6 Months, How Frequently Did One Use a Condom with the Sexual Partners One Was Not Married To?", ChoiceGroup.EXCLUSIVE, frequentlyUseCondomOptions, choices);
    private ChoiceGroup useCondomWithSpouse = new ChoiceGroup("Did One Use a Condom with One's Spouse or Live in Partner?", ChoiceGroup.EXCLUSIVE, yesNoDontRememberNA, choices);

    /* Pregnancy and Family Planning */
    private ChoiceGroup usingFpMethod = new ChoiceGroup("Currently using FP method?", ChoiceGroup.EXCLUSIVE, yesNoNotKnown, choices);

    /* HIV Testing */
    private ChoiceGroup testResult1 = new ChoiceGroup("Test 1", ChoiceGroup.EXCLUSIVE, testResultTypes, choices);
    private ChoiceGroup testResult2 = new ChoiceGroup("Test 2", ChoiceGroup.EXCLUSIVE, testResultTypes, choices);
    private ChoiceGroup testResult3 = new ChoiceGroup("Test 3", ChoiceGroup.EXCLUSIVE, testResultTypes, choices);
    private ChoiceGroup finalResult = new ChoiceGroup("Final Result", ChoiceGroup.EXCLUSIVE, finalResultTypes, choices);

    /* Referrals and Risk Reduction */
    private ChoiceGroup hivSymptoms = new ChoiceGroup("Does the client have any of the following symptoms?", ChoiceGroup.MULTIPLE, symptoms, choices);
    private ChoiceGroup riskReductionPlans = new ChoiceGroup("Risk Reduction Plan", ChoiceGroup.MULTIPLE, riskReductionPlansOptions, choices);
    private TextField maleCondomsGiven = new TextField("Male Condoms given by councellor", "", 4, TextField.NUMERIC);
    private TextField femaleCondomsGiven = new TextField("Female Condoms given by councellor", "", 4, TextField.NUMERIC);

    /**
     * Constructor for the class
     *
     * @param htcMidlet The containing midlet for this form
     */
    public Session(HtcMidlet htcMidlet) {
        super("HTC Session");
        this.htcMidlet = htcMidlet;

        /* Site and session information */
        append(traditionalAuthority);

        /* Demographic information */
        append(sex);
        append(dateOfBirth);
        append(occupation);
        append(highestEducation);
        append(maritalStatus);
        append(mostImportantReason);

        /* History of HIV Testing and HIV-Risk */
        append(everHivTestedBefore);
        append(oftenGetTested);
        append(oftenDrinksAlcohol);
        append(sexualPartners);
        append(sexualRelationships);
        append(statusOfAnyPartners);
        append(statusOfAllPartners);
        append(frequentlyUseCondom);
        append(useCondomWithSpouse);

        /* Pregnancy and Family Planning */
        append(usingFpMethod);

        /* HIV Testing */
        append(testResult1);
        append(testResult2);
        append(testResult3);
        append(finalResult);

        /* Referrals and Risk Reduction */
        append(hivSymptoms);
        append(riskReductionPlans);
        append(maleCondomsGiven);
        append(femaleCondomsGiven);

        /* Commands for the form */
        addCommand(saveCommand);
        addCommand(resetCommand);
        addCommand(cancelCommand);

        setCommandListener(this);
        setItemStateListener(this);
    }

    /**
     * Event handler for command buttons
     *
     * @param command The trigger for this event handler
     * @param displayable Displayable object for the event handler
     */
    public void commandAction(Command command, Displayable displayable) {
        if (command == cancelCommand) {
            htcMidlet.destroyApp(false);
            htcMidlet.notifyDestroyed();
        } else if (command == saveCommand) {
            if (htcMidlet.getSessionStatus() == ConstantHelper.FIRST_FORM) {
                // Save/send HTC form and capture second form
                htcMidlet.saveData();
                htcMidlet.setSessionStatus(ConstantHelper.SECOND_FORM);
                htcMidlet.setForm(ConstantHelper.SESSION_FORM);
            } else {
                // Save/send HTC form and display the session type form
                boolean errorFree = htcMidlet.saveData();
                if (errorFree) {
                    resetFields();
                    htcMidlet.setForm(ConstantHelper.SESSION_TYPE_FORM);
                }
            }
        } else if (command == resetCommand) {
            resetFields();
            if (htcMidlet.getSessionStatus() != ConstantHelper.FIRST_FORM && htcMidlet.getSessionStatus() != ConstantHelper.SECOND_FORM) {
                htcMidlet.setForm(ConstantHelper.SESSION_TYPE_FORM);
            }
        }
    }

    /**
     * This method resets all the fields on this form
     */
    private void resetFields() {
        this.traditionalAuthority.setString(null);
        this.dateOfBirth.setDate(null);
        this.sex.setSelectedFlags(new boolean[sex.size()]);
        this.occupation.setSelectedFlags(new boolean[occupation.size()]);
        this.highestEducation.setSelectedFlags(new boolean[highestEducation.size()]);
        this.maritalStatus.setSelectedFlags(new boolean[maritalStatus.size()]);
        this.mostImportantReason.setSelectedFlags(new boolean[mostImportantReason.size()]);
        this.everHivTestedBefore.setSelectedFlags(new boolean[everHivTestedBefore.size()]);
        this.oftenGetTested.setSelectedFlags(new boolean[oftenGetTested.size()]);
        this.oftenDrinksAlcohol.setSelectedFlags(new boolean[oftenDrinksAlcohol.size()]);
        this.sexualPartners.setSelectedFlags(new boolean[sexualPartners.size()]);
        this.sexualRelationships.setSelectedFlags(new boolean[sexualRelationships.size()]);
        this.statusOfAnyPartners.setSelectedFlags(new boolean[statusOfAnyPartners.size()]);
        this.statusOfAllPartners.setSelectedFlags(new boolean[statusOfAllPartners.size()]);
        this.frequentlyUseCondom.setSelectedFlags(new boolean[frequentlyUseCondom.size()]);
        this.useCondomWithSpouse.setSelectedFlags(new boolean[useCondomWithSpouse.size()]);
        this.usingFpMethod.setSelectedFlags(new boolean[usingFpMethod.size()]);
        this.testResult1.setSelectedFlags(new boolean[testResult1.size()]);
        this.testResult2.setSelectedFlags(new boolean[testResult2.size()]);
        this.testResult3.setSelectedFlags(new boolean[testResult3.size()]);
        this.finalResult.setSelectedFlags(new boolean[finalResult.size()]);
        this.hivSymptoms.setSelectedFlags(new boolean[hivSymptoms.size()]);
        this.riskReductionPlans.setSelectedFlags(new boolean[riskReductionPlans.size()]);
        this.maleCondomsGiven.setString(null);
        this.femaleCondomsGiven.setString(null);
        ErrorAlert.getInstance().displayAlert(null, this, htcMidlet, traditionalAuthority); // Remove the ticker and set focus to traditional authority
    }

    /**
     * Method will listen to state changes for the various form items and navigate to next item if applicable
     *
     * @param item The Item object being listened to
     */
    public void itemStateChanged(Item item) {
        if (item == sex) {
            dateOfBirth.setLayout(ChoiceGroup.LAYOUT_VCENTER);
            Display.getDisplay(htcMidlet).setCurrentItem(dateOfBirth);
        } else if (item == occupation) {
            highestEducation.setLayout(ChoiceGroup.LAYOUT_VCENTER);
            Display.getDisplay(htcMidlet).setCurrentItem(highestEducation);
        } else if (item == highestEducation) {
            maritalStatus.setLayout(ChoiceGroup.LAYOUT_VCENTER);
            Display.getDisplay(htcMidlet).setCurrentItem(maritalStatus);
        } else if (item == maritalStatus) {
            Display.getDisplay(htcMidlet).setCurrentItem(mostImportantReason);
        } else if (item == mostImportantReason) {
            Display.getDisplay(htcMidlet).setCurrentItem(everHivTestedBefore);
        } else if (item == everHivTestedBefore) {
            if(everHivTestedBefore.getSelectedIndex() == 1){
                Display.getDisplay(htcMidlet).setCurrentItem(oftenDrinksAlcohol);
            } else {
                Display.getDisplay(htcMidlet).setCurrentItem(oftenGetTested);
            }
        } else if (item == oftenGetTested) {
            Display.getDisplay(htcMidlet).setCurrentItem(oftenDrinksAlcohol);
        } else if (item == oftenDrinksAlcohol) {
            Display.getDisplay(htcMidlet).setCurrentItem(sexualPartners);
        } else if (item == sexualPartners) {
            if(sexualPartners.getSelectedIndex() == 1){
                boolean booleanFlagsArray[] = new boolean[sexualRelationships.size()];
                booleanFlagsArray[0] = true;
                this.sexualRelationships.setSelectedFlags(booleanFlagsArray);
                if(sex.getSelectedIndex() == 1){
                    Display.getDisplay(htcMidlet).setCurrentItem(testResult1);
                } else {
                    Display.getDisplay(htcMidlet).setCurrentItem(usingFpMethod);
                }
            } else {
                Display.getDisplay(htcMidlet).setCurrentItem(sexualRelationships);
            }
        } else if (item == statusOfAnyPartners) {
            Display.getDisplay(htcMidlet).setCurrentItem(statusOfAllPartners);
        } else if (item == statusOfAllPartners) {
            Display.getDisplay(htcMidlet).setCurrentItem(frequentlyUseCondom);
        } else if (item == frequentlyUseCondom) {
             Display.getDisplay(htcMidlet).setCurrentItem(useCondomWithSpouse);
        } else if (item == useCondomWithSpouse) {
            if(sex.getSelectedIndex() == 1){
                Display.getDisplay(htcMidlet).setCurrentItem(testResult1);
            } else {
                Display.getDisplay(htcMidlet).setCurrentItem(usingFpMethod);
            }
        } else if (item == usingFpMethod) {
            Display.getDisplay(htcMidlet).setCurrentItem(testResult1);
        } else if (item == testResult1) {
            int selectedIndex = testResult1.getSelectedIndex();
            if (selectedIndex == 1) {
                Display.getDisplay(htcMidlet).setCurrentItem(finalResult);
            } else {
                Display.getDisplay(htcMidlet).setCurrentItem(testResult2);
            }
        } else if (item == testResult2) {
            int selectedIndex = testResult2.getSelectedIndex();
            if (testResult1.getSelectedIndex() == 2 && testResult2.getSelectedIndex() == 2) {
                Display.getDisplay(htcMidlet).setCurrentItem(finalResult);
            } else {
                Display.getDisplay(htcMidlet).setCurrentItem(testResult3);
            }
        } else if (item == testResult3) {
            Display.getDisplay(htcMidlet).setCurrentItem(finalResult);
        } else if (item == finalResult) {
            Display.getDisplay(htcMidlet).setCurrentItem(hivSymptoms);
        }
    }

    /**
     * Getter method for the traditionalAuthority TextField attribute
     *
     * @return The traditionalAuthority TextField attribute
     */
    public TextField getTraditionalAuthority() {
        return traditionalAuthority;
    }

    /**
     * Getter method for the sex ChoiceGroup object
     *
     * @return The sex ChoiceGroup object
     */
    public ChoiceGroup getSex() {
        return sex;
    }

    /**
     * Getter method for the dateOfBirth DateField object
     *
     * @return The dateOfBirth DateField object
     */
    public DateField getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Getter method for the occupation ChoiceGroup object
     *
     * @return The occupation ChoiceGroup object
     */
    public ChoiceGroup getOccupation() {
        return occupation;
    }

    /**
     * Getter method for the highestEducation ChoiceGroup object
     *
     * @return The highestEducation ChoiceGroup object
     */
    public ChoiceGroup getHighestEducation() {
        return highestEducation;
    }

    /**
     * Getter method for the maritalStatus ChoiceGroup object
     *
     * @return The maritalStatus ChoiceGroup object
     */
    public ChoiceGroup getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * Getter method for the mostImportantReasons ChoiceGroup object
     *
     * @return The mostImportantReasons ChoiceGroup object
     */
    public ChoiceGroup getMostImportantReason() {
        return mostImportantReason;
    }

    /**
     * Getter method for the everHivTestedBefore ChoiceGroup object
     *
     * @return The everHivTestedBefore ChoiceGroup object
     */
    public ChoiceGroup getEverHivTestedBefore() {
        return everHivTestedBefore;
    }

    /**
     * Getter method for the oftenGetTested ChoiceGroup object
     *
     * @return The oftenGetTested ChoiceGroup object
     */
    public ChoiceGroup getOftenGetTested() {
        return oftenGetTested;
    }

    /**
     * Getter method for the oftenDrinksAlcohol ChoiceGroup object
     *
     * @return The oftenDrinksAlcohol ChoiceGroup object
     */
    public ChoiceGroup getOftenDrinksAlcohol() {
        return oftenDrinksAlcohol;
    }

    /**
     * Getter method for the sexualPartners ChoiceGroup object
     *
     * @return The sexualPartners ChoiceGroup object
     */
    public ChoiceGroup getSexualPartners() {
        return sexualPartners;
    }

    /**
     * Getter method for the sexualRelationships ChoiceGroup object
     *
     * @return The sexualRelationships ChoiceGroup object
     */
    public ChoiceGroup getSexualRelationships() {
        return sexualRelationships;
    }

    /**
     * Getter method for the statusOfAnyPartners ChoiceGroup object
     *
     * @return The statusOfAnyPartners ChoiceGroup object
     */
    public ChoiceGroup getStatusOfAnyPartners() {
        return statusOfAnyPartners;
    }

    /**
     * Getter method for the statusOfAllPartners ChoiceGroup object
     *
     * @return The statusOfAllPartners ChoiceGroup object
     */
    public ChoiceGroup getStatusOfAllPartners() {
        return statusOfAllPartners;
    }

    /**
     * Getter method for the frequentlyUseCondom ChoiceGroup object
     *
     * @return The frequentlyUseCondom ChoiceGroup object
     */
    public ChoiceGroup getFrequentlyUseCondom() {
        return frequentlyUseCondom;
    }

    /**
     * Getter method for the useCondomWithSpouse ChoiceGroup object
     *
     * @return The useCondomWithSpouse ChoiceGroup object
     */
    public ChoiceGroup getUseCondomWithSpouse() {
        return useCondomWithSpouse;
    }

    /**
     * Getter method for the usingFpMethod ChoiceGroup object
     *
     * @return The usingFpMethod ChoiceGroup object
     */
    public ChoiceGroup getUsingFpMethod() {
        return usingFpMethod;
    }

    /**
     * Getter method for the testResult1 ChoiceGroup object
     *
     * @return The testResult1 ChoiceGroup object
     */
    public ChoiceGroup getTestResult1() {
        return testResult1;
    }

    /**
     * Getter method for the testResult2 ChoiceGroup object
     *
     * @return The testResult2 ChoiceGroup object
     */
    public ChoiceGroup getTestResult2() {
        return testResult2;
    }

    /**
     * Getter method for the testResult3 ChoiceGroup object
     *
     * @return The testResult3 ChoiceGroup object
     */
    public ChoiceGroup getTestResult3() {
        return testResult3;
    }

    /**
     * Getter method for the finalResult ChoiceGroup object
     *
     * @return The finalResult ChoiceGroup object
     */
    public ChoiceGroup getFinalResult() {
        return finalResult;
    }

    /**
     * Getter method for the hivSymptoms ChoiceGroup object
     *
     * @return The hivSymptoms ChoiceGroup object
     */
    public ChoiceGroup getHivSymptoms() {
        return hivSymptoms;
    }

    /**
     * Getter method for the riskReductionPlans ChoiceGroup object
     *
     * @return The riskReductionPlans ChoiceGroup object
     */
    public ChoiceGroup getRiskReductionPlans() {
        return riskReductionPlans;
    }

    /**
     * Getter method for the maleCondomsGiven TextField object
     *
     * @return The maleCondomsGiven TextField object
     */
    public TextField getMaleCondomsGiven() {
        return maleCondomsGiven;
    }

    /**
     * Getter method for the femaleCondomsGiven TextField object
     *
     * @return The femaleCondomsGiven TextField object
     */
    public TextField getFemaleCondomsGiven() {
        return femaleCondomsGiven;
    }
}
