package com.rest.RestProject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import com.rest.jsonpayload.AddPlaceJsonBody;
import com.rest.jsonpayload.ReusableMethod;

public class RestAddPlace {

	public static void main(String args[]) throws Exception {

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		/**
		 * Basic Post - Code
		 */
		/*
		 * given().log().all() .queryParam("key", "qaclick123")
		 * .header("Content-Type","application/json")
		 * .body(AddPlaceJsonBody.addPlaceBody())
		 * .when().post("/maps/api/place/add/json")
		 * .then().log().all().assertThat().statusCode(200) .body("scope",
		 * equalTo("APP")) .header("Server", "Apache/2.4.18 (Ubuntu)");
		 */

		String body =given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(AddPlaceJsonBody.addPlaceBody())
				.when().post("/maps/api/place/add/json")
				.then().assertThat()
				.statusCode(200).body("scope", equalTo("APP")).header("Server", equalTo("Apache/2.4.18 (Ubuntu)"))
				.extract().response().asString(); 
		//Extracting Response as a String as we need to use Place id to update Address and use it Get to verify
		//E2E Test Case
		System.out.println(body);
		
		JsonPath js = new JsonPath(body);
		String place_id =js.getString("place_id");
		System.out.println(place_id);
		String newAddress = "70 Summer walk, USA";
		//Update Address with Put api
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").
		body("{\r\n" + 
				"\"place_id\":\""+place_id+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}")
		.when().put("maps/api/place/update/json")
		.then().assertThat().body("msg", equalTo("Address successfully updated"));
		Thread.sleep(5000);
		
		//Validate Address updated
		String response =given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id",place_id)
		.when().get("maps/api/place/get/json")
		.then().assertThat().statusCode(200).body("address",equalTo("70 Summer walk, USA")).extract().asString();
		
		//JsonPath js1 = new JsonPath(response);
		//Instead of above line use reusable method and keep rest assured code on this page for simplicity
		JsonPath js1 = ReusableMethod.rawToJson(response);
		String actualaddress =js1.getString("address");
		System.out.println(actualaddress);
		Assert.assertEquals(actualaddress, newAddress);

	}
}
