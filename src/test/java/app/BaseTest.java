package app;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import core.browser.BrowserManager;
import core.browser.DriverHandler;
import core.constant.Path;
import core.util.PropertyFileManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

	private static final Properties application;

	static {
		application = new Properties();
		PropertyFileManager.getInstance().loadProperties(application,
				Path.MAIN_RESOURCES + File.separator + "config" + File.separator + "application.properties");
	}

	@BeforeSuite
	public void beforeSuite() throws IOException, InterruptedException {
		BrowserManager.getInstance().killDriverProcess();
	}

	@BeforeMethod
	public void openBrowser() {
		BrowserManager.getInstance().openBrowser();
		System.out.println(DriverHandler.getDriver() + " " + Thread.currentThread().getId());
		DriverHandler.getDriver().get(application.getProperty("app.url"));
	}

	@AfterMethod(alwaysRun = true)
	public void closeBrowser() {
		BrowserManager.getInstance().closeBrowser();
	}

	@AfterSuite
	public void unload() {
		DriverHandler.unload();
	}
}
