//package ftm.testcases;
//
//import java.util.concurrent.TimeoutException;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.testng.annotations.Test;
//
//import ftm.base.BaseClass;
//
//public class Demo extends BaseClass{
//	
//	/*
//	* Log in to the FTM 4x application using provided credentials.
//	*/
//	@Test (priority = 0)
//	public void loginTest() throws Throwable {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds (10)); // 10 seconds of explicit wait
//		WebElement UserName = wait. until(ExpectedConditions.visibiLity0fELementLocated(By. id("j_username"))) ;
//		UserName. sendKeys ("smoketest");
//		WebElement Password = wait. until (ExpectedConditions.visibiLityOfELementLocated(By.id("j_password")));
//		Password. sendKeys ("smokepass");
//		west cit losin= wait.until(ExpectedConditions.elementToßeClickable(By.cssSelector("input. login-submit");
//		Thread. sLeep (4000) ;
//		WebElement closewindow = driver.findElement(By.xpath("//button[contains(@class, 'ICON_BUTTON') and contains(@class, 'CLOSE')]"));
//		closewindow. click();
//	}
//	 
//	 
//	/*
//	*	Navigate to and open/activate the Business Day(System Configuration › Business Day).
//	*/
//	 
//	@Test(priority = 3)
//	public void navigateToBusinessDay() throws InterruptedException {
//		WebElement systemManagement = driver. findElement (By.xpath("//table/tbody/tr/td[4]")) ;
//		systemManagement.click();
//		Thread. sleep (1000);
//		WebElement businessDay = driver. findElement (By.xpath(*//*[text() = 'Business Days']"));
//		businessDay.click();
//		Thread. sLeep (1000);
//		// Switching the frame to Business day page
//		WebElement switchFrameWork = driver
//		.findElement (By.xpath("//iframe[contains(@id,IFRAME) and @title='Business Days']"));
//		driver.switchTo(). frame(switchFrameWork);
//		Thread. sLeep (3000);
//		// Navigate to the Business Day page and check the current status
//		WebElement status = driver. findElement (By.xpath("//td[@id='state']")) ;
//		String businessDayStatus = status .getText().trim();
//		
//		// If the status is not active, activate it
//		if (!businessDayStatus.equalsIgnoreCase("Active")) {
//			// Click the action button to reveal the activation option
//			WebElement actionButton = driver. findElement (By.xpath("//img[@title= 'Selection Menu']"));
//			actionButton.click();
//			// Wait for the activate button to be clickable and click it
//			WebElement activateButton = new WebDriverWait(driver, Duration.ofSeconds (10))
//			.until(ExpectedConditions.elementToBeClickable(By. LinkText("Activate"))) ;
//			activateButton.click();
//			// Accept the alert which appears after activation
//			try (
//				Alert alert = new WebDriverWait(driver, Duration.ofSeconds (10)).until(ExpectedConditions.alertIsPresent());
//			alert.accept);
//			// Optionally, wait for the status to change to "Active" after handling the
//			// alert
//			new WebDriverWait(driver, Duration.ofSeconds (10)).until(ExpectedConditions. textToBe(By.xpath("//td[@id='state']"), "Active")) ;
//		} catch (TimeoutException e) i
//		// No alert appeared within the timeout period, which means the status was
//		// already active
//		// Refresh the page to update the status, if necessary
//		WebElement refreshButton = driver. findElement (By.xpath("//input [@value= 'Refresh']"));
//		refreshButton. click();
//		// Wait for the status to be updated to "Active"
//		new WebDriverWait(driver, Duration.ofSeconds (10))
//		•until(ExpectedConditions.textToBe(By.xpath("//td[@id='state']"), "Active"));
//		}
//		// Assert the final status to ensure it is "Active"
//		Assert.assertEquals("Active", driver. findElement(By.xpath("//td[@id= 'state']")).getText());
//		driver .switchTo().defaultContent();
//		 
//		// Close the window or navigate away as needed
//		WebElement closeWindowButton = driver
//		.findElement (By. xpath("//button[contains(@class, 'ICON_BUTTON') and contains(@class, 'CLOSE')]"));
//		closeWindowButton. click();
//	
// 
//	/*
//	 * Check Inbound Physical Transmissions for the file status.
//	*/
//	 
//	@Test(priority = 4)
//	public void inboundPhysicalTransmissions) throws InterruptedException {
//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds (10)); // 10 seconds of explicit wait
//	// Navigate to Inbound Physical Transmissions
//	WebElement originatorAndReceipt = driver. findElement (By.xpath("//table/tbody/tr/td[5]")) ;
//	originatorAndReceipt.click();
//	WebElement physicalTransmissions = wait
//	.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text() = 'Physical Transmissions']")));
//	physicalTransmissions.click();
//	// Switching the frame to Inbound Physical Transmissions
//	WebElement switchFrameWork = driver.findElement(By.xpath("//iframe[contains(@id, 'IFRAME') and @title='Inbound Physical Transmissions']"));
//	driver. switchTo().frame(switchFrameWork);
//	// Fetching status
//	WebElement status = driver. findElement (By.xpath("//td[@class='body-copysmall' and @headers='Status']"));
//	String state = status getText();
//	// Switching back to the main content
//	Assert assertTrue(state. equals"Processed")) ;
//	driver. switchTo() .defaultContent();
//	WebElement closeWindow = driver
//	• findElement(By.xpath("//button[contains(@class, 'ICON_BUTTON') and contains(@class, 'CLOSE')]"));
//	closeWindow.click();
//	}
//	 
//	/*
//	Verify in Inbound Transmissions that the file is Accepted.
//	*/
//	@Test(priority = 5)
//	public void inboundTransmissions () throws InterruptedException {
//	driver. manage (). timeouts().implicitlyWait(Duration.ofSeconds(10));
//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds (10));
//	// Navigate to Inbound Transmissions
//	 
//	WebElement originatorAndReceipt = driver. findElement (By xpath("//table/tbody/tr/td[5]")) ;
//	originatorAndReceipt.click();
//	WebElement transmissions = driver. findElement(By xpath("//*[text() = 'Transmissions']"));
//	transmissions.click();
//	// Switching the frame to Inbound Transmissions
//	WebElement switchFrameWork = driver
//	• findElement (By. xpath("//iframe[contains(@id, 'IFRAME') and @title='Inbound Transmissions']"));
//	driver. switchTo(). frame(switchFrameWork);
//	// Introduce a wait before fetching the condition
//	// Fetching Accept condition
//	WebElement conditionElement = driver .findElement
//	By xpath("//div[@class='gridxBody gridxBodyRowHoverEffect']/div[@role='row']/table/tbody/tr/td[9]"));
//	String condition = conditionElement.getText);
//	// Switching back to the main content
//	//Assert. assertTrue(condition.equals"Accepted" )) ;
//	driver. switchTo() .defaultContent();
//	WebElement closeWindow = driver
//	. findElement(By xpath("//button[contains(@class, 'ICON_BUTTON') and contains(@class, 'CLOSE')]"));
//	closeWindow. click();
//	}
//	 
//	/*
//
//	""Processing & Remediation › Duplicate Detect › Manage Duplicates › Units of Work""
//	*/
//	 
//	@Test (priority = 6)
//	public void duplicateDetect) throws InterruptedException {
//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds (10));
//	Actions actions = new Actions (driver);
//	// Hover over "Processing & Remediation"
//	WebElement ProcessingRemediation = wait.until (ExpectedConditions.eLementToBeCLickabLe(By.xpath("//td[@title='Processing & Remediation']")));
//	ProcessingRemediation. click();
//	WebElement duplicateDetect = driver. findElement(By xpath("//td[contains., 'Duplicate Detect' )]")) ;
//	actions moveToElement(duplicateDetect) •perform);
//	// Add a wait for the "Manage Duplicates" option to become visible if necessary
//	// Hover over "Manage Duplicates"
//	WebElement manageDuplicates = driver. findElement(By.xpath("//td[contains., 'Manage Duplicates')]"));
//	actions moveToElement(manageDuplicates) .perform);
//	// Add a wait for the "Units of Work" option to become visible if necessary
//	// Click on "Units of Work"
//	WebElement unitsOfWork = driver. findElement(By xpath("//td[contains., 'Units of Work')]"));
//	actions.moveToElement(unitsOfWork). click() perform);
//	WebElement switchFrameWork = driver. findElement (By.xpath("//iframe[contains@id, IFRAME) and @title='Units of Work']"));
//	driver. switchTo(). frame (switchFrameWork);
//	WebElement Onlyshowactiveunitsofwork = wait.until (ExpectedConditions.eLementToBeCLickable(By xpath("//*[@id=\ "activebox\"]")));
//	Onlyshowactiveunitsofwork.click();
//	WebAlement Donotusestartandendtimes = driver. findElement (By. xpath("//*[@id=\"showallbox\"]"));
//	Donotusestartandendtimes.click();
//	driver. findElement (By.xpath("//input[@type='button' and @value= 'Show unit of work list']")) .click(); driver.switchTo().defaultContent;
//	WebElement closeWindow = driver. findElement (By xpath("//button[contains(@class, 'ICON_BUTTON') and contains(@class, 'CLOSE')]"));
//	closeWindow.click() ;
//	}
//	}
//}
