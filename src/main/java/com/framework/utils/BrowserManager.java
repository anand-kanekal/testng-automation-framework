package com.framework.utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.framework.contants.Path;

public class BrowserManager {

	private static BrowserManager browserManager;

	private WebDriver driver;
	private ChromeOptions chromeOptions;
	private static final Properties browserProperties;

	static {
		browserProperties = new Properties();
		PropertyFileManager.getInstance().loadProperties(browserProperties,
				Path.TEST_RESOURCES + File.separator + "config" + File.separator + "browser.properties");
	}

	private BrowserManager() {

	}

	/**
	 * Get browserManager instance
	 * 
	 * @return browserManager
	 * @author Anand Kanekal
	 */
	public static BrowserManager getInstance() {
		if (browserManager == null) {
			browserManager = new BrowserManager();
		}

		return browserManager;
	}

	/**
	 * Set Chrome options
	 * 
	 * @author Anand Kanekal
	 */
	public void setChromeOptions() {
		String headless = browserProperties.getProperty("headless").toLowerCase().trim();
		String incognito = browserProperties.getProperty("incognito").toLowerCase().trim();
		String acceptInsecureCerts = browserProperties.getProperty("accept.insecure.certs").toLowerCase().trim();
		String pageLoadTimeout = browserProperties.getProperty("pageload.timeout").trim();
		String scriptTimeout = browserProperties.getProperty("script.timeout").trim();

		chromeOptions = new ChromeOptions();

		if (Boolean.parseBoolean(headless)) {
			chromeOptions.addArguments("--headless=chrome");
		}

		if (Boolean.parseBoolean(incognito)) {
			chromeOptions.addArguments("incognito");
		}

		chromeOptions.setAcceptInsecureCerts(Boolean.parseBoolean(acceptInsecureCerts));
		chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		chromeOptions.setPageLoadTimeout(Duration.ofSeconds(Integer.parseInt(pageLoadTimeout)));
		chromeOptions.setScriptTimeout(Duration.ofSeconds(Integer.parseInt(scriptTimeout)));
		chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
	}

	/**
	 * Get Chrome options
	 * 
	 * @return chromeOptions
	 */
	public ChromeOptions getChromeOptions() {
		return chromeOptions;
	}

	/**
	 * Open configured browser
	 * 
	 * @author Anand Kanekal
	 */
	public void openBrowser() {
		String browser = browserProperties.getProperty("browser").toLowerCase().trim();

		switch (browser) {
		case "chrome":
			setChromeOptions();
			driver = new ChromeDriver(getChromeOptions());
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;

		default:
			System.out.println("Invalid browser name specified in browser.properties present at location "
					+ Path.TEST_RESOURCES + File.separator + "config");
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}

	/**
	 * Close browser
	 * 
	 * @author Anand Kanekal
	 */
	public void closeBrowser() {
		getDriver().quit();
	}

	/**
	 * Get driver instance
	 * 
	 * @return driver
	 * @author Anand Kanekal
	 */
	public WebDriver getDriver() {
		if (driver != null) {
			return driver;
		}

		throw new RuntimeException("Driver is not initialized");
	}

	/**
	 * Kill driver process
	 * 
	 * @param processName
	 * @throws IOException
	 * @throws InterruptedException
	 * @author Anand Kanekal
	 */
	public static void killDriverProcess(String processName) throws IOException, InterruptedException {
		String command = String.format("taskkill /F /IM %s", processName);

		Runtime.getRuntime().exec(command);
		Thread.sleep(3000);
	}
}
