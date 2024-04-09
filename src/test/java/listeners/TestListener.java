package listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import reports.ExtentLogger;
import reports.ExtentReport;

public class TestListener implements ITestListener, ISuiteListener {

	ExtentReport extent;
	
	@Override
	public void onStart(ISuite suite) {
		extent = ExtentReport.getInstance();
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentReport.getInstance().startTest(result.getMethod().getDescription());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		try {
			ExtentLogger.pass(result.getMethod().getDescription());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			ExtentLogger.fail(result.getThrowable(), true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onFinish(ISuite suite) {
		ExtentReport.getInstance().flushReport();
	}
}
