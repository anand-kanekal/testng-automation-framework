package com.framework.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileManager {

	private static PropertyFileManager propertyFileManager;

	private PropertyFileManager() {

	}

	/**
	 * Get propertyFileManager instance
	 * 
	 * @return propertyFileManager
	 * @author Anand Kanekal
	 */
	public static PropertyFileManager getInstance() {
		if (propertyFileManager == null) {
			propertyFileManager = new PropertyFileManager();
		}

		return propertyFileManager;
	}

	/**
	 * Loads all the properties from file location
	 * 
	 * @param properties defines the properties object
	 * @param fileLocation defines the location of the properties file
	 * @author Anand Kanekal
	 */
	public void loadProperties(Properties properties, String fileLocation) {
		try (FileInputStream inputStream = new FileInputStream(fileLocation)) {
			properties.load(inputStream);
		} catch (FileNotFoundException e) {
			System.out.println("Property file not found at location " + fileLocation);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("An error encountered while reading property file present at location " + fileLocation);
			e.printStackTrace();
		}
	}

}
