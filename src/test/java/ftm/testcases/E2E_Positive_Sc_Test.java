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
//public class E2E_Positive_Sc_Test extends BaseClass {
//	
//	@BeforeTest
//	public void setup() throws IOException {
//		lunchApp(); 
//	}
//	
//	@AfterTest
//	public void tearDown() {
//		driver.quit();
//	}
//	
//	//Login with the user credentials.
//	@Test(priority=0, dataProvider="credentials", dataProviderClass = DataProviders.class)
//	public void loginTest(String username, String password) throws Throwable {	
//	    LoginPage loginPage = new LoginPage();
//	    Assert.assertTrue(loginPage.login(username, password), "Invalid username/password..!!");
//	}
//	
//	//user in the Home Page
//	@Test(priority=1)
//	public void homePage() {	
//		HomePage homePage = new HomePage();
//		Assert.assertTrue(homePage.verifyHomePage(),"failed to open homepage!!"); 	    
//	}
//
//	//Performing Business Day page actions.
//	@Test(priority=2)
//	public void businessDayPage() throws Exception {	
//		BusinessdayPage bussDay = new BusinessdayPage();
//		bussDay.activeActionOnBusinessdayPage();
//	}
//
//	//Performing Batch ICL page actions.
//	@Test(priority=3)
//	public void batchICLpage() throws Exception {	
//		BatchICLsPage ICLpage = new BatchICLsPage();
//		ICLpage.actionOnBatchICLpage();
//	}
//
//	//Performing Outbond Transmission page actions.
//	@Test(priority=4)
//	public void outbondTransmissions() throws Exception {	
//		OutbondTransmissionsPage transmissionPage = new OutbondTransmissionsPage();
//		transmissionPage.actionOnTransmissions();
//	}
//
//	@Test(priority=5)
//	public void expectedEventsPage() throws Exception{
//		ExpectedEventsPage exptEvntPage = new ExpectedEventsPage();
//		exptEvntPage.expectedEventsActions();
//	}
//
//	//Closing Business Day Actions.
//	@Test(priority=6)
//	public void closeBusinessDay() throws Exception{
//		BusinessdayPage bussDay = new BusinessdayPage();
//		bussDay.closeBusinessDayActions();
//	}
//}
