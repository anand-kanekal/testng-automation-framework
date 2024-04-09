package utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import constants.Path;

public class CommonUtils {

	public static final int SHORT_TIMEOUT = 10;
	public static final int NORMAL_TIMEOUT = 30;
	public static final int LONG_TIMEOUT = 60;

	/**
	 * Waits for the visibility of web element
	 * 
	 * @param element
	 * @param timeout
	 * @return WebElement
	 * @author Anand Kanekal
	 */
	public static WebElement waitForVisibilityOf(WebElement element, int timeout) {
		return new WebDriverWait(BrowserManager.getInstance().getDriver(), Duration.ofSeconds(timeout))
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
		return new WebDriverWait(BrowserManager.getInstance().getDriver(), Duration.ofSeconds(timeout))
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
		WebDriverWait wait = new WebDriverWait(BrowserManager.getInstance().getDriver(), Duration.ofSeconds(timeout));

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
		Actions action = new Actions(BrowserManager.getInstance().getDriver());

		action.moveToElement(element).click().build().perform();
	}

	/**
	 * Simulates mouse hover on web element
	 * 
	 * @param element defines the web element
	 * @author Anand Kanekal
	 */
	public static void mouseHoverOn(WebElement element) {
		Actions action = new Actions(BrowserManager.getInstance().getDriver());

		action.moveToElement(element).build().perform();
	}

	/**
	 * Click on web element using js executor
	 * 
	 * @param element defines the web element
	 * @author Anand Kanekal
	 */
	public static void javaScriptClickOn(WebElement element) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) BrowserManager.getInstance().getDriver();
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
		JavascriptExecutor jsExecutor = (JavascriptExecutor) BrowserManager.getInstance().getDriver();
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/**
	 * Scrolls to the bottom of the page
	 * 
	 * @author Anand Kanekal
	 */
	public static void scrollToBottom() {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) BrowserManager.getInstance().getDriver();
		jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public static String getBase64Screenshot() throws Exception {
		TakesScreenshot takesScreenshot = (TakesScreenshot) BrowserManager.getInstance().getDriver();
		return takesScreenshot.getScreenshotAs(OutputType.BASE64);
	}
}
