package com.utilities;

import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.apache.logging.log4j.LogManager;

public class FileUtil {
	
	private static final Logger log = LogManager.getLogger(FileUtil.class);
	
	
	public FileUtil() {}
	
	public static FileReader getFileReader(String filePath) {
		
		FileReader file = null;
		
		
		try {
			
			file = new FileReader(filePath);
			
		}catch (FileNotFoundException var3) {
			
			log.error("Unable to read the file");
		}
		return file;
		
		
	}

}
