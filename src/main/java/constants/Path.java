package constants;

import java.io.File;

public final class Path {
	
	private Path() {
		
	}

	public static final String MAIN_RESOURCES = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "main" + File.separator + "resources";
	public static final String TEST_RESOURCES = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "test" + File.separator + "resources";
	public static final String EXTENT_REPORTS = System.getProperty("user.dir") + File.separator + "test-output"
			+ File.separator + "extent-reports";
}
