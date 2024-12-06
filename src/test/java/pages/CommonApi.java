package pages;

import java.util.LinkedHashMap;
import com.utilities.JsonUtil;
import java.util.Map;
import java.util.List;
import java.util.Locale;
import java.util.ArrayList;
import java.util.Arrays;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import io.restassured.response.ValidatableResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.asserts.SoftAssert;

import com.utilities.Context;
import com.utilities.ContextLoader;
import com.utilities.CustomAssert;
import com.utilities.EnvPropertyManager;
import com.utilities.JsonFileManager;
import com.utilities.LocalScenarioContext;

import static io.restassured.RestAssured.given;
import static io.restassured.http.Method.GET;


public class CommonApi {
	
	protected Logger log;

public CommonApi(){
	super();
	log = LogManager.getLogger(this.getClass());
}
	
LocalScenarioContext localScenarioContext = ContextLoader.getLocalScenarioContext();
RestClientUtil restClientutil = new RestClientUtil();
SoftAssert softAssert = new SoftAssert();
EnvPropertyManager envPropertyManager = new EnvPropertyManager();


	public  void setAPIRequestParameters (Map<String, String> requestParams) {
		validateParameterNamesAndValues (requestParams);
		Map<String, String> headers = new LinkedHashMap<>();
		headers.put("Content-Type", "application/json");
      	headers.put("Accept-Encoding", "gzip, deflate, br");
	    headers.put("Authorization", localScenarioContext.getStringContext(Context.ACCESS_TOKEN));
		Map<String, String> queryParameters = new LinkedHashMap<>();
		String path = null;
		Map<String, String> pathParameters = new LinkedHashMap<>();
		String idValue = localScenarioContext.isContains(Context.ID) ? localScenarioContext.getStringContext(Context.ID): "";

		for (Map.Entry<String, String> entry: requestParams.entrySet()) {
		switch (entry.getKey().toUpperCase()) {
			case "HEADERS":
			
		List<String> headerParams = Arrays.asList(entry.getValue().split(","));
		headerParams = modifyHeaderParameters (headerParams);
		for (String param: headerParams) {
		String[] paramNameValue = param.split("=");
		if (paramNameValue.length > 1)
		headers.put(paramNameValue[0], paramNameValue[1]);
		else
			headers.remove(paramNameValue[0]);
		
		}

		break;
		case "QUERYPARAMETERS":
			List<String> queryParams = Arrays.asList(entry.getValue().split(","));
			for (String param : queryParams) {
			param=param.contains(";") ? param.replace(";", ","): param;
			String[] paramNameValue = param.split("=");
			if (paramNameValue.length>1){
			queryParameters.put(paramNameValue [0], paramNameValue[1]);
			}

			}

			break;
			case "PATH":
			path = entry.getValue();
			path = path.toLowerCase(Locale.ROOT).contains("<id>")? path.replace("<Id>",idValue): path;
			break;
			case "PATHPARAMETERS":

				List<String> pathParams = Arrays.asList(entry.getValue().split(","));
				for (String param : pathParams) {
				String[] paramNameValue = param.split("=");
				String paramName = paramNameValue[0];
				String paramValue = paramNameValue[1];
				paramValue = paramName.equalsIgnoreCase("Id") ? idValue: paramValue;
				pathParameters.put(paramName,
				paramValue.equalsIgnoreCase("[blank]")?"": paramValue);
				}
				break;
				
		}}

		localScenarioContext.setMapStringContext(Context.HEADERS, headers);
		localScenarioContext.setMapStringContext(Context.QUERY_PARAMS, queryParameters);
		localScenarioContext.setStringContext(Context.PATH, path);
		localScenarioContext.setMapStringContext(Context.PATH_PARAMS, pathParameters);
		log.info("Headers ->"+ headers);
		log.info("Query Parameters >"+ queryParameters);
		log.info("Path : -> " + path);
		log.info("Path Parameters:->" + pathParameters);
		System.out.println("Method is done");
		}


public void validateParameterNamesAndValues(Map<String, String> requestParams) {

for (Map. Entry<String, String> entry:  requestParams.entrySet()) {
log.info("Validate ParameterNamesAndValues:"+ entry.getKey() + ":" + entry.getValue());

String parameter = entry.getKey();
String value = entry.getValue();
// Fail if null or emoty
CustomAssert.assertIfNullorEmpty(parameter, "Parameter should be specified for API request");
CustomAssert.assertIfNullorEmpty(value,"Value should be specified for the parameter: " + parameter + " for API request");
switch (parameter.toUpperCase()) {
case "HEADERS":
case "QUERYPARAMETERS":
case "PATH":
case "PATHPARAMETERS":
break;
default:
CustomAssert.assertIfNullorEmpty( null, "Incorrect parameter name:" + parameter);
break;
}
}
	}


public List<String> modifyHeaderParameters (List<String> headerParams) {

List<String> result = new ArrayList<>();
for (String param: headerParams) {
String[] paramNameValue = param.split( "");
String paramName = paramNameValue[0];
String paramValue = paramNameValue [1];

if (paramName.equalsIgnoreCase( "Authorization")) {
String modifiedParamValue = paramValue;
switch (paramValue.toUpperCase()) {
case "INVALID":

modifiedParamValue = EnvPropertyManager.getInvalidToken();
break;
case "EXPIRED":
modifiedParamValue = EnvPropertyManager.getExpiredToken();
break;
case "NO TOKEN":

modifiedParamValue = "";
break;
}
paramValue = modifiedParamValue;
}
result.add(paramName + "="+ paramValue);
}
return result;
}



public void triggerAPI (String method, String api, int expectedStatusCode, String statusDescription) {

String baseUri = null;
Response response;
Object requestBody = null;
Map<String, String> headers = localScenarioContext.getMapStringContext(Context.HEADERS);
Map<String, String> queryParams = localScenarioContext.getMapStringContext(Context.QUERY_PARAMS);
String path = localScenarioContext.getStringContext(Context.PATH);
Map<String, String> pathParams = localScenarioContext.getMapStringContext(Context.PATH_PARAMS);

if (method.equalsIgnoreCase("POST")) {
	JSONObject JsonValues = JsonUtil.getJsonObject(JsonFileManager.getPatternJsonFileReader("postrequest"));
	String requestJson = JsonUtil.getJsonString(JsonValues);
	requestJson = "["+requestJson+"]";
	localScenarioContext.setStringContext(Context.REQUEST_BODY, requestJson);
}

baseUri = envPropertyManager.apiBaseURI();
requestBody = localScenarioContext.getStringContext(Context.REQUEST_BODY);

localScenarioContext.setStringContext(Context.METHOD,method);
response = restClientutil.doHttpRequestWithBodyasresponse (baseUri, getMethod(method), headers, requestBody, path, expectedStatusCode );

String responseBody = response.body().asString();

boolean statusDesCheck = response.statusLine().toUpperCase().contains(statusDescription.toUpperCase());

softAssert.assertEquals(response.statusCode(), expectedStatusCode, "Status Code is Mismatched");
log.info("Actual Status Code is: " + response.getStatusCode()+" Expected Status Code is "+expectedStatusCode);
	System.out.println(response.getStatusCode());
softAssert.assertTrue(statusDesCheck, "Status Description is Mismatched");

log.info("Actual Status Description is: " + response.statusLine()+ "Expected Status Description is" + statusDescription);
	System.out.println(response.statusLine());
log.info("Response is:->" + responseBody.trim());

localScenarioContext.setStringContext(Context.RESPONSE_BODY, responseBody);

softAssert.assertAll();


}



