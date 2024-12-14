package ftm.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ftm.base.BaseClass;
import ftm.dataprovider.DataProviders;
import ftm.pageobjects.LoginPage;

public class test1 extends BaseClass{
	
	@BeforeTest
	public void setup() throws IOException {
		lunchApp(); 
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
		//driver.close();
	}
	//Login with the user credentials.
	@Test
	public void TestCase(){	
	    //driver.findElement(By.TagName("body"))
		driver.switchTo().newWindow(WindowType.WINDOW);
		//driver.switchTo().newWindow(WindowType.TAB);
		driver.navigate().to("https://www.lambdatest.com/");
		
		
	}
}
