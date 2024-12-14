package ftm.pageobjects;

import java.awt.RenderingHints.Key;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ftm.base.BaseClass;

public class BatchICLsPage extends BaseClass{
	
	public BatchICLsPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//table/tbody/tr/td[5]")
	WebElement originatorAndReceipt;
	
	@FindBy(xpath="//div[text()='Batches / ICLs']")
	WebElement batchICL;
	
	@FindBy(xpath="//td/div[text()='Processing']/..")
	WebElement processing;
	
	@FindBy(xpath="//iframe[contains(@id,IFRAME) and @title='Processing Batches / ICLs']")
	WebElement switchFramework;
	
	@FindBy(xpath="//td[@role='gridcell' and @colid='p.condition']")
	WebElement batchICLconditionSts;
	
	@FindBy(xpath="//span[@class='dijitReset dijitInline dijitIcon toolbarButtonRefresh']")
	WebElement batchICLrefresh;
	
	@FindBy(xpath="//span[@class='dijitReset dijitInline dijitButtonText' and text()='More']")
	WebElement moreButton;
	
	@FindBy(xpath="//td[text()='Preference']")
	WebElement preference;
	
	@FindBy(xpath="//td[text()='Show/Hide Columns']")
	WebElement showHideColumns;
	
	@FindBy(xpath="//div[@class='gridx gridxAlternatingRows gridxDesktop']/div/div/div[@class='gridxRowHeaderHeader']")
	WebElement checkBox;
	
	@FindBy(xpath="//span[text()='Apply']")
	WebElement applyButton;
	
	@FindBy(xpath="//li[@class='nav-tab ng-scope closable active']/button")
	WebElement closeWindow;
	
	public Map verifyingBatchICLsFields() throws Exception {
		originatorAndReceipt.click();
		Thread.sleep(1000);
		batchICL.click();
		Thread.sleep(1000);
		processing.click();
		Thread.sleep(4000);
		driver.switchTo().frame(switchFramework);
		Thread.sleep(4000);
		//appling all filter to show hide columns
		moreButton.click();
		preference.click();
		showHideColumns.click();
		Thread.sleep(1000);
		checkBox.click();
		applyButton.click();
		Thread.sleep(2000);
		Map<String, String> hashMap = batchICLstatus();
		Thread.sleep(5000);
		driver.switchTo().defaultContent();
		closeWindow.click();
		return hashMap;
	}
	
	public Map batchICLstatus() {
		LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
		int count=15;
		// Taking row count from the UI
		WebElement trCount = driver.findElement(By.xpath("//div[@class='gridxBody gridxBodyRowHoverEffect']/div[@role='row']/table/tbody"));
		List<WebElement> TotalRowsList = trCount.findElements(By.tagName("tr"));

		for(int j=1; j<=TotalRowsList.size(); j++) {
			// Taking column count from the UI
			WebElement tdCount = driver.findElement(By.xpath("//div[@class='gridxBody gridxBodyRowHoverEffect']/div[@role='row']/table/tbody/tr["+j+"]"));
			List<WebElement> TotalColumnList = tdCount.findElements(By.tagName("td"));

			for(int i=1; i<=TotalColumnList.size(); i++) {
				// header Text from the UI
				String headerXpath = "//div[@class='gridxHeaderRow']/div[@class='gridxHeaderRowInner' and @role='row']/table/tbody/tr/td["+i+"]/div";		
				WebElement element1 = driver.findElement(By.xpath(headerXpath));
				String ele1 = element1.getText();
				
				// Checking Status from the UI
				String xpath = "//div[@class='gridxBody gridxBodyRowHoverEffect']/div[@role='row']/table/tbody/tr/td["+i+"]";
				WebElement element2 = driver.findElement(By.xpath(xpath));
				String ele2 = element2.getText();
				
				if (!element2.findElements(By.cssSelector("img[src*='state_complete.png']")).isEmpty()) {
					ele2 = "Complete";
				}
	
				if(count<i) {
					element2.click();
					new Actions(driver)
	                .keyDown(Keys.ARROW_RIGHT)
	                .perform();
				}
				    		    
				hashMap.put(ele1, ele2);
				//System.out.println("Key=" + ele1 + ", Value=" + ele2);
			}
		}
		return hashMap;
	}
}
