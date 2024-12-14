package ftm.testcases;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ftm.base.BaseClass;
import ftm.dataprovider.DataProviders;
import ftm.pageobjects.AmountKeying;
import ftm.pageobjects.BatchICLsInboundPage;
import ftm.pageobjects.BatchICLsPage;
import ftm.pageobjects.BusinessdayPage;
import ftm.pageobjects.DuplicateDetectPage;
import ftm.pageobjects.E2E_testcase_page;
import ftm.pageobjects.InboundPhysicalTransmissionsPage;
import ftm.pageobjects.InboundTransmissionsPage;
import ftm.pageobjects.LoginPage;
import ftm.pageobjects.OutbondTransmissionsPage;
import ftm.utility.Copy_FTM_Files;

public class E2E_testcases extends BaseClass{
	@BeforeTest
	public void setup() throws IOException {
		lunchApp(); 
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
	BusinessdayPage busDay;
	OutbondTransmissionsPage OBT;
	
	/**
	 * Log in to the FTM CCUID 4x application using valid credentials.
	 */
	@Test(priority=0, dataProvider="credentials", dataProviderClass = DataProviders.class)
	public void loginTest(String username, String password) throws Throwable {	
	    LoginPage loginPage = new LoginPage();
	    Assert.assertTrue(loginPage.login(username, password));	    
	}
	
	/**
	 *  Navigate to and open/activate the Business Day(System Configuration > Business Day).
	 */
	@Test(priority=1)
	public void navigateToBusinessDay() throws InterruptedException {
		busDay=new BusinessdayPage();
		String state = busDay.activateTheCurrentBusinessDay();
		Assert.assertTrue(state.equals("Active"));	
	}
	
	/**
	 * Go to Inbound Physical Transmissions for the file status(Originations & Receipts > Physical).
	 */	
	@Test(priority=2)
	public void inboundPhysicalTransmissions() {
		InboundPhysicalTransmissionsPage IPT = new InboundPhysicalTransmissionsPage(); 
		String status = IPT.checkInboundPhysicalTransmissions();
		Assert.assertTrue(status.equals("Processed"));
	}
	
	/**
	 * Verify in Inbound Transmissions that the file is Accepted.
	 */	
	@Test(priority=3)
	public void inboundTransmissions() throws InterruptedException {
		InboundTransmissionsPage IT = new InboundTransmissionsPage();
		String condition = IT.inboundTransmissionsCondition();
		Assert.assertTrue(condition.equals("Accepted"));
	}
	
	/**
	 * Go to "Processing & Remediation > Duplicate Detect > Manage Duplicates > Units of Work"
	 * Select the 2 check boxes "Only show active units of work	 & Do not use start and end times" button.
	 */
	@Test(priority=4)
	public void manageDuplicateDetect() throws InterruptedException {
		DuplicateDetectPage DDP = new DuplicateDetectPage();
		DDP.manageDuplicates();
	}
	
	/**
	 * Go to "Origination & Receipt > Batch/ICL > Inbound" and verify the "Balanced"
	 */
	@Test(priority=5)
	public void verifyTheBalancedStatus() throws InterruptedException {
		BatchICLsInboundPage BatchICL = new BatchICLsInboundPage();
		boolean state = BatchICL.getBalancedStatus();
		Assert.assertTrue(state);
	}
	
	/**
	 * Go to "Distribution > Transmissions" Outbound Transmissions screen will apper, From the start field,
	 * select the active Business Day that the file was.
	 */
	@Test(priority=6)
	public void outBoundTransmissionAndSelectBussDay() throws InterruptedException {
		OBT = new OutbondTransmissionsPage();
		OBT.selectActiveBusinessDay();
	}
	
	/**
	 * Select all items on the Distribution and click the twisty icon 'Build and Release' from the dropdown list
	 * The 'Build X9 Confirmation' screen will list few options, select the Radio button to ignore missing
	 * images, and click the 'Build' button.
	 */
	@Test(priority=7)
	public void outBoundTransmissionBuildAndRelease() throws Exception {
		OBT = new OutbondTransmissionsPage();
		OBT.buildAndRelease();
	}
	
	@Test(priority=8)
	public void batchICLsProcessingPageToVerifySpecificFields() throws Exception {
		BatchICLsPage Bpage = new BatchICLsPage();
		Map<String, String> hashMap = Bpage.verifyingBatchICLsFields();
		for (Map.Entry<String, String> entry : hashMap.entrySet()) {
	        String key = entry.getKey();
	        String value = entry.getValue();

	        if((key.equals("Adjusted"))||
	        	(key.equals("Balanced"))||
	        	(key.equals("Captured"))||
	        	(key.equals("Created"))||
	        	(key.equals("Duplicate Detected"))||
	        	(key.equals("Duplicate Reviewed"))||
	        	(key.equals("Image Quality Analysis"))||
	        	(key.equals("Keyed"))||
	        	(key.equals("Loaded"))||
	        	(key.equals("Repaired"))||
	        	(key.equals("Reviewed"))
    		) {
	        	Assert.assertTrue(value.equals("Complete"),key);
	        }
		}
	}
	
}
