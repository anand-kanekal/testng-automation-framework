package framework.browser;

import framework.constant.Path;
import framework.util.PropertyFileManager;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Objects;
import java.util.Properties;

public class BrowserManager {

	private static BrowserManager browserManager;
	private ChromeOptions chromeOptions;
	private static final Properties browserProperties;

	static {
		browserProperties = new Properties();
		PropertyFileManager.getInstance().loadProperties(browserProperties,
				Path.TEST_RESOURCES + File.separator + "config" + File.separator + "browser.properties");
	}

	private BrowserManager() {}

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
	protected void setChromeOptions() {
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
	protected ChromeOptions getChromeOptions() {
		return chromeOptions;
	}

	/**
	 * Open configured browser
	 * 
	 * @author Anand Kanekal
	 */
	public void openBrowser() {
		String browser = browserProperties.getProperty("browser").toLowerCase().trim();
		
		WebDriverFactory.createDriver(browser);
		DriverHandler.getDriver().manage().deleteAllCookies();
		DriverHandler.getDriver().manage().window().maximize();
	}

	/**
	 * Close browser
	 * 
	 * @author Anand Kanekal
	 */
	public void closeBrowser() {
		DriverHandler.getDriver().close();
	}

	/**
	 * Get driver instance
	 * 
	 * @return driver
	 * @author Anand Kanekal
	 *//*
	public WebDriver getDriver() {
		if (Objects.nonNull(DriverHandler.getDriver())) {
			return DriverHandler.getDriver();
		}

		throw new RuntimeException("Driver is not initialized");
	}*/

	/**
	 * Kill driver process
	 *
	 * @throws IOException
	 * @throws InterruptedException
	 * @author Anand Kanekal
	 */
	public void killDriverProcess() throws IOException, InterruptedException {
		String browser = browserProperties.getProperty("browser").toLowerCase().trim();

		if (SystemUtils.IS_OS_WINDOWS) {
			String command = "taskkill /F /IM %s";

			if (browser.equalsIgnoreCase("chrome")) {
				command = String.format(command, "chromedriver.exe");
				Runtime.getRuntime().exec(command);
				Thread.sleep(3000);
			}
		}
	}
}
