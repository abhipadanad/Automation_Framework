package ftm.pageobjects;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import ftm.base.BaseClass;
import ftm.utility.Log;

public class E2E_testcase_page extends BaseClass {
	public E2E_testcase_page() {
		PageFactory.initElements(driver, this);
	}
	
	
	//Navigate to and open/activate the Business Day module. Skip if already open on file drop.
		public void navigateToBusinessDay() throws InterruptedException {
			WebElement systemManagement = driver.findElement(By.xpath("//table/tbody/tr/td[4]"));
			systemManagement.click();
			Thread.sleep(1000);
			WebElement businessDay = driver.findElement(By.xpath("//*[text() = 'Business Days']"));
			businessDay.click();
			Thread.sleep(1000);
			Log.info("User in Business Day Page");
			//Switching the frame to Business day page
			WebElement switchFrameWork = driver.findElement(By.xpath("//iframe[contains(@id,IFRAME) and @title='Business Days']"));
			driver.switchTo().frame(switchFrameWork);
			Thread.sleep(3000);
			WebElement refresh = driver.findElement(By.xpath("//input[@value='Refresh']"));
			refresh.click();
		}
		
		//Confirm the current Business Day is open. If after 10am, should be the next day's date.
		public void confirmTheCurrentBusinessDay() {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
			LocalDateTime now = LocalDateTime.now();  
			//System.out.println(dtf.format(now)); 
			WebElement dateWebElement =driver.findElement(By.xpath("//td[@class='body-copysmall' and @id='date']"));
			String date = dateWebElement.getText();
			Assert.assertTrue(date.equals(dtf.format(now)));
		}
		
		//Activate the current Business Day by clicking the twisty icon and selecting 'Activate > OK'
		public void activateTheCurrentBusinessDay() throws InterruptedException {
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
			Log.info("Business Day Activated");
			driver.switchTo().defaultContent();
			WebElement closeWindow = driver.findElement(By.xpath("//li[@class='nav-tab ng-scope closable active']/button"));
			closeWindow.click();
		}
		
		//Check Inbound Physical Transmissions for the file status.
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
		
		//In Amt Key, skip all and click 'get more' to check for remaining payments.
		public void amountKeyPayments() {
			////Navigate to Amount Key
			WebElement processingAndRemediation = driver.findElement(By.xpath("//table/tbody/tr/td[6]"));
			processingAndRemediation.click();
			WebElement amountKeying = driver.findElement(By.xpath("//*[text() = 'Amount Keying']"));
			amountKeying.click();
			//Switching the frame to Amount Keying
			WebElement switchFrameWork = driver.findElement(By.xpath("//iframe[contains(@id,IFRAME) and @title='Amount Keying']"));
			driver.switchTo().frame(switchFrameWork);
			//clicking on Get More Items button
			WebElement getMoreItems = driver.findElement(By.xpath("//button[@id='refreshlist']"));
			getMoreItems.click();
			WebElement msgTxt = driver.findElement(By.xpath("//div[@id='payidfield']/span[@class='redtextcenterbold']"));
			Assert.assertTrue(msgTxt.getText().contains("no more items"));		
			driver.switchTo().defaultContent();
			WebElement closeWindow = driver.findElement(By.xpath("//li[@class='nav-tab ng-scope closable active']/button"));
			closeWindow.click();
			
		}
}
