package PSL.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import PSL.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingpage;
	

	public WebDriver initializeDriver() throws IOException {

		// properties class

		Properties prop = new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//PSL//Resources//GlobalData.properties");
		prop.load(fis);

		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser"); // java ternary operator.
		

		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if (browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			// driver.manage().window().setSize(new Dimension(1440,900)); // full screen
			driver.manage().window().setSize(new Dimension(800, 600));

		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			// Firefox
		} else if (browserName.equalsIgnoreCase("edge")) {
			// Edge
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
	
		return driver;

	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {

		driver = initializeDriver();
		landingpage = new LandingPage(driver);
		landingpage.goTo();
		
		return landingpage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}

	

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

	// Extent Reports -

}
