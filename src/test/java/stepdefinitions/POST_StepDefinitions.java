package stepdefinitions;

import com.utilities.PatternData;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.asserts.SoftAssert;
import pages.CommonApi;

public class POST_StepDefinitions {



    PatternData patternData;
    protected Logger log;
    CommonApi commonApi  = new CommonApi();

    public POST_StepDefinitions(){
        super();
        log = LogManager.getLogger(this.getClass());
        commonApi = new CommonApi();
        patternData = new PatternData();


    }

    SoftAssert softAssert = new SoftAssert();


    @Then("User Trigger {string} request to {string} API and validate response code as {int} for {string}")
    public void trigger_request_to_api_and_validate_response_code_as_and_status_description_as(String method, String api, int statusCode,String tcName) {
        commonApi.triggerAPI_POST(method, api,statusCode,tcName);

    }}
