package ftm.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import ftm.utility.ExtentManager;
import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * 
 * BaseClass is used to load the config file and Initialize 
 * WebDriver
 *  
 **/
public class BaseClass {
	public static Properties prop;
	public static WebDriver driver;
	
	//loadConfig method is to load the configuration
	@BeforeSuite(groups = {"Smoke","Sanity","Regression"})
	public void loadConfig() throws IOException {
		ExtentManager.setExtent();
		BasicConfigurator.configure();
		DOMConfigurator.configure("log4j.xml");

		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "\\Configuration\\Config.properties");
			prop.load(ip);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void lunchApp() throws IOException {
		String browserName = (prop.getProperty("browser")); 
		String headlessMode = (prop.getProperty("headless"));

		if (browserName.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			if(headlessMode.equals("true"))
				options.addArguments("--headless");
			driver = new ChromeDriver(options);

		} else if (browserName.equalsIgnoreCase("FireFox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			if(headlessMode.equals("true"))
				options.addArguments("--headless");
			driver = new FirefoxDriver(options);

		} else if (browserName.equalsIgnoreCase("Edge")) {
			//WebDriverManager.edgedriver().setup();
			EdgeOptions options = new EdgeOptions();
			options.addArguments("--remote-allow-origins=*");
			if(headlessMode.equals("true"))
				options.addArguments("--headless");
	        driver = new EdgeDriver(options);	
		}
		
		//Maximize the screen
		driver.manage().window().maximize();
		//Delete all the cookies
		driver.manage().deleteAllCookies();
		//Implicit TimeOuts	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.get(prop.getProperty("URL"));
	}

	@AfterSuite(groups = {"Smoke","Sanity","Regression"})
	public void afterSuite() {
		ExtentManager.endReport();
	}
}
