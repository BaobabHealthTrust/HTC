package com.baobab.htc.data.factories;

import junit.framework.TestCase;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Assert;
import com.baobab.htc.business.beans.HtcBean;
import com.baobab.htc.business.utils.Security;

/**
 * Created by IntelliJ IDEA.
 * User: yamiko
 * Date: 31-May-2010
 * Time: 10:53:14
 * To change this template use File | Settings | File Templates.
 */
public class XmlFactoryTest extends TestCase {
    /* Members for the class */
    //private SequenceGenerationService service;
    //protected final Log logger = LogFactory.getLog(getClass());

    /**
     * Method to setup the test
     */
    public void setUp() {
        //DOMConfigurator.configure("service/log4j.xml"); // load log4j config
        //FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext("service/HtcApplicationContext.xml");
        //service = (SequenceGenerationService) applicationContext.getBean("sequenceGenerationService");
    }

    /**
     * This tests XML file from bean generation functionality
     */
    public void xtestGenerateXmlFile() {
        /* Test xml file generation */
        HtcBean htcBean = new HtcBean();
        XmlFactory xmlFactory = new XmlFactory();

        String xmlFile = xmlFactory.htcBeanToXml(htcBean);
        String xmlExpected = "<?xml version='1.0' encoding='UTF-8' ?><HtcForm><FileReference /><SessionType>-1</SessionType><TraditionalAuthority /><CounsellorCode /><SiteCode>0</SiteCode><TerminalId /><SessionDate>-1</SessionDate><ReturnVisit>-1</ReturnVisit><CouplesReference /><Sex>-1</Sex><DateOfBirth>-1</DateOfBirth><CurrentOccupation>-1</CurrentOccupation><HighestEducation>-1</HighestEducation><MaritalStatus>-1</MaritalStatus><MostImportantReasons /><EverHivTestedBefore>-1</EverHivTestedBefore><OftenGetTested>-1</OftenGetTested><OftenDrinksAlcohol>-1</OftenDrinksAlcohol><SexualPartners>-1</SexualPartners><SexualRelationships>-1</SexualRelationships><SameSexPartner>-1</SameSexPartner><StatusOfPartners>-1</StatusOfPartners><FrequentlyUseCondom>-1</FrequentlyUseCondom><UsingFpMethod>-1</UsingFpMethod><TestResult1>-1</TestResult1><TestResult2>-1</TestResult2><TestResult3>-1</TestResult3><FinalResult>-1</FinalResult><HivSymptoms /><RiskReductionPlans /><CondomsGiven>-1</CondomsGiven></HtcForm>";
        Assert.assertEquals(xmlExpected, xmlFile);


        xmlExpected = "<?xml version='1.0' encoding='UTF-8' ?><HtcForm><FileReference>TrialRef</FileReference><SessionType>-1</SessionType><TraditionalAuthority /><CounsellorCode /><SiteCode>0</SiteCode><TerminalId /><SessionDate>-1</SessionDate><ReturnVisit>-1</ReturnVisit><CouplesReference /><Sex>-1</Sex><DateOfBirth>-1</DateOfBirth><CurrentOccupation>-1</CurrentOccupation><HighestEducation>-1</HighestEducation><MaritalStatus>-1</MaritalStatus><MostImportantReasons /><EverHivTestedBefore>-1</EverHivTestedBefore><OftenGetTested>-1</OftenGetTested><OftenDrinksAlcohol>-1</OftenDrinksAlcohol><SexualPartners>-1</SexualPartners><SexualRelationships>-1</SexualRelationships><SameSexPartner>-1</SameSexPartner><StatusOfPartners>-1</StatusOfPartners><FrequentlyUseCondom>-1</FrequentlyUseCondom><UsingFpMethod>-1</UsingFpMethod><TestResult1>-1</TestResult1><TestResult2>-1</TestResult2><TestResult3>-1</TestResult3><FinalResult>-1</FinalResult><HivSymptoms /><RiskReductionPlans /><CondomsGiven>-1</CondomsGiven></HtcForm>";
        htcBean.setReference("TrialRef");
        xmlFile = xmlFactory.htcBeanToXml(htcBean);
        Assert.assertEquals(xmlExpected, xmlFile);

        /* Test valid key generation */
        /*myKey = service.getNextKey("District");
        logger.info("Key: " + myKey);
        Assert.assertTrue(myKey > -1);*/
    }

    public void testGenerateXmlHash() {
        for (int i=3000; i<3045; i++){
            System.out.println("" + i + "$" + Security.toMD5("" + i));    
        }

    }
}
