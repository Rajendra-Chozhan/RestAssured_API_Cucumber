package com.utilities;

public class ContextLoader {
	
	private static final SystemConfig config = new SystemConfig();

	//private static WebDriver edgeClientDriver;

	
private static LocalScenarioContext localScenarioContext;



public static void shutdown(){

//	if (edgeClientDriver != null){
	//	edgeClientDriver.quit();
//		edgeClientDriver = null();

	//}
}














public static LocalScenarioContext getLocalScenarioContext() {
	return (localScenarioContext == null)? localScenarioContext = new LocalScenarioContext():localScenarioContext;
	


}








}
