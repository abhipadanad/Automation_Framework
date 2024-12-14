package ftm.pageobjects;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ftm.base.BaseClass;
import ftm.utility.Log;

public class BusinessdayPage extends BaseClass{

	public BusinessdayPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//table/tbody/tr/td[4]")
	WebElement systemManagement;
	
	@FindBy(xpath="//*[text() = 'Business Days']")
	WebElement businessDay;
	
	 @FindBy(xpath="//iframe[contains(@id,IFRAME) and @title='Business Days']")
	 WebElement switchFrameWork;
	 
	 @FindBy(xpath="//td[@id=\"state\"]")
	 WebElement status;
	 
	 @FindBy(xpath="//img[@title = 'Selection Menu']")
	 WebElement actionButton;
	 
	 @FindBy(linkText="Activate")
	 WebElement activateButton;
	 
	 @FindBy(xpath="//td[@class='body-copysmall' and @id='date']")
	 WebElement dateWebElement;
	 
	 @FindBy(linkText="Close")
	 WebElement closeButton;
	 
	 @FindBy(linkText="Check End of Day")
	 WebElement checkEndOfDay;
	 
	 @FindBy(linkText="End of Day")
	 WebElement endOfDay;
	 
	 @FindBy(linkText="Purge")
	 WebElement purge;
	 
	 @FindBy(linkText="Purge Force")
	 WebElement purgeForce;
	 
	 @FindBy(xpath="//input[@value='Refresh']")
	 WebElement refresh;
	 
	 @FindBy(xpath="//li[@class='nav-tab ng-scope closable active']/button")
	 WebElement closeWindow;
	
	
	//Navigate to and open/activate the Business Day(System Configuration > Business Day).
	public String activateTheCurrentBusinessDay() throws InterruptedException {
		//Navigate to Business Day
		systemManagement.click();
		Thread.sleep(1000);
		businessDay.click();
		Thread.sleep(1000);
		Log.info("User in Business Day Page");
		//Switching the frame to Business day page
		driver.switchTo().frame(switchFrameWork);
		Thread.sleep(3000);
		refresh.click();
		String busdayStatus = status.getText();
		if(busdayStatus.equals("Open")) {
			//clicking the twisty icon
			actionButton.click();
			Thread.sleep(1000);
			//selecting Activate
			activateButton.click();		
			//Accepting 'OK' alert
			Alert alert = driver.switchTo().alert();
			alert.accept();
			Thread.sleep(5000);
			refresh.click();
		}
		Thread.sleep(2000);
		Log.info("Business Day Activated");
		//fetching Active status on Business Day
		busdayStatus = status.getText();
		driver.switchTo().defaultContent();
		closeWindow.click();
		return busdayStatus;
	}
	
	
}
