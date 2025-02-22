package framework.util;

import framework.browser.DriverHandler;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ActionUtils {

    public static final int SHORT_TIMEOUT = 10;
    public static final int NORMAL_TIMEOUT = 30;
    public static final int LONG_TIMEOUT = 60;

    private ActionUtils() {}

    /**
     * Waits for the visibility of web element
     *
     * @param element
     * @param timeout
     * @return WebElement
     * @author Anand Kanekal
     */
    public static WebElement waitForVisibilityOf(WebElement element, int timeout) {
        return new WebDriverWait(DriverHandler.getDriver(), Duration.ofSeconds(timeout))
                .until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits for the visibility of list of web elements
     *
     * @param elements defines the list of web elements
     * @param timeout
     * @return List<WebElement>
     * @author Anand Kanekal
     */
    public static List<WebElement> waitForVisibilityOfAll(List<WebElement> elements, int timeout) {
        return new WebDriverWait(DriverHandler.getDriver(), Duration.ofSeconds(timeout))
                .until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    /**
     * Waits for the invisibility of web element
     *
     * @param element defines the web element
     * @param timeout
     * @return
     * @author Anand Kanekal
     */
    public static boolean waitForInvisibilityOf(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(DriverHandler.getDriver(), Duration.ofSeconds(timeout));

        return wait.until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * Click on web element
     *
     * @param element defines the web element
     * @author Anand Kanekal
     */
    public static void clickOn(WebElement element) {
        if (element.isEnabled()) {
            element.click();
        }
    }

    /**
     * Simulates mouse click on web element
     *
     * @param element defines the web element
     * @author Anand Kanekal
     */
    public static void mouseClickOn(WebElement element) {
        new Actions(DriverHandler.getDriver()).moveToElement(element).click().build().perform();
    }

    /**
     * Simulates mouse hover on web element
     *
     * @param element defines the web element
     * @author Anand Kanekal
     */
    public static void mouseHoverOn(WebElement element) {
        Actions action = new Actions(DriverHandler.getDriver());

        action.moveToElement(element).build().perform();
    }

    /**
     * Click on web element using js executor
     *
     * @param element defines the web element
     * @author Anand Kanekal
     */
    public static void javaScriptClickOn(WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverHandler.getDriver();
        jsExecutor.executeScript("arguments[0].click();", element);
    }

    public static void typeOn(WebElement element, String text) {
        if (element.isDisplayed() && element.isEnabled()) {
            element.clear();
            element.sendKeys(text);
        }
    }

    /**
     * Scrolls to the web element
     *
     * @param element defines the web element
     * @author Anand Kanekal
     */
    public static void scrollTo(WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverHandler.getDriver();
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Scrolls to the bottom of the page
     *
     * @author Anand Kanekal
     */
    public static void scrollToBottom() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverHandler.getDriver();
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    /**
     * Get a Base64 format of screenshot
     *
     * @return
     * @author Anand Kanekal
     */
    public static String getBase64Screenshot() {
        TakesScreenshot takesScreenshot = (TakesScreenshot) DriverHandler.getDriver();
        return takesScreenshot.getScreenshotAs(OutputType.BASE64);
    }
}
