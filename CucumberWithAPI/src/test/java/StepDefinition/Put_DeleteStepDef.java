package StepDefinition;

import org.json.simple.JSONObject;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Put_DeleteStepDef {
	private static final String BASE_URL = "https://gorest.co.in/public-api";
	private static Response response;
	RequestSpecification httpRequest;
	int statusCode;
	String responseBody;
	JSONObject requestParams=new JSONObject();
	JsonPath jsonPathEvaluator;

@Given("I am authorized User")
public void i_am_authorized_user() {
	RestAssured.baseURI = BASE_URL;
	httpRequest=RestAssured.given();
	httpRequest.auth().oauth2("243b030a622a146bf10d1fbfeae4e032cd813b50f8aa6ce074dc46504b201516");
}

@When("I enter the details to update")
public void i_enter_the_details_to_update() {
	requestParams.put("name", "Agnimitra Sinha DM");
	 requestParams.put("email", "md_agnimitra_sinha@emmerich.info");
	 requestParams.put("status", "Active");
	 
	 httpRequest.header("Content-Type","application/json;charset=utf-8");
	  
	 httpRequest.body(requestParams.toJSONString());
}
@When("Send PUT HTTP request")
public void send_put_http_request() {
	response=httpRequest.request(Method.PUT,"/users/59");
}
@Then("Verify the responce code")
public void verify_the_responce_code() {
	responseBody=response.getBody().asString();
	System.out.println("Response Body is:" +responseBody);
	jsonPathEvaluator = response.jsonPath();
	statusCode = jsonPathEvaluator.get("code");
	Assert.assertEquals(statusCode, 200);
}

@When("I enter details to update")
public void i_enter_details_to_update() {
	 requestParams.put("name", "Siva sai");
	  requestParams.put("email", "adfaf@gamgphjksfafd.com");
	  requestParams.put("status", "Active");
	 
	 httpRequest.header("Content-Type","application/json;charset=utf-8");
	  
	 httpRequest.body(requestParams.toJSONString());
}
@When("Send HTTP PUT request")
public void send_http_put_request() {
	response=httpRequest.request(Method.PUT,"/users/1640");
}

@Then("Verify the HTTP responce code")
public void verify_the_http_responce_code() {
	responseBody=response.getBody().asString();
	System.out.println("Response Body is:" +responseBody);
	jsonPathEvaluator = response.jsonPath();
	statusCode = jsonPathEvaluator.get("code");
	Assert.assertEquals(statusCode, 200);
}

@When("Send DELETE HTTP request")
public void send_delete_http_request() {
	response=httpRequest.request(Method.DELETE,"/users/1601");
}
@Then("Verify responce code")
public void verify_responce_code() {
	responseBody=response.getBody().asString();
	System.out.println("Response Body is:" +responseBody);
	jsonPathEvaluator = response.jsonPath();
	statusCode = jsonPathEvaluator.get("code");
	Assert.assertEquals(statusCode, 204);
}

}
