package tests;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import framework.browser.BrowserManager;
import framework.browser.DriverHandler;
import framework.constant.Path;
import framework.util.PropertyFileManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

	private static final Properties application;

	static {
		application = new Properties();
		PropertyFileManager.getInstance().loadProperties(application,
				Path.MAIN_RESOURCES + File.separator + "config" + File.separator + "application.properties");
	}

	@BeforeMethod
	public void setUp() throws IOException, InterruptedException {
		// BrowserManager.getInstance().killDriverProcess();
		BrowserManager.getInstance().openBrowser();
		System.out.println(DriverHandler.getDriver() + " " + Thread.currentThread().getId());
		DriverHandler.getDriver().get(application.getProperty("app.url"));
	}

	@AfterMethod
	public void tearDown() {
		BrowserManager.getInstance().closeBrowser();
	}
}
