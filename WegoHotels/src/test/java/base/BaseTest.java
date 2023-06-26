package base;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

import org.apache.logging.log4j.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public static WebDriver driver;
	public static Properties prop = new Properties();
	public static FileReader fileReader;
	public static ChromeOptions chromeOptions;
	
	public static Logger logger = LogManager.getLogger(BaseTest.class.getName());
	
	@BeforeMethod
	public void setup() throws IOException {
		fileReader = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\Config_Files\\config.properties");
		prop.load(fileReader);
		
		if(prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			chromeOptions = new ChromeOptions();
			chromeOptions.setExperimentalOption("prefs", allowPopup(1));
			driver = new ChromeDriver(chromeOptions);
			driver.manage().window().maximize();	
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(prop.getProperty("url"));
			logger.info("Browser Launched Successfully.");
		}
		else if(prop.getProperty("browser").equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get(prop.getProperty("url"));
			logger.info("Browser Launched Successfully.");

		}
		else if(prop.getProperty("browser").equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			driver.get(prop.getProperty("url"));
			logger.info("Browser Launched Successfully.");

		}
	}
	
	public HashMap<String, Object> allowPopup(int optionValue) {
		HashMap<String, Integer> contentSettings = new HashMap<String, Integer>();
		HashMap<String, Object> profile = new HashMap<String, Object>();
		HashMap<String, Object> prefs = new HashMap<String, Object>();
		contentSettings.put("geolocation", optionValue);
		contentSettings.put("notifications", optionValue);
		profile.put("managed_default_content_settings", contentSettings);
		prefs.put("profile", profile);
		return prefs;
		
	}
	
	@AfterMethod
	public void tearDown() throws InterruptedException {
		driver.quit();
		logger.info("All browsers closed successfully.");
	}
	
}
