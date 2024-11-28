package com.utilities;

import java.util.HashMap;
import java.util.Map;

public class LocalScenarioContext {

	
	public Map<String,Object> scenarioContext = new HashMap();
	public Map<String,String> scenarioContextString = new HashMap();
	public  Map<String,Map <String,String>> scenarioContextMapString = new HashMap();
	


	public void setStringContext(Context key, String value) {
	
		scenarioContextString.put(key.toString(),value);
	}


	public String getStringContext(Context key) {
		
		
		return isContains(key) ? scenarioContextString.get(key.toString()) :null;
	}




	
public void setMapStringContext(Context key, Map<String, String> value) {
	scenarioContextMapString.put(key.toString(),value);	
		
	}
	
	
	
public Map<String, String> getMapStringContext(Context key) {
		
		return isContains(key) ? scenarioContextMapString.get(key.toString()): null;
	}

	
	
	
	public boolean isContains(Context key) {
		
		return scenarioContext.containsKey(key.toString()) || scenarioContextString.containsKey(key.toString())
				
	|| scenarioContextMapString.containsKey(key.toString());
		
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
