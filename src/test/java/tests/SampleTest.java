package tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import reports.ExtentLogger;
import reports.ExtentReport;

public class SampleTest extends BaseTest {

	private ExtentReport extentReport;

	@BeforeClass
	public void beforeClass() throws Exception {
		extentReport = ExtentReport.getInstance();
		extentReport.createReport("Google");
	}

	@Test(description = "First test")
	public void firstTest() throws Exception {
		System.out.println("First Test " + Thread.currentThread().getId());
		ExtentLogger.info("First test executed");
		System.out.println(10/0);
	}

	@Test(description = "Second test")
	public void secondTest() throws Exception {
		System.out.println("Second Test " + Thread.currentThread().getId());
		ExtentLogger.info("Second test executed");
		System.out.println(10/0);
	}
}
