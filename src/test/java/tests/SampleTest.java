package tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import reports.ExtentLogger;
import reports.ExtentReport;
import utilities.TestManager;

public class SampleTest extends TestManager {

	private ExtentReport extentReport;

	@BeforeClass
	public void beforeClass() throws Exception {
		extentReport = ExtentReport.getInstance();
		extentReport.createReport("Google");
	}

	@Test(description = "First test")
	public void firstTest() throws Exception {
		ExtentLogger.info("First test executed");
	}

	@Test(description = "Second test")
	public void secondTest() throws Exception {
		ExtentLogger.info("Second test executed");
		System.out.println(10/0);
	}
}
