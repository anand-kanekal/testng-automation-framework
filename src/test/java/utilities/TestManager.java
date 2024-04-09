package utilities;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import constants.Path;

public class TestManager {
	
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
		BrowserManager.getInstance().getDriver().get(application.getProperty("app.url"));
	}
	
	@AfterMethod
	public void tearDown() {
		BrowserManager.getInstance().closeBrowser();
	}
}
