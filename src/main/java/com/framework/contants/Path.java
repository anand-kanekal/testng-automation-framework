package com.framework.contants;

import java.io.File;

public class Path {

	public static final String MAIN_RESOURCES = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "main" + File.separator + "resources";
	public static final String TEST_RESOURCES = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "test" + File.separator + "resources";

}
