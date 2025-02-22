package framework.listeners;

import framework.report.ExtentLogger;
import framework.report.ExtentReport;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener, ISuiteListener {

	ExtentReport extent;
	
	/**
	 * 
	 * 
	 * @author Anand Kanekal
	 */
	@Override
	public void onStart(ISuite suite) {
		extent = ExtentReport.getInstance();
	}

	/**
	 * Creates a new test in the report when test starts
	 * 
	 * @author Anand Kanekal
	 */
	@Override
	public void onTestStart(ITestResult result) {
		ExtentReport.getInstance().startTest(result.getMethod().getDescription());
	}

	/**
	 * Logs success message
	 * 
	 * @author Anand Kanekal
	 */
	@Override
	public void onTestSuccess(ITestResult result) {
		try {
			ExtentLogger.pass(result.getMethod().getDescription());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Logs failure message to the report along with screenshot
	 * 
	 * @author Anand Kanekal
	 */
	@Override
	public void onTestFailure(ITestResult result) {
		try {
			ExtentLogger.fail(result.getThrowable(), true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Flushes the report when suite execution ends
	 * 
	 * @author Anand Kanekal
	 */
	@Override
	public void onFinish(ISuite suite) {
		ExtentReport.getInstance().flushReport();
	}
}
