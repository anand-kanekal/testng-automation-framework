package reports;

import java.io.File;
import java.util.Objects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import constants.Path;
import exceptions.ExtentReportException;

public final class ExtentReport {

	private static ExtentReport extentReport;
	private ExtentReports extent;

	private ExtentReport() {

	}

	public static ExtentReport getInstance() {
		if (Objects.isNull(extentReport)) {
			extentReport = new ExtentReport();
		}

		return extentReport;
	}

	/**
	 * Create an extent report
	 * 
	 * @param reportName
	 * @return
	 * @throws Exception
	 * @author Anand Kanekal
	 */
	public ExtentReports createReport(String reportName) throws Exception {
		extent = new ExtentReports();

		String reportPath = Path.EXTENT_REPORTS + File.separator + reportName + ".html";
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(reportPath);

		try {
			final File extentConfig = new File(
					Path.TEST_RESOURCES + File.separator + "config" + File.separator + "spark-config.json");
			extentSparkReporter.loadJSONConfig(extentConfig);
		} catch (Exception e) {
			throw new ExtentReportException("An error encountered while reading spark-config.xml");
		}

		addSystemInfo(extent);
		extent.attachReporter(extentSparkReporter);

		return extent;
	}
	
	/**
	 * Add system information to the report
	 * 
	 * @param extentReports
	 * @author Anand Kanekal
	 */
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

	/**
	 * Create a new test in report
	 * 
	 * @param testName defines the name of the test
	 * @author Anand Kanekal
	 */
	public void startTest(String testName) {
		ExtentTest test = extent.createTest(testName);
		ExtentManager.setExtentTest(test);
	}

	/**
	 * Flush the content to the report
	 * 
	 * @author Anand Kanekal
	 */
	public void flushReport() {
		extent.flush();
		ExtentManager.unloadExtentTest();
	}
}
