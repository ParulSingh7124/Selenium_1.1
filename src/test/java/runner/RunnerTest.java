package runner;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.vimalselvam.cucumber.listener.ExtentProperties;

import common.utils.BaseClass;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/FeatureFiles", glue = "", monochrome = true, 
		stepNotifications = true,																								
		plugin = { "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }, tags = "@SmokeRun")

public class RunnerTest extends BaseClass {

	public static String reportpath;

	@BeforeClass
	public static void setup() {
		Date date = Calendar.getInstance().getTime();
		String dateValue = date.toString().replace(" ", "_").replace(":", "_");
		String FolderName = "PMTestExceution" + dateValue;

		ExtentProperties extentProperties = ExtentProperties.INSTANCE;
		reportpath = "target\\ExtentReports\\" + FolderName;
		extentProperties.setReportPath(reportpath + "\\" + "CLMTestExecution.html");

	}

	@AfterClass
	public static void destroy() throws IOException {
		/*
		 * Reporter.loadXMLConfig(new File("ExtentConfig/extentconfig.xml"));
		 * Reporter.setSystemInfo("User Name", "TESTDEVELOPER");
		 * Reporter.setSystemInfo("Application Name", "PM");
		 * Reporter.setSystemInfo("Operating System Type",
		 * System.getProperty("os.name").toString());
		 * Reporter.setTestRunnerOutput("Test Execution Report");
		 */
	}
}
