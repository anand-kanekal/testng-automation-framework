package handler;

import org.openqa.selenium.WebDriver;

public class DriverHandler {

	private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();
	
	private DriverHandler() {
		
	}

	public static void setDriver(WebDriver driver) {
		driverThread.set(driver);
	}

	public static WebDriver getDriver() {
		return driverThread.get();
	}

	public static void unload() {
		driverThread.remove();
	}

}
