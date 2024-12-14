//package ftm.testcases;
//
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//import ftm.pageobjects.BatchICLsPage;
//import ftm.pageobjects.BusinessdayPage;
//import ftm.pageobjects.HomePage;
//import ftm.pageobjects.LoginPage;
//
//public class E2E_Negative_Sc_Test {
//	
//	@Test(priority=0)
//	public void loginTest() throws Throwable {	
//	    LoginPage loginPage = new LoginPage();
//	    Assert.assertTrue(loginPage.login("fxhadmin", "fxhpass"), "Invalid username/password..!!");
//	}
//	
//	@Test(priority=1)
//	public void homePage() {	
//		HomePage homePage = new HomePage();
//		Assert.assertTrue(homePage.verifyHomePage(),"failed to open homepage!!"); 	    
//	}
//	
//	@Test(priority=2)
//	public void businessDayPage() throws Exception {	
//		BusinessdayPage bussDay = new BusinessdayPage();
//		bussDay.activeActionOnBusinessdayPage();
//	}
//	
//	@Test(priority=3)
//	public void batchICLpage() throws Exception {	
//		BatchICLsPage ICLpage = new BatchICLsPage();
//		ICLpage.actionOnBatchICLpage();
//	}
//
//}
