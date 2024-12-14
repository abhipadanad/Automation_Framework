package ftm.testcases;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ftm.base.BaseClass;
import ftm.dataprovider.DataProviders;
import ftm.pageobjects.LoginPage;
import ftm.utility.Log;

public class E2E_Test extends BaseClass{
	@BeforeTest
	public void setup() throws IOException {
		lunchApp(); 
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
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
		WebElement systemManagement = driver.findElement(By.xpath("//table/tbody/tr/td[4]"));
		systemManagement.click();
		Thread.sleep(1000);
		WebElement businessDay = driver.findElement(By.xpath("//*[text() = 'Business Days']"));
		businessDay.click();
		Thread.sleep(1000);
		//Switching the frame to Business day page
		WebElement switchFrameWork = driver.findElement(By.xpath("//iframe[contains(@id,IFRAME) and @title='Business Days']"));
		driver.switchTo().frame(switchFrameWork);
		Thread.sleep(3000);
		//clicking the twisty icon
		WebElement actionButton = driver.findElement(By.xpath("//img[@title = 'Selection Menu']"));
		actionButton.click();
		Thread.sleep(1000);
		//selecting Activate
		WebElement activateButton = driver.findElement(By.linkText("Activate"));
		activateButton.click();		
		//Accepting 'OK' alert
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Thread.sleep(8000);
		WebElement refresh = driver.findElement(By.xpath("//input[@value='Refresh']"));
		refresh.click();
		Thread.sleep(2000);
		WebElement status =driver.findElement(By.xpath("//td[@id=\\\"state\\\"]"));
		String busdayStatus = status.getText();
		Assert.assertTrue(busdayStatus.equals("Active"));
		driver.switchTo().defaultContent();
		WebElement closeWindow = driver.findElement(By.xpath("//li[@class='nav-tab ng-scope closable active']/button"));
		closeWindow.click();
	}
	
	/**
	 * Go to Inbound Physical Transmissions for the file status(Originations & Receipts > Physical).
	 */	
	@Test(priority=2)
	public void checkInboundPhysicalTransmissions() {
		//Navigate to Inbound Physical Transmissions
		WebElement originatorAndReceipt = driver.findElement(By.xpath("//table/tbody/tr/td[5]"));
		originatorAndReceipt.click();
		WebElement physicalTransmissions = driver.findElement(By.xpath("//*[text() = 'Physical Transmissions']"));
		physicalTransmissions.click();
		//Switching the frame to Inbound Physical Transmissions
		WebElement switchFrameWork = driver.findElement(By.xpath("//iframe[contains(@id,IFRAME) and @title='Inbound Physical Transmissions']"));
		driver.switchTo().frame(switchFrameWork);
		WebElement status = driver.findElement(By.xpath("//td[@class='body-copysmall' and @headers='Status']"));
		Assert.assertTrue(status.getText().equals("Processed"));
		driver.switchTo().defaultContent();
		WebElement closeWindow = driver.findElement(By.xpath("//li[@class='nav-tab ng-scope closable active']/button"));
		closeWindow.click();	
	}
	
	//Verify in Inbound Transmissions that the file is Accepted
	@Test(priority=5)
	public void inboundTransmissionsCondition() throws InterruptedException {
		//Navigate to Inbound Transmissions
		WebElement originatorAndReceipt = driver.findElement(By.xpath("//table/tbody/tr/td[5]"));
		originatorAndReceipt.click();
		WebElement transmissions = driver.findElement(By.xpath("//*[text() = 'Transmissions']"));
		transmissions.click();
		//Switching the frame to Inbound Physical Transmissions
		WebElement switchFrameWork = driver.findElement(By.xpath("//iframe[contains(@id,IFRAME) and @title='Inbound Transmissions']"));
		driver.switchTo().frame(switchFrameWork);
		//fetching Accept condition
		Thread.sleep(4000);
		WebElement condition = driver.findElement(By.xpath("//div[@class='gridxBody gridxBodyRowHoverEffect']/div[@role='row']/table/tbody/tr/td[9]"));
		System.out.println(condition.getText());
		Assert.assertTrue(condition.getText().equals("Accepted"));
		driver.switchTo().defaultContent();
		WebElement closeWindow = driver.findElement(By.xpath("//li[@class='nav-tab ng-scope closable active']/button"));
		closeWindow.click();
	}
}
