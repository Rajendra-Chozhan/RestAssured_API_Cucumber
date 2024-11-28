package com.utilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;


public class JsonUtil {

	private static final Logger log = LogManager.getLogger(JsonUtil.class);
	
	
	
	public static JSONObject getJsonObject (FileReader jsonFile) {
		
			JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = null;
		
		try { 
			
			jsonObject = (JSONObject)jsonParser.parse(jsonFile);
		}catch(ParseException | IOException var4){
			log.error("Unable to parse json file:{};Exception:{}",jsonFile,var4.getMessage());
		
			
		}
		return jsonObject;	
		
		
		
	}
	
	
	
	public static String getJsonString (Object classObject) {
		
		Gson gson = new Gson();
		return gson.toJson(classObject);
		
	}
	
	
	
	public static Boolean CompareJson(String actualJson, String expectedJson) {
		Boolean comparisonResult =false;
		Map<String,Object> resultMap = new HashMap();
		Gson gson = new Gson();
		Type mapType = (new TypeToken<Map<String, Object>>(){
					}).getType();
		
		Map<String,Object> actualMap = gson.fromJson(actualJson, mapType);
		Map<String,Object> expectedMap = gson.fromJson(expectedJson, mapType);
	
		MapDifference<String,Object> difference = Maps.difference(actualMap,expectedMap);
		
		if (difference.entriesOnlyOnLeft().isEmpty()) {
			resultMap.putAll(difference.entriesOnlyOnLeft());
		}else {
			resultMap.put("Entries only in actual", difference.entriesOnlyOnLeft());
		}
		
		if (difference.entriesOnlyOnRight().isEmpty()) {
			resultMap.putAll(difference.entriesOnlyOnRight());
		}else {
			resultMap.put("Entries only in expected", difference.entriesOnlyOnRight());
		}
		
		if (difference.entriesDiffering().isEmpty()) {
			resultMap.putAll(difference.entriesDiffering());
		}else {
			resultMap.put("Entries differing", difference.entriesDiffering());
		}
		
		if (resultMap.isEmpty()) {
			comparisonResult = true;}
			else {
				Assert.assertEquals(actualMap, expectedMap);
				Assert.fail("The Json values are differing and thus test case failed");
				log.error("Json are not equal, Json Difference:{}",resultMap);
			}
			return comparisonResult;}
		
		
		
		
		
		
	
}
