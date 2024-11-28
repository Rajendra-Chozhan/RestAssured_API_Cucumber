package com.utilities;



import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EnvPropertyManager {
	

	private static final Logger log = LogManager.getLogger(EnvPropertyManager.class);
	private static Properties envProperties  = new Properties();
	private static StringWriter writer = new StringWriter();
	private static final String PROPERTIES_FILE = "configuration"+ File.separator+ "Config.properties";



	static {
	try (InputStream resourceAsStream = EnvPropertyManager.class.getClassLoader()
	.getResourceAsStream(PROPERTIES_FILE)) {
	envProperties.load(resourceAsStream);
	} catch (IOException e) {
	log.error( "Properties file not found in path", e.getMessage());
}
}

public static String getUrl() {
	
	String url = envProperties.getProperty("url");
	return url;
	
	
}

public static String getInvalidToken() {

	return null;
}

public static String getExpiredToken() {

	return null;
}

public static String apiBaseURI() {
	return envProperties.getProperty("api.baseuri");
}

	public static String apiBaseURI1() {
		return envProperties.getProperty("api.baseuri1");
	}



public static String getPatternJsonPath(String json) {
	
	if (json.equalsIgnoreCase("getrequest")) {
		return envProperties.getProperty("getrequestjson.path");
	}
	else {
		return envProperties.getProperty("postrequestjson.path");
	}
	
}


	public static String getPayloadJsonPath(String json) {

		if (json.equalsIgnoreCase("getrequest")) {
			return envProperties.getProperty("getrequestjson.path");
		}
		else {
			return envProperties.getProperty("payload.path");
		}

	}






}