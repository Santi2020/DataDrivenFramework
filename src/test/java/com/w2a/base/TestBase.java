package com.w2a.base;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
//import org.testng.log4testng.Logger;

public class TestBase {
	public static WebDriver driver;
	
	public static Properties config = new Properties();
	public static Properties orProp= new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	
	
	@BeforeSuite
	public void setUp() throws IOException {
		if (driver==null) {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
			config.load(fis);
			log.debug("Config file loaded");
			System.out.println(config.getProperty("browser"));
			
			fis = new FileInputStream(System.getProperty("user.dir") +"\\src\\test\\resources\\properties\\OR.properties");
			orProp.load(fis);
			log.info("OR file loaded");
			System.out.println(orProp.getProperty("bmlBtn"));
		}
		
		if(config.getProperty("browser").equals("firefox")) {
			System.setProperty("webdriver.gecko.driver","C://Drivers//geckodriver-v0.30.0-win64/geckodriver.exe");
			driver= new FirefoxDriver();
		}else if(config.getProperty("browser").equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C://Drivers//chromedriver_win32/chromedriver.exe");
			driver= new ChromeDriver();
		}else if(config.getProperty("browser").equals("edge")) {
			System.setProperty("webdriver.chrome.driver", "C://Drivers//edgedriver_win64/msedgedriver.exe");
			driver= new EdgeDriver();
		}
		
		driver.get(config.getProperty("testsiteurl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt( config.getProperty("implicit.wait")), TimeUnit.SECONDS);
	}
	
	@AfterSuite
	public void tearDown() {
		if (driver!=null) {
			driver.quit();
		}
	}
}
