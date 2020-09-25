package com.rest.RestProject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RestApiExample {

	@Test
	public void restAssured() {
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://maps.googleapis.com";
		given().
			param("location","-33.8670522,151.1957362").
			param("radius","500").
			param("key","AIzaSyDvizYCIfF7JMyld-SHetVQ3Ty7ip8V8yg").
			//header("","")
			//cookies("","")
			//body("","")
		when().
			get("/maps/api/place/nearbysearch/json").
			
		then().
			assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
			body("results[0].place_id",equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and()
			.header("Server", "scaffolding on HTTPServer2");
		

	}

}
