package ftm.testcases;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ftm.base.BaseClass;
import ftm.dataprovider.DataProviders;
import ftm.utility.Log;

public class E2E_TestCases_Scenario extends BaseClass{
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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds (10)); // 10 seconds of explicit wait
		WebElement UserName = wait. until(ExpectedConditions.visibilityOfElementLocated(By. id("j_username"))) ;
		UserName. sendKeys ("fxhadmin");
		WebElement Password = wait. until (ExpectedConditions.visibilityOfElementLocated(By.id("j_password")));
		Password. sendKeys ("fxhpass");
		WebElement login= wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input.login-submit")));		
		login.click();
		Thread.sleep(4000) ;
		//WebElement closewindow = driver.findElement(By.xpath("//button[contains(@class, 'ICON_BUTTON') and contains(@class, 'CLOSE')]"));
		//closewindow. click();	  
		//WebElement closeWindow = driver.findElement(By.xpath("//li[@class='nav-tab ng-scope active closable']/button/span/.."));
		//closeWindow.click();
	}
	
	/**
	 *  Navigate to and open/activate the Business Day(System Configuration > Business Day).
	 */
	@Test(priority=1)
	public void navigateToBusinessDay() throws InterruptedException {		
		//Navigate to Business Day
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
		WebElement status =driver.findElement(By.xpath("//td[@id='state']"));
		String busdayStatus = status.getText();
		if(!busdayStatus.equalsIgnoreCase("Active")) {
			//clicking the twisty icon
			WebElement actionButton = driver.findElement(By.xpath("//img[@title = 'Selection Menu']"));
			actionButton.click();
			Thread.sleep(1000);
			//selecting Activate
			WebElement activateButton = driver.findElement(By.linkText("Activate"));
			activateButton.click();		
			//Accepting 'OK' alert
			Alert alert = new WebDriverWait(driver, Duration.ofSeconds (10)).until(ExpectedConditions.alertIsPresent());
			alert.accept();
			Thread.sleep(5000);
			refresh = driver.findElement(By.xpath("//input[@value='Refresh']"));
			refresh.click();
		}
		Thread.sleep(2000);
		Log.info("Business Day Activated");
		//fetching Active status on Business Day
		status =driver.findElement(By.xpath("//td[@id='state']"));
		busdayStatus = status.getText();
		Assert.assertTrue(busdayStatus.equals("Active"));
		driver.switchTo().defaultContent();
		WebElement closeWindow = driver.findElement(By.xpath("//li[@class='nav-tab ng-scope closable active']/button"));
		closeWindow.click();
	}
	
	/**
	 * Go to Inbound Physical Transmissions for the file status(Originations & Receipts > Physical).
	 */	
	@Test(priority=2)
	public void inboundPhysicalTransmissions() {	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds (10)); // 10 seconds of explicit wait
		// Navigate to Inbound Physical Transmissions
		WebElement originatorAndReceipt = driver.findElement(By.xpath("//table/tbody/tr/td[5]"));
		originatorAndReceipt.click();
		WebElement physicalTransmissions = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text() = 'Physical Transmissions']")));
		physicalTransmissions.click();
		//Switching the frame to Inbound Physical Transmissions
		WebElement switchFrameWork = driver.findElement(By.xpath("//iframe[contains(@id,IFRAME) and @title='Inbound Physical Transmissions']"));
		driver.switchTo().frame(switchFrameWork);
		
		WebElement receivedTime = driver.findElement(By.xpath("//th[@id='Received Time']"));
		receivedTime.click();
		// Fetching status
		WebElement status = driver.findElement(By.xpath("//tr[5]/td[@class='body-copysmall' and @headers='Status']"));
		String state = status.getText();

		Assert.assertTrue(state.equals("Processed"));
		driver.switchTo().defaultContent();
		WebElement closeWindow = driver.findElement(By.xpath("//li[@class='nav-tab ng-scope closable active']/button"));
		closeWindow.click();
	}
	
	/**
	 * Verify in Inbound Transmissions that the file is Accepted.
	 */	
	@Test(priority=3)
	public void inboundTransmissions() throws InterruptedException {	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds (10)); // 10 seconds of explicit wait
		//Navigate to Inbound Transmissions
		WebElement originatorAndReceipt = driver.findElement(By.xpath("//table/tbody/tr/td[5]"));
		originatorAndReceipt.click();
		WebElement transmissions = driver.findElement(By.xpath("//*[text() = 'Transmissions']"));
		transmissions.click();
		//Switching the frame to Inbound Physical Transmissions
		WebElement switchFrameWork = driver.findElement(By.xpath("//iframe[contains(@id,IFRAME) and @title='Inbound Transmissions']"));
		driver.switchTo().frame(switchFrameWork);
		
		//clicking on sort by received time.
		WebElement sortByReceived = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='gridxSortNode gridxSortUp']/div[1]")));
		sortByReceived.click();		
		
		String condition="";
		// Taking column count from the UI
		WebElement tdCount = driver.findElement(By.xpath("//div[@class='gridxBody gridxBodyRowHoverEffect']/div[@role='row'][1]/table/tbody/tr[1]"));
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

			element2.click();
			new Actions(driver)
            .keyDown(Keys.ARROW_RIGHT)
            .perform();

			if(ele1.equals("Condition") && ele2.equals("Accepted")) {
				condition=ele2;
				break;
			}
		}

		Assert.assertTrue(condition.equals("Accepted"));
		driver.switchTo().defaultContent();
		WebElement closeWindow = driver.findElement(By.xpath("//li[@class='nav-tab ng-scope closable active']/button"));
		closeWindow.click();
	}
	
	/**
	 * Go to "Processing & Remediation > Duplicate Detect > Manage Duplicates > Units of Work"
	 * Select the 2 check boxes "Only show active units of work	 & Do not use start and end times" button.
	 */
	@Test(priority=4)
	public void manageDuplicateDetect() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds (10)); // 10 seconds of explicit wait
		//Navigate to Units of Work
		WebElement processingRemediation = driver.findElement(By.xpath("//td[@title='Processing & Remediation']"));
		processingRemediation.click();
		WebElement duplicateDetect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text() = 'Duplicate Detect']/..")));
		duplicateDetect.click();
		WebElement manageDuplicates = driver.findElement(By.xpath("//*[text() = 'Manage Duplicates']/.."));
		manageDuplicates.click();
		WebElement unitsOfWork = driver.findElement(By.xpath("//*[text() = 'Units of Work']/.."));
		unitsOfWork.click();
		Thread.sleep(4000);
		//Switching iframe
		WebElement switchFramework = driver.findElement(By.xpath("//iframe[contains(@id,IFRAME) and @title='Units of Work']"));
		driver.switchTo().frame(switchFramework);

		WebElement activeUunitsOfWorkCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='activebox']")));
		activeUunitsOfWorkCheckbox.click();
		WebElement startAndEndTimesCheckbox = driver.findElement(By.xpath("//input[@id='showallbox']"));
		startAndEndTimesCheckbox.click();
		WebElement showUnitOfWorkListButton = driver.findElement(By.xpath("//input[@value='Show unit of work list']"));
		showUnitOfWorkListButton.click();
		
		WebElement tdCount = driver.findElement(By.xpath("//table[@class='white']/tbody"));
		List<WebElement> TotalColumnList = tdCount.findElements(By.tagName("tr"));
		
		
		System.out.println(TotalColumnList.size());
		int rows = (TotalColumnList.size()-4)/2;
		
		WebElement allNotDuplicatesButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='white']/tbody/tr["+((rows+3)+(rows-1))+"]/td/a/img[@title='All not Duplicates']")));
		
		//WebElement allNotDuplicatesButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a/img[@title='All not Duplicates']")));
		allNotDuplicatesButton.click();
		Thread.sleep(1000);
		//Accepting 'OK' alert
		Alert alert = new WebDriverWait(driver, Duration.ofSeconds (10)).until(ExpectedConditions.alertIsPresent());
		alert.accept();
		Thread.sleep(4000);
		driver.switchTo().defaultContent();
		WebElement closeWindow = driver.findElement(By.xpath("//li[@class='nav-tab ng-scope closable active']/button"));
		closeWindow.click();
	}
	
	/**
	 * Go to "Origination & Receipt > Batch/ICL > Inbound" and verify the "Balanced"
	 */
	@Test(priority=5)
	public void verifyTheBalancedStatus() throws InterruptedException {		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds (10)); // 10 seconds of explicit wait
		//Navigate to Inbound Batch/ICLs page
		WebElement originatorAndReceipt = driver.findElement(By.xpath("//table/tbody/tr/td[5]"));
		originatorAndReceipt.click();
		WebElement batchICL = driver.findElement(By.xpath("//div[text()='Batches / ICLs']"));
		batchICL.click();
		WebElement inbound = driver.findElement(By.xpath("//td/div[text()='Inbound']/.."));
		inbound.click();
		Thread.sleep(4000);
		//switching iframe
		WebElement switchFramework = driver.findElement(By.xpath("//iframe[contains(@id,IFRAME) and @title='Inbound Batches / ICLs']"));
		driver.switchTo().frame(switchFramework);

		//clicking on sort by received time.
		WebElement sortByReceived = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='gridxSortNode gridxSortUp']/div[1]")));
		sortByReceived.click();	
		
		boolean state =false;

		// Taking column count from the UI
		WebElement tdCount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='gridxBody gridxBodyRowHoverEffect']/div[@role='row'][1]/table/tbody/tr[1]")));
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

			element2.click();
			new Actions(driver)
            .keyDown(Keys.ARROW_RIGHT)
            .perform();

			if(ele1.equals("Balanced")) {
				if(ele2.equals("Complete"))
					state = true;
				break;
			}
		}

		Assert.assertTrue(state);
		driver.switchTo().defaultContent();
		WebElement closeWindow = driver.findElement(By.xpath("//li[@class='nav-tab ng-scope closable active']/button"));
		closeWindow.click();	
	}
	
	/**
	 * Go to "Distribution > Transmissions" Outbound Transmissions screen will apper, From the start field,
	 * select the active Business Day that the file was.
	 */
	@Test(priority=6)
	public void outBoundTransmissionAndSelectBussDay() throws InterruptedException {
		WebElement distribution = driver.findElement(By.xpath("//table/tbody/tr/td[7]"));
		distribution.click();
		WebElement transmissions = driver.findElement(By.xpath("//div[@class='MENU_ITEM_TEXT' and text()='Transmissions']"));
		transmissions.click();
		Thread.sleep(1000);
		WebElement switchFramework = driver.findElement(By.xpath("//iframe[contains(@id,IFRAME) and @title='Outbound Transmissions']"));
		driver.switchTo().frame(switchFramework);
		Thread.sleep(1000);	
		WebElement clickSelectDropDown = driver.findElement(By.xpath("//select[@name='businessDayId']"));
		clickSelectDropDown.click();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		LocalDateTime now = LocalDateTime.now();  

		WebElement activeBussDay = driver.findElement(By.xpath("//option[contains(text(),'"+dtf.format(now)+"')]"));
		activeBussDay.click();
		WebElement refreshGoButton = driver.findElement(By.xpath("//td/input[@id='gobutton' and @type='button']"));
		refreshGoButton.click();
		Thread.sleep(4000);
	}
	
	/**
	 * Select all items on the Distribution and click the twisty icon 'Build and Release' from the dropdown list
	 * The 'Build X9 Confirmation' screen will list few options, select the Radio button to ignore missing
	 * images, and click the 'Build' button.
	 */
	@Test(priority=7)
	public void outBoundTransmissionBuildAndRelease() throws Exception {
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
			WebElement selectionMenu = driver.findElement(By.xpath("//a/img[@title='Selection Menu']"));
			selectionMenu.click();
			WebElement buildAndRelease = driver.findElement(By.linkText("Build and release"));
			buildAndRelease.click();
			WebElement buildButton = driver.findElement(By.xpath("//input[@type='button' and @value='Build']"));
			buildButton.click();
			Thread.sleep(2000);
			WebElement continueButton = driver.findElement(By.xpath("//div[@class='dijitDialogPaneActionBar']/span[@class='idxDialogActionBarEnd']/span/span/span/span[text()='Continue']"));
			continueButton.click();
			Thread.sleep(5000);
		}
		WebElement refreshGoButton = driver.findElement(By.xpath("//td/input[@id='gobutton' and @type='button']"));
		refreshGoButton.click();
		
		driver.switchTo().defaultContent();
		WebElement closeWindow = driver.findElement(By.xpath("//li[@class='nav-tab ng-scope closable active']/button"));
		closeWindow.click();
	}
	
	@Test(priority=8)
	public void batchICLsProcessingPageToVerifySpecificFields() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds (10)); // 10 seconds of explicit wait
		WebElement originatorAndReceipt =driver.findElement(By.xpath("//table/tbody/tr/td[5]"));
		originatorAndReceipt.click();
		Thread.sleep(1000);
		WebElement batchICL = driver.findElement(By.xpath("//div[text()='Batches / ICLs']"));
		batchICL.click();
		Thread.sleep(1000);
		WebElement processing = driver.findElement(By.xpath("//td/div[text()='Processing']/.."));
		processing.click();
		Thread.sleep(4000);
		WebElement switchFramework = driver.findElement(By.xpath("//iframe[contains(@id,IFRAME) and @title='Processing Batches / ICLs']"));
		driver.switchTo().frame(switchFramework);
		Thread.sleep(4000);
		
		//clicking on sort by.
		WebElement sortBy = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='gridxSortNode gridxSortUp']/div[1]")));
		sortBy.click();
		
		LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
		
		// Taking column count from the UI
		WebElement tdCount = driver.findElement(By.xpath("//div[@class='gridxBody gridxBodyRowHoverEffect']/div[@role='row'][1]/table/tbody/tr[1]"));
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

			element2.click();
			new Actions(driver)
            .keyDown(Keys.ARROW_RIGHT)
            .perform();
			    		    
			hashMap.put(ele1, ele2);
			System.out.println("Key=" + ele1 + ", Value=" + ele2);
		}
		
		for (Map.Entry<String, String> entry : hashMap.entrySet()) {
	        String key = entry.getKey();
	        String value = entry.getValue();

	        if((key.equals("Adjusted"))||
	        	(key.equals("Balanced"))||
	        	(key.equals("Captured"))||
	        	(key.equals("Created"))||
	        	(key.equals("Duplicate Detected"))||
	        	(key.equals("Duplicate Reviewed"))||
	        	(key.equals("Image Quality Analysis"))||
	        	(key.equals("Keyed"))||
	        	(key.equals("Loaded"))||
	        	(key.equals("Repaired"))||
	        	(key.equals("Reviewed"))
    		) {
	        	Assert.assertTrue(value.equals("Complete"),key);
	        }
		}
		
		Thread.sleep(5000);
		driver.switchTo().defaultContent();
		WebElement closeWindow = driver.findElement(By.xpath("//li[@class='nav-tab ng-scope closable active']/button"));
		closeWindow.click();
	}
}
