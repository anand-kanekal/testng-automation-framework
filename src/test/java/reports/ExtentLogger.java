package reports;

import com.aventstack.extentreports.MediaEntityBuilder;

import utilities.CommonUtils;

public class ExtentLogger {
	
	public static void info(String message) throws Exception {
		ExtentManager.getExtentTest().info(message);
	}
	
	public static void info(String message, boolean isScreenshotRequired) throws Exception {
		ExtentManager.getExtentTest().info(message, MediaEntityBuilder.createScreenCaptureFromBase64String(CommonUtils.getBase64Screenshot()).build());
	}
	
	public static void pass(String message) throws Exception {
		ExtentManager.getExtentTest().pass(message);
	}
	
	public static void pass(String message, boolean isScreenshotRequired) throws Exception {
		ExtentManager.getExtentTest().pass(message, MediaEntityBuilder.createScreenCaptureFromBase64String(CommonUtils.getBase64Screenshot()).build());
	}
	
	public static void fail(String message) throws Exception {
		ExtentManager.getExtentTest().fail(message);
	}
	
	public static void fail(String message, boolean isScreenshotRequired) throws Exception {
		ExtentManager.getExtentTest().fail(message, MediaEntityBuilder.createScreenCaptureFromBase64String(CommonUtils.getBase64Screenshot()).build());
	}
	
	public static void fail(Throwable throwable, boolean isScreenshotRequired) throws Exception {
		ExtentManager.getExtentTest().fail(throwable, MediaEntityBuilder.createScreenCaptureFromBase64String(CommonUtils.getBase64Screenshot()).build());
	}
	
	public static void skip(String message) throws Exception {
		ExtentManager.getExtentTest().skip(message);
	}
}
