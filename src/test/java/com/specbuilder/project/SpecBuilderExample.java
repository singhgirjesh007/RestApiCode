package com.specbuilder.project;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.pojomaps.project.Location;
import com.pojomaps.project.ParentMaps;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilderExample {

	@Test
	public void serializeTest() {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		ParentMaps pm= new ParentMaps();
		pm.setAccuracy(50);
		pm.setAddress("front line tes");
		pm.setLanguage("french");
		pm.setName("Girjesh");
		pm.setPhone_number("4574747474");
		pm.setWebsite("www.google.com");
		
		//pm.setLocation expecting Location Object
		Location l= new Location();
		l.setLat(-32.577557);
		l.setLng(32.67766);
		pm.setLocation(l);
		
		//pm.setTypes also expect List object
		List<String> ls = new ArrayList<String>();
		ls.add("my tes");
		ls.add("my test2");
		pm.setTypes(ls);
	
		//In Body just need to paas Pojo claas Object
		
		RequestSpecBuilder req = new RequestSpecBuilder();
		RequestSpecification requestBuild = req.setBaseUri("https://rahulshettyacademy.com")
		.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		
		ResponseSpecBuilder res = new ResponseSpecBuilder();
		ResponseSpecification responseBuild = res.expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		
		//Code breal into Request
		RequestSpecification request = given().spec(requestBuild)
		.body(pm);
	
		ValidatableResponse response = request.when().post("/maps/api/place/add/json")
		.then().spec(responseBuild);
		
		Response output = response.extract().response();
		System.out.println(output);
		System.out.println(output.asString());
		
		
		
	}
}
