package ftm.pageobjects;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ftm.base.BaseClass;

public class OutbondTransmissionsPage extends BaseClass{
	public OutbondTransmissionsPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//table/tbody/tr/td[7]")
	WebElement distribution;
	
	@FindBy(xpath="//div[@class='MENU_ITEM_TEXT' and text()='Transmissions']")
	WebElement transmissions;
	
	@FindBy(xpath="//iframe[contains(@id,IFRAME) and @title='Outbound Transmissions']")
	WebElement switchFramework;
	
	@FindBy(xpath="//td/input[@id='gobutton' and @type='button']")
	WebElement refreshGoButton;	
	
	@FindBy(xpath="//a/img[@title='Selection Menu']")
	WebElement selectionMenu;
	
	@FindBy(linkText="Build and release")
	WebElement buildAndRelease;
	
	@FindBy(xpath="//input[@type='button' and @value='Build']")
	WebElement buildButton;
	
	@FindBy(xpath="//div[@class='dijitDialogPaneActionBar']/span[@class='idxDialogActionBarEnd']/span/span/span/span[text()='Continue']")
	WebElement continueButton;
	
	@FindBy(linkText="Rebuild and release")
	WebElement RebuildAndRelease;
	
	@FindBy(xpath="//li[@class='nav-tab ng-scope closable active']/button")
	WebElement closeWindow;
	
	@FindBy(xpath="//select[@name='businessDayId']")
	WebElement clickSelectDropDown;
	
	public void buildAndRelease() throws Exception {
		
		//Open Status operation
		openStateToBuildAndRelease();
		refreshGoButton.click();
		
		driver.switchTo().defaultContent();
		closeWindow.click();
		Thread.sleep(9000);
	}
	//selecting active Business Day in Outbound transmission
	public void selectActiveBusinessDay() throws InterruptedException {
		distribution.click();
		Thread.sleep(1000);
		transmissions.click();
		Thread.sleep(1000);
		driver.switchTo().frame(switchFramework);
		Thread.sleep(1000);	
		
		clickSelectDropDown.click();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		LocalDateTime now = LocalDateTime.now();  

		WebElement activeBussDay = driver.findElement(By.xpath("//option[contains(text(),'"+dtf.format(now)+"')]"));
		activeBussDay.click();
		refreshGoButton.click();
		Thread.sleep(4000);
	}
	
	//Open status actions
	public void openStateToBuildAndRelease() throws InterruptedException {	
		WebElement trCount = driver.findElement(By.xpath("//table/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody"));
		List<WebElement> TotalRowsList = trCount.findElements(By.tagName("tr"));
		
		//Open Status build and release
		int openCount=0;
		for(int i=0; i<TotalRowsList.size(); i++) {
		    
			if(TotalRowsList.get(i).getText().contains("Open")) {
				TotalRowsList.get(i).findElement(By.tagName("input")).click();
				openCount++;
				Thread.sleep(1000);
			}		
		}
		if(openCount>=1) {
			selectionMenu.click();
			buildAndRelease.click();
			buildButton.click();
			Thread.sleep(2000);
			continueButton.click();
			Thread.sleep(5000);
		}
	}
}
