package StepDefinition;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.LocationMaps;
import pojo.MapAddApi;
import resources.ApiResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDef extends Utils {
	RequestSpecification request;
	ResponseSpecification resSpec;
	Response response;
	TestDataBuild dataBuild = new TestDataBuild();
	static String place_id;

	@Given("^add place payload$")
	public void add_place_payload() throws IOException {

		request = given().spec(requestSpecification()).body(dataBuild.addPlacePayload());
	}

	@Given("add place payload {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException {
		request = given().log().all().spec(requestSpecification())
				.body(dataBuild.addPlacePayload(name, language, address));
	}

	@When("^user calls \"([^\"]*)\" with post http request$")
	public void user_calls_something_with_post_http_request(String resource) {
		ApiResources apiResource = ApiResources.valueOf(resource);
		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		response = reqSpec.when().post(apiResource.getResource()).then().log().all().spec(resSpec).extract().response();
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String apinameResource, String method) {
		ApiResources apiResource = ApiResources.valueOf(apinameResource);
		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if  (method.equalsIgnoreCase("POST")) {
			response = request.when().post(apiResource.getResource());
			System.out.println("respnseeeeeeee"+response.asString());
		}
		else if (method.equalsIgnoreCase("GET")) {
			response = request.when().get(apiResource.getResource());
		}
	}

	@Then("^the api is success with status code 200$")
	public void the_api_is_success_with_status_code_200() {
		System.out.println("=================" + response.getStatusCode());
		assertEquals(response.getStatusCode(), 200);
	}

	@And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	public void something_in_response_body_is_something(String keyValue, String expectedValue) {
		assertEquals(getJsonPath(response, "status"), expectedValue);

	}

	@Then("verify plcaeId created maps to {string} using {string}")
	public void verify_plcae_id_created_maps_to_using(String name, String apiname) throws IOException {
		place_id = getJsonPath(response, "place_id");
		request = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(apiname, "GET");
		assertEquals(getJsonPath(response, "name"), name);
	}

	@Given("delete Place payload")
	public void delete_place_payload() throws IOException {
		System.out.println("-----------"+dataBuild.deleteApiPayload(place_id));
		request = given().spec(requestSpecification()).body(dataBuild.deleteApiPayload(place_id));
		System.out.println("requstttttttttttt"+request.toString());
	}
}
