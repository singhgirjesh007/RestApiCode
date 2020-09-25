package com.rest.RestProject;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rest.jsonpayload.DynamicJsonPayLoad;
import com.rest.jsonpayload.ReusableMethod;

import io.restassured.RestAssured;
import static  io.restassured.RestAssured.*;

public class LibraryApiExample {
	
	
	@Test
	public void addBook() {
		RestAssured.baseURI = "http://216.10.245.166";
		given()
		.body(DynamicJsonPayLoad.dynamicjson("bbc2", "2243")).when().put("Library/AddBook.php")
		.then().assertThat().statusCode(200);
		
	}
	
	@Test
	public void getDatafromprovider() {
		RestAssured.baseURI = "http://216.10.245.166";
		given()
		.body(DynamicJsonPayLoad.dynamicjson("bbc2", "2243")).when().put("Library/AddBook.php")
		.then().assertThat().statusCode(200);
		
		
	}
	
	
	@DataProvider(name ="BaseData")
	public Object[][] getData() {
		 return new Object[][] {{"gire","123"},{"ira","1234"}};
	}
	
	/**
	 * Rule for Data provider
	 * Put name of Data Provided
	 * Same name will pass into @Test(dataProvided ="BaseData")
	 * also @Test Methond should pass same number of argument dataprovider is using in Array or collection
	 */

}
