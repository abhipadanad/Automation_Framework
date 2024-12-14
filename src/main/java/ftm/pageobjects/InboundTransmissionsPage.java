package ftm.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import ftm.base.BaseClass;

public class InboundTransmissionsPage extends BaseClass{
	public InboundTransmissionsPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//table/tbody/tr/td[5]")
	WebElement originatorAndReceipt;
	
	@FindBy(xpath="//*[text() = 'Transmissions']")
	WebElement transmissions;
	
	@FindBy(xpath="//iframe[contains(@id,IFRAME) and @title='Inbound Transmissions']")
	WebElement switchFrameWork;
	
	@FindBy(xpath="//li[@class='nav-tab ng-scope closable active']/button")
	WebElement closeWindow;
	
	//Verify in Inbound Transmissions that the file is Accepted
	public String inboundTransmissionsCondition() throws InterruptedException {
		//Navigate to Inbound Transmissions
		originatorAndReceipt.click();
		transmissions.click();
		//Switching the frame to Inbound Physical Transmissions
		driver.switchTo().frame(switchFrameWork);
		//fetching Accept condition
		Thread.sleep(4000);
		WebElement condition = driver.findElement(By.xpath("//div[@class='gridxBody gridxBodyRowHoverEffect']/div[@role='row']/table/tbody/tr/td[9]"));
		String state = condition.getText();
		driver.switchTo().defaultContent();
		closeWindow.click();
		return state;
	}
}
