package com.maps.project;

import org.testng.annotations.Test;

import com.pojomaps.project.Location;
import com.pojomaps.project.ParentMaps;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class SerializationExample {
	
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
		
		Response res = given().log().all().queryParam("key", "qaclick123")
		.relaxedHTTPSValidation()
		.header("Content-Type","application/json")
		.body(pm)
		.expect().defaultParser(Parser.JSON)
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response();
		
		String response = res.asString();
		System.out.println(response);
		
		
	}

}
