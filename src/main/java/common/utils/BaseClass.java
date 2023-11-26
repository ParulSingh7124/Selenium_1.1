package common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
/*import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;*/

import com.aventstack.extentreports.gherkin.model.Scenario;
//import com.mongodb.diagnostics.logging.Logger;

public class BaseClass {
	public static File destinationPath;
	public static WebDriver driver;
	public static String dirPath = System.getProperty("user.dir");

	public static Properties config = new Properties();
//	protected static Logger log = (Logger) LoggerFactory.getLogger(BaseClass.class);
	public static int count = 1;
	Scenario scenario;

	public BaseClass() {

	}

	public BaseClass(WebDriver driver) {
		this.driver = driver;
	}

	Actions act;

	public void launchBrowser(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {

			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_setting_values.notifications", 2);
			chromePrefs.put("profile.default_content_setting.popups", 0);
			chromePrefs.put("download.prompt_for_download", false);

			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePrefs);

			// used to handle failed to load extension error
			options.setExperimentalOption("useAutomationExtension", false);
			options.addArguments("--disable-notifications");

			// DesiredCapabilities cap = DesiredCapabilities.chrome();

			System.setProperty("webdriver.chrome.driver", "Drivers\\chromedriver.exe");
			driver = new ChromeDriver(options);
			driver.manage().deleteAllCookies();

		}
		/*
		 * if(browser.equalsIgnoreCase("headless")) {
		 * 
		 * }
		 */
	}

	public void loadConfig(String path) throws IOException {
		FileInputStream fls = new FileInputStream(path);
		config = new Properties();
		config.load(fls);

	}

	public void saveConfig(String path) throws IOException {
		FileOutputStream fos = new FileOutputStream(path);
		config.store(fos, "");
		fos.close();

	}

	public void initURL(String URL) throws InterruptedException {
		driver.get(URL);
		Thread.sleep(1000);
		/*
		 * WebDriverWait wait = new WebDriverWait(driver,30);
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
		 * "//div[contains(text(),'COMPOSE')]")));
		 */
		// driver.manage().timeouts().implicitlyWait(10, null);
		driver.manage().window().maximize();

	}

	public String getDayOfTheYear() {
		Calendar calendar = Calendar.getInstance();
		int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
		String dayOfTheYear = Integer.toString(dayOfYear);
		return dayOfTheYear;

	}

	public void writeData(String key, String value, String path) throws IOException {
		loadConfig(path);
		config.setProperty(key, value);
		try {
			saveConfig(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getTestData(String field, String path) {
		try {
			loadConfig(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String value = config.getProperty(field).toString();
		return value;
	}

	public String getDateTime(String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		LocalDateTime dateTime = LocalDateTime.now();
		String currentDateTime = formatter.format(dateTime);
		return currentDateTime;

	}
	/*
	 * WebDriver driver = null;
	 * 
	 * // TODO Auto-generated method stub
	 * 
	 * @BeforeTest public void setup1() {
	 * 
	 * String projectPath = System.getProperty("user.dir");
	 * System.out.println(projectPath); // ---:Deprecated:---
	 * System.setProperty("webdriver.chrome.driver", projectPath +
	 * "/drivers/chromedriver.exe");
	 * 
	 * // WebDriverManager.chromedriver().setup(); driver = new ChromeDriver();
	 * driver.manage().window().maximize();
	 * driver.get("https://login.salesforce.com"); }
	 * 
	 * @Test public void googleSearch() {
	 * 
	 * }
	 * 
	 * @AfterTest public void tearDown() {
	 * 
	 * driver.quit(); }
	 */
}
