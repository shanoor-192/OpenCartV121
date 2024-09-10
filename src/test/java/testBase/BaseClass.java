package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager; // Log4j
import org.apache.logging.log4j.Logger; // Log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger; // log4j
	public Properties p;

	@BeforeClass(groups= {"Sanity","Regression","Master"/*,"Datadriven"*/})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException {
		
		// loading config file
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		
		
		
		
		logger = LogManager.getLogger(this.getClass());
		
		
		if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			
			DesiredCapabilities cap = new DesiredCapabilities();
			
			//os
			switch (os.toLowerCase()) {
			case "windows" : cap.setPlatform(Platform.WIN11); break;
			case "linux" : cap.setPlatform(Platform.LINUX); break;
			case "mac" : cap.setPlatform(Platform.MAC); break;
			default : System.out.println("No matching OS!"); return;
			}
			
			//browser
			switch (br.toLowerCase()) {
			case "chrome" : cap.setBrowserName("chrome"); break;
			case "edge" : cap.setBrowserName("MicrosoftEdge"); break;
			case "firefox" : cap.setBrowserName("firefox"); break;
			default : System.out.println("No matching Browser!"); return;
			}
			
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
			
			
		}
			
		if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
			
			switch(br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				WebDriverManager.chromedriver().setup();
				break;
			case "edge":
				driver = new EdgeDriver();
				WebDriverManager.edgedriver().setup();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				WebDriverManager.firefoxdriver().setup();
				break;
			default:
				System.out.println("Invalid browser! Use one of the following: edge, chrome, firefox");
				return;
			}
			
		}
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appUrl")); // reading url from prop file
		driver.manage().window().maximize();
		
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master"/*,"Datadriven"*/})
	public void tearDown() {
		
		driver.quit();
		
	}
	
	
	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomNumber() {
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}
	
	public String randomAlphaNumeric() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		String generatedNumber = RandomStringUtils.randomNumeric(3);
		return (generatedString+"@"+generatedNumber);
	}
	
	
	public String captureScreen(String tname) throws IOException {
		
		String timeStamp = new SimpleDateFormat("yyyyMMddHHss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "-" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
		
	}
	
	
}
