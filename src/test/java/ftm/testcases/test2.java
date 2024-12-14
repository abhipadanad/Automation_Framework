//package ftm.testcases;
//
//import java.io.IOException;
//
//import org.testng.Assert;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
//
//import ftm.base.BaseClass;
//import ftm.dataprovider.DataProviders;
//import ftm.pageobjects.BatchICLsPage;
//import ftm.pageobjects.BusinessdayPage;
//import ftm.pageobjects.ExpectedEventsPage;
//import ftm.pageobjects.HomePage;
//import ftm.pageobjects.LoginPage;
//import ftm.pageobjects.OutbondTransmissionsPage;
//
//public class test2 extends BaseClass{
//	
//	@BeforeTest(groups = {"Smoke","Sanity","Regression"})
//	public void setup() throws IOException {
//		lunchApp(); 
//	}
//	
//	@AfterTest(groups = {"Smoke","Sanity","Regression"})
//	public void tearDown() {
//		driver.quit();
//	}
//	
//	@Test(priority=0,groups = {"Smoke","Sanity"}, dataProvider="credentials", dataProviderClass = DataProviders.class)
//	public void E2E_Test_SC(String username, String password) throws Throwable {	
//		
//		//Login with the user credentials.
//	    LoginPage loginPage = new LoginPage();
//	    Assert.assertTrue(loginPage.login(username, password), "Invalid username/password..!!");
//	    
//	    //user in the Home Page
//	    HomePage homePage = new HomePage();
//		Assert.assertTrue(homePage.verifyHomePage(),"failed to open homepage!!");
//		
//		//Performing Business Day page actions.
//		BusinessdayPage bussDay = new BusinessdayPage();
//		bussDay.activeActionOnBusinessdayPage();
//		
//		//Performing Batch ICL page actions.
//		BatchICLsPage ICLpage = new BatchICLsPage();
//		ICLpage.actionOnBatchICLpage();
//		
//		//Performing Outbond Transmission page actions.
//		OutbondTransmissionsPage transmissionPage = new OutbondTransmissionsPage();
//		transmissionPage.actionOnTransmissions();
//		
//		ExpectedEventsPage exptEvntPage = new ExpectedEventsPage();
//		exptEvntPage.expectedEventsActions();
//	}
//}
