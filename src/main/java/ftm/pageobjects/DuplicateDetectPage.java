package ftm.pageobjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ftm.base.BaseClass;

public class DuplicateDetectPage extends BaseClass {
	public DuplicateDetectPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//td[@title='Processing & Remediation']")
	WebElement processingRemediation;
	
	@FindBy(xpath="//*[text() = 'Duplicate Detect']/..")
	WebElement duplicateDetect;
	
	@FindBy(xpath="//*[text() = 'Manage Duplicates']/..")
	WebElement manageDuplicates;
	
	@FindBy(xpath="//*[text() = 'Units of Work']/..")
	WebElement unitsOfWork;	
	
	@FindBy(xpath="//iframe[contains(@id,IFRAME) and @title='Units of Work']")
	WebElement switchFramework;
	
	@FindBy(xpath="//input[@id='activebox']")
	WebElement activeUunitsOfWorkCheckbox;
	
	@FindBy(xpath="//input[@id='showallbox']")
	WebElement startAndEndTimesCheckbox;
	
	@FindBy(xpath="//input[@value='Show unit of work list']")
	WebElement showUnitOfWorkListButton;
	
	@FindBy(xpath="//a/img[@title='All not Duplicates']")
	WebElement allNotDuplicatesButton;
	
	@FindBy(xpath="//li[@class='nav-tab ng-scope closable active']/button")
	WebElement closeWindow;
	
	//Go to "Processing & Remediation > Duplicate Detect > Manage Duplicates > Units of Work"
	//Select the 2 check boxes "Only show active units of work	 & Do not use start and end times" button.
	public void manageDuplicates() throws InterruptedException {
		//Navigate to Units of Work
		processingRemediation.click();
		duplicateDetect.click();
		manageDuplicates.click();
		unitsOfWork.click();
		Thread.sleep(4000);
		driver.switchTo().frame(switchFramework);
		Thread.sleep(3000);
		activeUunitsOfWorkCheckbox.click();
		startAndEndTimesCheckbox.click();
		showUnitOfWorkListButton.click();
		Thread.sleep(2000);
		allNotDuplicatesButton.click();
		Thread.sleep(1000);
		//Accepting 'OK' alert
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Thread.sleep(4000);
		driver.switchTo().defaultContent();
		closeWindow.click();
	}
}
