package reports;

import com.aventstack.extentreports.MediaEntityBuilder;

import utility.CommonUtils;

public class ExtentLogger {
	
	/**
	 * Add info log to the extent report
	 * 
	 * @param message defines the log message
	 * @throws Exception
	 * @author Anand Kanekal
	 */
	public static void info(String message) throws Exception {
		ExtentManager.getExtentTest().info(message);
	}
	
	/**
	 * Add info log along with screenshot to the extent report
	 * 
	 * @param message defines the log message
	 * @param isScreenshotRequired defines if screenshot is required
	 * @throws Exception
	 * @author Anand Kanekal
	 */
	public static void info(String message, boolean isScreenshotRequired) throws Exception {
		if (isScreenshotRequired) {
			ExtentManager.getExtentTest().info(message, 
					MediaEntityBuilder.createScreenCaptureFromBase64String(CommonUtils.getBase64Screenshot()).build());
		} else {
			info(message);
		}
	}
	
	/**
	 * Add pass log to the extent report
	 * 
	 * @param message defines the log message
	 * @throws Exception
	 * @author Anand Kanekal
	 */
	public static void pass(String message) throws Exception {
		ExtentManager.getExtentTest().pass(message);
	}
	
	/**
	 * Add pass log along with screenshot to the extent report
	 * 
	 * @param message defines the log message
	 * @param isScreenshotRequired defines if screenshot is required
	 * @throws Exception
	 * @author Anand Kanekal
	 */
	public static void pass(String message, boolean isScreenshotRequired) throws Exception {
		if (isScreenshotRequired) {
			ExtentManager.getExtentTest().pass(message, 
					MediaEntityBuilder.createScreenCaptureFromBase64String(CommonUtils.getBase64Screenshot()).build());
		} else {
			pass(message);
		}
	}
	
	/**
	 * Add fail log to the extent report
	 * 
	 * @param message defines the log message
	 * @throws Exception
	 * @author Anand Kanekal
	 */
	public static void fail(String message) throws Exception {
		ExtentManager.getExtentTest().fail(message);
	}
	
	/**
	 * Add fail log along with screenshot to the extent report
	 * 
	 * @param message defines the log message
	 * @param isScreenshotRequired defines if screenshot is required
	 * @throws Exception
	 * @author Anand Kanekal
	 */
	public static void fail(String message, boolean isScreenshotRequired) throws Exception {
		ExtentManager.getExtentTest().fail(message, 
				MediaEntityBuilder.createScreenCaptureFromBase64String(CommonUtils.getBase64Screenshot()).build());
	}
	
	/**
	 * Add exception log along with screenshot to the extent report
	 * 
	 * @param throwable defines the exception stacktrace
	 * @param isScreenshotRequired defines if screenshot is required
	 * @throws Exception
	 * @author Anand Kanekal
	 */
	public static void fail(Throwable throwable, boolean isScreenshotRequired) throws Exception {
		ExtentManager.getExtentTest().fail(throwable, 
				MediaEntityBuilder.createScreenCaptureFromBase64String(CommonUtils.getBase64Screenshot()).build());
	}
	
	/**
	 * Add skip log to the extent report
	 * 
	 * @param message defines the log message
	 * @throws Exception
	 * @author Anand Kanekal
	 */
	public static void skip(String message) throws Exception {
		ExtentManager.getExtentTest().skip(message);
	}
}
