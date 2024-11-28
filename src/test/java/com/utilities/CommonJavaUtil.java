package com.utilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CommonJavaUtil {

	private static final Logger log = LogManager.getLogger(CommonJavaUtil.class);

	public static boolean isNullOrEmpty(String str) {
	
		return str == null || str.trim().isEmpty();	}
}
