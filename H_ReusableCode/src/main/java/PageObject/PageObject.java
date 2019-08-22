package PageObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Enums.BrowserType;

public class PageObject {

	protected static WebDriver driver;

	public static WebDriver getBrowser() throws IOException {
		if (driver==null || driver!=null) {
			driver = getDriver(getPropertyValue("BrowserType"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		} else {
			System.out.println("scdcddcdf");
		}
		return driver;
	}

	public static WebDriver getDriver(String Browsertype) {

		switch (Browsertype.toUpperCase()) {
		case "CHROME_DRIVER":
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Drivers//chromedriver.exe");
			driver = new ChromeDriver();
			return driver;
		case "FIREFOX_DRIVER":
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"//Drivers//firefoxdriver.exe");
			driver = new FirefoxDriver();
			return driver;
		case "INTERNET_EXPLORER_DRIVER":
			System.out.println("Internet Explorer");
			break;
		case "HTMLUNIT_DRIVER":
			System.out.println("HTMLUNIT_DRIVER");
			break;

		default:
			break;
		}

//		if(Browsertype.equalsIgnoreCase(BrowserType.CHROME_DRIVER.name())) {
//			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Drivers//chromedriver.exe");
//			driver = new ChromeDriver();
//			return driver;
//		} else if (Browsertype.equalsIgnoreCase(BrowserType.FIREFOX_DRIVER.name())) {
//			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"//Drivers//firefoxdriver.exe");
//			driver = new FirefoxDriver();
//			return driver;
//		} else {
//
//		}
		return null;
	}

	public static String getPropertyValue(String value) throws IOException {
		FileInputStream fileInput = new FileInputStream(System.getProperty("user.dir")+"\\Properties\\config.properties");
		Properties prop = new Properties();
		prop.load(fileInput);
		return prop.getProperty(value);
	}

}
