package ftm.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ftm.base.BaseClass;

public class AmountKeying extends BaseClass {
	public AmountKeying() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//table/tbody/tr/td[6]")
	WebElement processingAndRemediation;
	
	@FindBy(xpath="//*[text() = 'Amount Keying']")
	WebElement amountKeying;
	
	@FindBy(xpath="//iframe[contains(@id,IFRAME) and @title='Amount Keying']")
	WebElement switchFrameWork;
	
	@FindBy(xpath="//button[@id='refreshlist']")
	WebElement getMoreItems;
	
	@FindBy(xpath="//div[@id='payidfield']/span[@class='redtextcenterbold']")
	WebElement msgTxt;
	
	@FindBy(xpath="//li[@class='nav-tab ng-scope closable active']/button")
	WebElement closeWindow;
	
	//In Amt Key, skip all and click 'get more' to check for remaining payments.
	public String amountKeyPayments() {
		// Navigate to Amount Key
		processingAndRemediation.click();
		amountKeying.click();
		//Switching the frame to Amount Keying
		driver.switchTo().frame(switchFrameWork);
		//clicking on Get More Items button
		getMoreItems.click();
		String msg = msgTxt.getText();	
		driver.switchTo().defaultContent();
		closeWindow.click();
		return msg;	
	}
}
