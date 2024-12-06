package stepdefinitions;

import com.utilities.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.asserts.SoftAssert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import pages.CommonApi;

import java.io.IOException;


public class GET_StepDefinitions {


	PatternData patternData;
	protected Logger log;
	 CommonApi commonApi  = new CommonApi();
	 LocalScenarioContext localScenarioContext = ContextLoader.getLocalScenarioContext();

public GET_StepDefinitions(){
	super();
	log = LogManager.getLogger(this.getClass());
	commonApi = new CommonApi();
	patternData = new PatternData();


}

SoftAssert softAssert = new SoftAssert();

@Given("build the request parameters for API")
public void build_the_request_parameters_for_api(DataTable requestParams) {
commonApi.setAPIRequestParameters(DataTableUtil.toMap(requestParams));
}


@Then("Trigger {string} request to {string} API and validate response code as {int} and Status description as {string}")
public void trigger_request_to_api_and_validate_response_code_as_and_status_description_as(String method, String api, int statusCode, String statusDescription) {
commonApi.triggerAPI(method, api, statusCode, statusDescription);

}

	@Then("Trigger {string} request to {string} API and validate response code as {int}")
	public void trigger_request_to_api_and_validate_response_code(String method, String api, int statusCode) {
		commonApi.triggerAPI(method, api, statusCode);

	}


	@Then("Validate the GET api data for {string}")
	public void validateTheGETApiDataFor(String tcName) throws IOException {
		softAssert.assertTrue(patternData.compareGetPatternJsonvalueswithResponse(tcName),"Json Payload is different for "+tcName);
		//softAssert.assertAll();}
}


	@Then("User Validate the POST api data for {string}")
	public void validateTheGETApiDataForPOST(String tcName) throws IOException {
		softAssert.assertTrue(patternData.compareGetPatternJsonvalueswithResponseforPOST(tcName),"Json Payload is different for "+tcName);
		//softAssert.assertAll();}
	}



	@Then("User Validate the PUT api data for {string}")
	public void userValidateThePUTApiDataFor(String tcName) {
		softAssert.assertTrue(patternData.compareGetPatternJsonvalueswithResponseforPUT(tcName), "Json Payload is different for " + tcName);
	}



	@Then("Trigger {string} request to {string} API and validate response code as {int} and status Title as {} and Error description as {} for {}")
	public void triggerRequestandValidatebadRequest(String method, String api,int statusCode, String statusTitle, String errorDescription,String tcName) {
    commonApi.triggerandvalidateBadrequest(method,api,statusCode,statusTitle,errorDescription,tcName);
	}


}