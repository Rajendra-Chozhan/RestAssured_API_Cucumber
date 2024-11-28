package com.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.asserts.SoftAssert;

public class CustomAssert {



	public static void assertIfNullorEmpty (String field, String message) {

		SoftAssert softAssert = new SoftAssert();

		softAssert.assertFalse(CommonJavaUtil.isNullOrEmpty (field), message);

		softAssert.assertAll();

		}




}
