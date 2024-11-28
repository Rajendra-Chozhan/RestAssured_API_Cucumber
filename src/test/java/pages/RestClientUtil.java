package pages;

import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.http.Method;
import io.restassured.response.Response;

public class RestClientUtil {
	
	private static Response response = null;
	private static final Logger log = LogManager.getLogger(RestClientUtil.class);

	public RestClientUtil(){

	}

	public <T> Response doHttpRequestNoBody(String baseUri, Method methodType, Map <String, String> headers, String path){

		RequestSpecification requestSpecification = initRequestSpecifications(headers,(Object)null,(Map)null,(Map)null,baseUri);
		response = sendRequestSpecifications(baseUri,requestSpecification,methodType,path);

		return response;
	}

	public <T> Response doHttpRequestWithBodyasresponse(String baseUri, Method methodType, Map<String, String> headers, T requestBody, String path, int expectedStatusCode){

		RequestSpecification requestSpecification = initRequestSpecifications(headers,requestBody,(Map)null, (Map)null,baseUri);
		response = sendRequestSpecifications(baseUri,requestSpecification,methodType,path);
		((ValidatableResponse)response.then()).statusCode(expectedStatusCode);
		return response;
	}

	private static <T> RequestSpecification initRequestSpecifications(Map <String,String> headers, T requestBody,Map<String, String> pathParameters, Map<String,String> queryParameters, String baseUri){

		RequestSpecification request = RestAssured.given();
		request.config(RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))).baseUri(baseUri);


		if (headers != null){
		request.headers(headers);
		}


		if (requestBody != null){
			request.body(requestBody);
		}

		if (pathParameters != null){
			request.pathParams(pathParameters);

		}
		if (queryParameters != null){
			request.queryParams(queryParameters);

		}
return request;
	}


	private static Response sendRequestSpecifications(String baseUri, RequestSpecification request, Method methodType, String path){

		String pathvalue = "";
		if (path != null){
			pathvalue = path;
		}
		else {
			pathvalue = baseUri;
		}

		request.log().all();

try {
	switch (methodType){
		case GET:
			response = (Response)request.get(pathvalue, new Object[0]);
			break;
		case PUT:
			response = (Response)request.put(pathvalue, new Object[0]);
			break;
		case POST:
			response = (Response)request.post(pathvalue, new Object[0]);
			break;
		case DELETE:
			response = (Response)request.delete(pathvalue, new Object[0]);
			break;
		case HEAD:
			response = (Response)request.head(pathvalue, new Object[0]);
			break;
		case OPTIONS:
			response = (Response)request.options(pathvalue, new Object[0]);
			break;
		case PATCH:
			response = (Response)request.patch(pathvalue, new Object[0]);
			break;
		default:
			log.error(methodType+ "Method not available for this api:"+ baseUri);
	}
}catch (Exception var8){
	try{
		switch (methodType){
			case GET:
				response = (Response)request.get(pathvalue,new Object[0]);
				break;
			case PUT:
				response = (Response)request.put(pathvalue, new Object[0]);
				break;
			case POST:
				response = (Response)request.post(pathvalue, new Object[0]);
				break;
			case DELETE:
				response = (Response)request.delete(pathvalue, new Object[0]);
				break;
			case HEAD:
				response = (Response)request.head(pathvalue, new Object[0]);
				break;
			case OPTIONS:
				response = (Response)request.options(pathvalue, new Object[0]);
				break;
			case PATCH:
				response = (Response)request.patch(pathvalue, new Object[0]);
				break;
			default:
				log.error(methodType+ "Method not available for this api:"+ baseUri);
		}
	}catch(Exception var7){
		log.error(methodType+ "Method request fails for api:"+ baseUri + "; Exception:"+ var7.getMessage());
	}

}
		return response;

	}

}