	public void triggerAPI_POST (String method, String api, int expectedStatusCode,String tcName) {

		String baseUri1 = null;
		Response response;
		Object requestBody = null;
		Map<String, String> headers = localScenarioContext.getMapStringContext(Context.HEADERS);
		Map<String, String> queryParams = localScenarioContext.getMapStringContext(Context.QUERY_PARAMS);
		String path = localScenarioContext.getStringContext(Context.PATH);
		Map<String, String> pathParams = localScenarioContext.getMapStringContext(Context.PATH_PARAMS);

		if (method.equalsIgnoreCase("POST")) {
			JSONObject jsonValues = JsonUtil.getJsonObject(JsonFileManager.getPayloadJsonFileReader("postrequest"));
			String requestJson = jsonValues.get(tcName).toString();
			requestJson = "["+requestJson+"]";
			StringBuilder RequestJson = new StringBuilder(requestJson);
			RequestJson.deleteCharAt(0);
			RequestJson.deleteCharAt(RequestJson.length()-1);
			System.out.println("The request body is "+RequestJson);
			localScenarioContext.setStringContext(Context.REQUEST_BODY, String.valueOf(RequestJson));
		} else if

		(method.equalsIgnoreCase("PUT")) {
			JSONObject jsonValues = JsonUtil.getJsonObject(JsonFileManager.getPayloadJsonFileReader("postrequest"));
			String requestJson = jsonValues.get(tcName).toString();
			requestJson = "["+requestJson+"]";
			StringBuilder RequestJson = new StringBuilder(requestJson);
			RequestJson.deleteCharAt(0);
			RequestJson.deleteCharAt(RequestJson.length()-1);
			System.out.println("The request body is "+RequestJson);
			localScenarioContext.setStringContext(Context.REQUEST_BODY, String.valueOf(RequestJson));
		}

		baseUri1 = envPropertyManager.apiBaseURI1();
		requestBody = localScenarioContext.getStringContext(Context.REQUEST_BODY);

		localScenarioContext.setStringContext(Context.METHOD,method);
		response = restClientutil.doHttpRequestWithBodyasresponse (baseUri1, getMethod(method), headers, requestBody, path, expectedStatusCode );

		String responseBody = response.body().asString();
		softAssert.assertEquals(response.statusCode(), expectedStatusCode, "Status Code is Mismatched");
		log.info("Actual Status Code is: " + response.getStatusCode()+" Expected Status Code is "+expectedStatusCode);
		System.out.println(response.getStatusCode());
		System.out.println(response.statusLine());
		log.info("Response is:->" + responseBody.trim());
		System.out.println(responseBody.trim());

		localScenarioContext.setStringContext(Context.RESPONSE_BODY, responseBody);

		softAssert.assertAll();


	}



