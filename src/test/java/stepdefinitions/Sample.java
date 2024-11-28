package stepdefinitions;


import com.utilities.DataTableUtil;
import com.utilities.EnvPropertyManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import pages.CommonApi;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class Sample {
    EnvPropertyManager envPropertyManager;
    CommonApi commonApi;



    @Then("^user enter username and password$")
    public void user_enter_username_and_password() {
        System.out.println("entered step 2");
    }

    @Then("user clicks login button")
    public void user_clicks_login_button() {
        System.out.println("entered step 3");
    }

    @Given("User url for website")
    public void userUrlForWebsite() {
        System.out.println("entered step 1");
    }

    @Given("build the request parameters for APII")
    public void build_the_request_parameters_for_apii(DataTable requestParams) {
        System.out.println("entered step 4");
        commonApi.setAPIRequestParameters(DataTableUtil.toMap(requestParams));
    }

    Response response;

    @Given("the valid endpoint to fetch users")
    public void setupEndpoint() {
        RestAssured.baseURI = "https://fakestoreapi.com/";
        RestAssured.basePath = "/products";
    }


    @When("the request is send to server with page number as “{int}”")
    public void sendtheRequest(int Id) {
        response = given().
                queryParam("id", Id).
                when().
                get().
                then().
                contentType(ContentType.JSON).
                extract().response();
        System.out.println(response);
    }

    @Then("validate the response of first user record having email as “{}”")
    public void validatetheUserData(String title) {
        String responseBody = response.body().asString();
        JsonPath js = new JsonPath(responseBody);

        String Title = js.getString("title[0]");
        Assert.assertEquals(Title, title);
    }

}
