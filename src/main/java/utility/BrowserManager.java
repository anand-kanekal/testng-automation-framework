package utility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Objects;
import java.util.Properties;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import constants.Path;
import handler.DriverHandler;

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
		if (Objects.isNull(browserManager)) {
			browserManager = new BrowserManager();
		}

		return browserManager;
	}

	/**
	 * Set Chrome options
	 * 
	 * @author Anand Kanekal
	 */
	private void setChromeOptions() {
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
	 * @author Anand Kanekal
	 */
	private ChromeOptions getChromeOptions() {
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

		DriverHandler.setDriver(driver);
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
		if (Objects.nonNull(DriverHandler.getDriver())) {
			return DriverHandler.getDriver();
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
	public void killDriverProcess() throws IOException, InterruptedException {
		String browser = browserProperties.getProperty("browser").toLowerCase().trim();

		String command = "taskkill /F /IM %s";

		if (browser.equalsIgnoreCase("chrome")) {
			command = String.format(command, "chromedriver.exe");
			Runtime.getRuntime().exec(command);
			Thread.sleep(3000);
		}
	}
}
