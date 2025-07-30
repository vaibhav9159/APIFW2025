package com.qa.api.configmanager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

	
	private static Properties properties = new Properties();
	
	static {
				// this is reflection concept or reflection api we are using it , its faster than file i/p stream
		// as its not a methd , its a block it will directly go to metaspace and its available everywhre anytime, evry class
		// since its a static block it will be loaded even before the class is loaded
			InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream("config/config.properties");
			if(input!=null) {
					try {
						properties.load(input);
					} catch (IOException e) {
						
						e.printStackTrace();
					}
			}	
	}
		/// it will fetch the key from prop file and return its corresponding value
	public static String get(String key) {
		return properties.getProperty(key).trim();
	}
	
	
	public static void set(String key, String value) {
		properties.setProperty(key, value);
	}
	
	
	
	
	
}
