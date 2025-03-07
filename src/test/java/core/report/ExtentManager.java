package core.report;

import com.aventstack.extentreports.ExtentTest;

public final class ExtentManager {

	private static final ThreadLocal<ExtentTest> extentTestLocal = new ThreadLocal();

	private ExtentManager() {}

	static void setExtentTest(ExtentTest test) {
		extentTestLocal.set(test);
	}

	public static ExtentTest getExtentTest() {
		return extentTestLocal.get();
	}

	static void unloadExtentTest() {
		extentTestLocal.remove();
	}
}
