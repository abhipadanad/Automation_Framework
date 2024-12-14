package ftm.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import ftm.base.BaseClass;

public class InboundPhysicalTransmissionsPage extends BaseClass{
	public InboundPhysicalTransmissionsPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//table/tbody/tr/td[5]")
	WebElement originatorAndReceipt;
	
	@FindBy(xpath="//*[text() = 'Physical Transmissions']")
	WebElement physicalTransmissions;
	
	@FindBy(xpath="//td[@class='body-copysmall' and @headers='Status']")
	WebElement status;
	
	@FindBy(xpath="//iframe[contains(@id,IFRAME) and @title='Inbound Physical Transmissions']")
	WebElement switchFrameWork;
	
	@FindBy(xpath="//li[@class='nav-tab ng-scope closable active']/button")
	WebElement closeWindow;
	
	// Check Inbound Physical Transmissions for the file status.
	public String checkInboundPhysicalTransmissions() {
		// Navigate to Inbound Physical Transmissions
		originatorAndReceipt.click();
		physicalTransmissions.click();
		//Switching the frame to Inbound Physical Transmissions
		driver.switchTo().frame(switchFrameWork);
		// Fetching status
		String state = status.getText();
		driver.switchTo().defaultContent();
		closeWindow.click();
		return state;
	}
}
