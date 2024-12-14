package ftm.utility;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ftm.base.BaseClass;

public class WaitClass extends BaseClass{
	WebDriver driver;

	public WaitClass() {
		PageFactory.initElements(driver, this);
	}

	private static void until(WebDriver webDriver, Duration timeOutInSeconds, Function<WebDriver, Boolean> waitCondition) {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, timeOutInSeconds);
        try {
            webDriverWait.until(waitCondition);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
	
    public static void untilElementIsVisible(WebDriver webDriver, WebElement webElement, Duration timeOutInSeconds) {
        new WebDriverWait(webDriver, timeOutInSeconds).until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void untilListElementIsVisible(WebDriver webDriver, List<WebElement> webElements, Duration timeOutInSeconds) {
        new WebDriverWait(webDriver, timeOutInSeconds).until(ExpectedConditions.visibilityOfAllElements(webElements));
    }
    
    public void waitForSomeTime(int time) {
    	try {
    		Thread.sleep(time);
    	}catch(InterruptedException e) {
    		e.printStackTrace();
    	}
    }
}
