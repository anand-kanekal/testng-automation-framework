package framework.constant;

import java.io.File;

public final class Path {
	
	private Path() {}

	public static final String MAIN_RESOURCES = "src" + File.separator + "main" + File.separator + "resources";
	public static final String TEST_RESOURCES = "src" + File.separator + "test" + File.separator + "resources";
	public static final String EXTENT_REPORTS = System.getProperty("user.dir") + File.separator + "test-output"
			+ File.separator + "extent-reports";
}
