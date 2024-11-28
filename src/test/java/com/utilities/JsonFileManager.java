package com.utilities;



import java.io.FileReader;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;




public class JsonFileManager {

	private static final Logger log = LogManager.getLogger(JsonFileManager.class);

	public static FileReader getPatternJsonFileReader(String json){
		
		log.info("Reading Pattern Json file");
		return FileUtil.getFileReader(EnvPropertyManager.getPatternJsonPath(json));
		
	}

	public static FileReader getPayloadJsonFileReader(String json){

		log.info("Reading Pattern Json file");
		return FileUtil.getFileReader(EnvPropertyManager.getPayloadJsonPath(json));

	}

}