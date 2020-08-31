package StepDefinition;

import org.apache.commons.lang3.RandomStringUtils;
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

public class PostStepDef {
	private static final String BASE_URL = "https://gorest.co.in/public-api";
	private static Response response;
	RequestSpecification httpRequest;
	int statusCode;
	String responseBody;
	JSONObject requestParams=new JSONObject();
	JsonPath jsonPathEvaluator;
	
	
	@Given("I am a authorized User")
	public void i_am_a_authorized_user() {
		RestAssured.baseURI = BASE_URL;
		httpRequest=RestAssured.given();
		httpRequest.auth().oauth2("243b030a622a146bf10d1fbfeae4e032cd813b50f8aa6ce074dc46504b201516");
	}

	@When("I enter required details to create a record")
	public void i_enter_required_details_to_create_a_record() {
		int length = 10;
		boolean useLetters = true;
		boolean useNumbers = true;
		String Randomemail = RandomStringUtils.random(length, useLetters, useNumbers);
		String emailNew = Randomemail +"@" + Randomemail + ".com";
		 requestParams.put("name", "seshu");
		 requestParams.put("email", emailNew);
		 requestParams.put("gender", "Male");
		 requestParams.put("status", "Active");
		 
		 httpRequest.header("Content-Type","application/json;charset=utf-8");
		  
		 httpRequest.body(requestParams.toJSONString());
	}
	@When("Send POST HTTP request")
	public void send_post_http_request() {
		response=httpRequest.request(Method.POST,"/users");
	}
	@Then("Verfiy the HTTP Response Code")
	public void verfiy_the_http_response_code() {
		responseBody=response.getBody().asString();
		System.out.println("Response Body is:" +responseBody);
		jsonPathEvaluator = response.jsonPath();
		statusCode = jsonPathEvaluator.get("code");
		Assert.assertEquals(statusCode, 201);
	}
	
	@When("I do not enter details required to create a record")
	public void i_do_not_enter_details_required_to_create_a_record() {
		requestParams.put("name", "seshu");
		requestParams.put("email", "adfaf@seshu.com");
		requestParams.put("status", "Active");
		httpRequest.header("Content-Type","application/json;charset=utf-8");
		  
		httpRequest.body(requestParams.toJSONString());
	}
	
	@Then("Verify HTTP Responce")
	public void verify_http_responce() {
		responseBody=response.getBody().asString();
		System.out.println("Response Body is:" +responseBody);
		jsonPathEvaluator = response.jsonPath();
		statusCode = jsonPathEvaluator.get("code");
		Assert.assertEquals(statusCode, 422);
	}
	
	@When("I enter details with existing email id")
	public void i_enter_details_with_existing_email_id() {
		requestParams.put("name", "seshu");
		requestParams.put("email", "adfaf@gamghjghjksfafd.com");
		requestParams.put("gender", "Male");
		requestParams.put("status", "Active");
		httpRequest.header("Content-Type","application/json;charset=utf-8");
  
		httpRequest.body(requestParams.toJSONString());
	}
	
	@Then("Verify Responce Code")
	public void verify_responce_code() {
		responseBody=response.getBody().asString();
		System.out.println("Response Body is:" +responseBody);
		jsonPathEvaluator = response.jsonPath();
		statusCode = jsonPathEvaluator.get("code");
		Assert.assertEquals(statusCode, 422);
	}

}
