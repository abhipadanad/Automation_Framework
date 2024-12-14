/**
 * 
 */
package ftm.pageobjects;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ftm.base.BaseClass;
import ftm.utility.Log;

public class LoginPage extends BaseClass {
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="j_username")
	WebElement username;
	
	@FindBy(id="j_password")
	WebElement password;
	
	@FindBy(id="com.ibm.tenx.ui.UIMessages.LOG_IN")
	WebElement loginButton;
	
    @FindBy(xpath="//div[@class='login-error']")
    WebElement loginError;
    
    @FindBy(className="MENU_TEXT")
	WebElement clickOnUser;
	
	@FindBy(className="MENU_ITEM_TEXT")
	WebElement clickOnSignOff;
	
	//Login to the FTM UI
	public boolean login(String uname, String pswd) throws Throwable {
		Log.startTestCase("loginTest");
		//Entering the username and password
		username.sendKeys(uname);
		password.sendKeys(pswd);

		boolean flag=true;
		Log.info("Verifying if user is able to login");
		loginButton.click();

		try {
			//handling the Exception of Login Error
			loginError.isDisplayed();
			} catch (WebDriverException e) {
			flag=false;
		}	
		if(flag) {
			Log.info("Login is failed!!");
			Log.endTestCase("loginTest");
	        return false;
		}
	     else {
	    	Log.info("Login is Sucess");
	    	Log.endTestCase("loginTest");
	        return true;
	        }
	}
		
}






