package framework.browser;

import framework.constant.Path;
import framework.util.PropertyFileManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.util.Properties;

public class WebDriverFactory {

    private static final Properties browserProperties;

    static {
        browserProperties = new Properties();
        PropertyFileManager.getInstance().loadProperties(browserProperties,
                Path.TEST_RESOURCES + File.separator + "config" + File.separator + "browser.properties");
    }

    private WebDriverFactory() {}

    public static WebDriver createDriver(String browserName) {
        WebDriver driver;

        switch (browserName.toLowerCase()) {
            case "chrome":
                BrowserManager.getInstance().setChromeOptions();
                driver = new ChromeDriver(BrowserManager.getInstance().getChromeOptions());
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;

            default:
                throw new RuntimeException("Invalid browser name specified in browser.properties present at location "
                        + Path.TEST_RESOURCES + File.separator + "config");
        }

        DriverHandler.setDriver(driver);

        return driver;
    }
}
