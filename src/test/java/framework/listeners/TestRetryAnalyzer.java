package framework.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class TestRetryAnalyzer implements IRetryAnalyzer {

	private int counter = 0;
	
	private static final int retryLimit = 0;
	
	/**
	 * Retry failed test
	 * 
	 * @author Anand Kanekal
	 */
	@Override
	public boolean retry(ITestResult result) {
		boolean flag = false;
		
		if ( counter < retryLimit ) {
			counter++;
			flag = true;
			return flag;
		}
		
		flag = false;
		return flag;
	}

}
