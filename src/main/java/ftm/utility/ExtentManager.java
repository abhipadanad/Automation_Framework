package ftm.utility;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
/**
 * ExtentManager class is used for Extent Report
 *  
 */

public class ExtentManager {
	
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentSparkReporter spark;
	
	public static void setExtent() throws IOException {
		
		extent = new ExtentReports();
		spark = new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/"+"Automation_Report.html");
		spark.loadXMLConfig(System.getProperty("user.dir")+"/ExtentConfig/extent-config.xml");
		spark.config().setReportName("FTM Test Automation Report");
		extent.attachReporter(spark);
		
	}
	
	public static void endReport() {
		extent.flush();
	}
}
