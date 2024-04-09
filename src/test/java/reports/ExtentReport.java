package reports;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import constants.Path;

public final class ExtentReport {

	private static ExtentReport extentReport;
	private ExtentReports extent;
	// private Map<Integer, ExtentTest> extentMap = new HashMap<Integer, ExtentTest>();

	private ExtentReport() {

	}

	public static ExtentReport getInstance() {
		if (Objects.isNull(extentReport)) {
			extentReport = new ExtentReport();
		}

		return extentReport;
	}

	public ExtentReports createReport(String reportName) throws Exception {
		extent = new ExtentReports();

		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(
				Path.EXTENT_REPORTS + File.separator + reportName + ".html");

		try {
			final File extentConfig = new File(
					Path.TEST_RESOURCES + File.separator + "config" + File.separator + "spark-config.json");
			extentSparkReporter.loadJSONConfig(extentConfig);
		} catch (IOException e) {
			throw new Exception("An error encountered while reading spark-config.xml");
		}

		addSystemInfo(extent);
		extent.attachReporter(extentSparkReporter);

		return extent;
	}
	
	private void addSystemInfo(ExtentReports extentReports) {
		extentReports.setSystemInfo("os", System.getProperty("os.name"));
		extentReports.setSystemInfo("java version", System.getProperty("java.version"));
	}

	public ExtentReports getExtentReports() throws Exception {
		if (Objects.nonNull(extent)) {
			return extent;
		}

		throw new Exception("Extent reports is not created");
	}

	public void startTest(String testName) {
		ExtentTest test = extent.createTest(testName);
		ExtentManager.setExtentTest(test);
		// extentMap.put((int) Thread.currentThread().getId(), test);
		// return test;
	}

//	public ExtentTest getTest() throws Exception {
//		ExtentTest test = extentMap.get((int) Thread.currentThread().getId());
//
//		if (Objects.nonNull(test)) {
//			return test;
//		}
//
//		throw new Exception("Extent test is not intantiated. Kindly start extent test");
//	}

	public void flushReport() {
		extent.flush();
		ExtentManager.unloadExtentTest();
	}
}
