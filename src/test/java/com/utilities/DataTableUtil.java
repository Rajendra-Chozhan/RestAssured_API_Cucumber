package com.utilities;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.cucumber.datatable.DataTable;

public class DataTableUtil {

	public DataTableUtil()
{}
	

List <List<String>> raw;


public static Map<String, String > toMap(DataTable dt){
	
	Map<String,String> retMap = new LinkedHashMap();
	List<Map<String,String>> options = dt.asMaps();
	Iterator var3 = options.iterator();
	
	while (var3.hasNext()) {
		
		Map<String,String> option = (Map)var3.next();
		retMap.put(option.get("Field"), option.get("Value"));
	}
	
	return retMap;
	
}

}
