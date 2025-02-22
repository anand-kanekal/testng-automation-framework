package framework.report;

import com.aventstack.extentreports.ExtentTest;

public final class ExtentManager {

	private static ThreadLocal<ExtentTest> extentTestLocal = new ThreadLocal<ExtentTest>();

	private ExtentManager() {

	}

	static void setExtentTest(ExtentTest test) {
		extentTestLocal.set(test);
	}

	static ExtentTest getExtentTest() {
		return extentTestLocal.get();
	}

	static void unloadExtentTest() {
		extentTestLocal.remove();
	}
}
