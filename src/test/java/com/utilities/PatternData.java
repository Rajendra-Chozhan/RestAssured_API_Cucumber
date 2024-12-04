package com.utilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static com.utilities.JsonUtil.getJsonObject;

import java.io.FileReader;
import java.io.IOException;
public class PatternData {

	private static final Logger log = LogManager.getLogger(PatternData.class);
	LocalScenarioContext localscenarioContext = ContextLoader.getLocalScenarioContext();
	
public String validateResponseAPI() {
	String response = localscenarioContext.getStringContext(Context.RESPONSE_BODY);
	StringBuilder responseJson = new StringBuilder(response);
	char firstChar = responseJson.charAt(0);

	if (firstChar == '[') {
		responseJson.deleteCharAt(0);
		responseJson.deleteCharAt(responseJson.length()-1);
		return responseJson.toString();
	}
	return response;
}
	

public String validateExpectedAPI( JSONObject jsonValues, String tcName) {
	String response = jsonValues.get(tcName).toString();
	StringBuilder responseJson = new StringBuilder(response);
	char firstChar = responseJson.charAt(0);
	
	if (firstChar == '[') {
		responseJson.deleteCharAt(0);
		responseJson.deleteCharAt(responseJson.length()-1);
		return responseJson.toString();
		
	}
	return response;
}
	
	public boolean compareGetPatternJsonvalueswithResponse(String tcName) {
		JSONObject jsonValues = getJsonObject(JsonFileManager.getPatternJsonFileReader("getrequest"));
		String response = jsonValues.get(tcName).toString();
		System.out.println(response);
		String response1 = localscenarioContext.getStringContext(Context.RESPONSE_BODY);
		System.out.println(response1);
		return JsonUtil.CompareJson(validateExpectedAPI(jsonValues,tcName),validateResponseAPI() );
	}

	public boolean compareGetPatternJsonvalueswithResponseforPOST(String tcName) {
		JSONObject jsonValues = getJsonObject(JsonFileManager.getPatternJsonFileReader("postrequest"));
		String Expectedresponse = jsonValues.get(tcName).toString();
		System.out.println("The expected response is :"+Expectedresponse);
		String response1 = localscenarioContext.getStringContext(Context.RESPONSE_BODY);
		System.out.println("The Actual response is :"+response1);
	//	return JsonUtil.CompareJson(Expectedresponse,response1 );
	return JsonUtil.CompareJson(validateExpectedAPI(jsonValues,tcName),validateResponseAPI() );
	}
	
}