	public void triggerAPI (String method, String api, int expectedStatusCode) {
		String baseUri = null;
		Response response;
		Object requestBody = null;
		Map<String, String> headers = localScenarioContext.getMapStringContext(Context.HEADERS);
		Map<String, String> queryParams = localScenarioContext.getMapStringContext(Context.QUERY_PARAMS);
		String path = localScenarioContext.getStringContext(Context.PATH);
		Map<String, String> pathParams = localScenarioContext.getMapStringContext(Context.PATH_PARAMS);

		if (method.equalsIgnoreCase("POST")) {
			JSONObject JsonValues = JsonUtil.getJsonObject(JsonFileManager.getPatternJsonFileReader("postrequest"));
			String requestJson = JsonUtil.getJsonString(JsonValues);
			requestJson = "["+requestJson+"]";
			localScenarioContext.setStringContext(Context.REQUEST_BODY, requestJson);
		}

		baseUri = envPropertyManager.apiBaseURI();
		requestBody = localScenarioContext.getStringContext(Context.REQUEST_BODY);

		localScenarioContext.setStringContext(Context.METHOD,method);
		response = restClientutil.doHttpRequestWithBodyasresponse (baseUri, getMethod(method), headers, requestBody, path, expectedStatusCode );

		String responseBody = response.body().asString();

		softAssert.assertEquals(response.statusCode(), expectedStatusCode, "Status Code is Mismatched");
		log.info("Actual Status Code is: " + response.getStatusCode()+" Expected Status Code is "+expectedStatusCode);
		System.out.println(response.getStatusCode());

		log.info("Actual Status Description is: " + response.statusLine());
		System.out.println(response.statusLine());
		log.info("Response is:->" + responseBody.trim());

		localScenarioContext.setStringContext(Context.RESPONSE_BODY, responseBody);
		softAssert.assertAll();
	}

public Method getMethod(String methodType) {
	Method method = null;
	switch (methodType.toUpperCase()) {
		case "GET":
		method = Method.GET;
		break;
	case "POST":
		method = Method.POST;
		break;
	case "PUT":
		method = Method.PUT;
		break;
	case "DELETE":
		method = Method.DELETE;
		break;
	case "HEAD":
		method = Method.HEAD;
		break;
	case "OPTIONS":
		method = Method.OPTIONS;
		break;
	case "PATCH":
		method = Method.PATCH;
		break;
		default:
			log.error(methodType + "method not available");
	}
	return method;

}




	public void triggerandvalidateBadrequest (String method, String api, int expectedStatusCode,String statusTitle,String errorDescription,String tcName) {
		String baseUri1 = null;
		Response response;
		Object requestBody = null;
		Map<String, String> headers = localScenarioContext.getMapStringContext(Context.HEADERS);
		Map<String, String> queryParams = localScenarioContext.getMapStringContext(Context.QUERY_PARAMS);
		String path = localScenarioContext.getStringContext(Context.PATH);
		Map<String, String> pathParams = localScenarioContext.getMapStringContext(Context.PATH_PARAMS);

		if (method.equalsIgnoreCase("POST")) {
			JSONObject JsonValues = JsonUtil.getJsonObject(JsonFileManager.getPatternJsonFileReader("postrequest"));
			String requestJson = JsonUtil.getJsonString(JsonValues);
			requestJson = "["+requestJson+"]";
			localScenarioContext.setStringContext(Context.REQUEST_BODY, requestJson);
		}

		baseUri1 = envPropertyManager.apiBaseURI1();
		requestBody = localScenarioContext.getStringContext(Context.REQUEST_BODY);

		localScenarioContext.setStringContext(Context.METHOD,method);
		response = restClientutil.doHttpRequestWithBodyasresponse (baseUri1, getMethod(method), headers, requestBody, path, expectedStatusCode );

		String responseBody = response.body().asString();

		softAssert.assertEquals(response.statusCode(), expectedStatusCode, "Status Code is Mismatched");
		log.info("Actual Status Code is: " + response.getStatusCode()+" Expected Status Code is "+expectedStatusCode);
		System.out.println(response.getStatusCode());

		log.info("Actual Status Description is: " + response.statusLine());
		System.out.println(response.statusLine());
		log.info("Response is:->" + responseBody.trim());

		localScenarioContext.setStringContext(Context.RESPONSE_BODY, responseBody);

		JsonPath js = new JsonPath(responseBody);
		String errordescription = js.getString("errors.id");
		StringBuilder sb = new StringBuilder(errordescription);
		sb.deleteCharAt(errordescription.length()-1);
		sb.deleteCharAt(0);
		String updatederrordescription = sb.toString();
		System.out.println(updatederrordescription);
		Assert.assertEquals(updatederrordescription,errorDescription);

		String statustitle = js.getString("title");
		Assert.assertEquals(statustitle,statusTitle);
		System.out.println("The Actual status Title is "+statustitle+"The expected status Title is "+statusTitle);

		System.out.println("The Actual status description is " +updatederrordescription+ "The expected status Description is " +errorDescription);
	}

}