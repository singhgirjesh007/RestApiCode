package com.rest.jsonpayload;

public class AddPlaceJsonBody {
	
	public static String addPlaceBody() {
		
		return "{\r\n" + 
				"  \"location\": {\r\n" + 
				"    \"lat\": -38.383494,\r\n" + 
				"    \"lng\": 33.427362\r\n" + 
				"  },\r\n" + 
				"  \"accuracy\": 50,\r\n" + 
				"  \"name\": \"Frontline house\",\r\n" + 
				"  \"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
				"  \"address\": \"29, side layout, cohen 09\",\r\n" + 
				"  \"types\": [\r\n" + 
				"    \"shoe park\",\r\n" + 
				"    \"shop\"\r\n" + 
				"  ],\r\n" + 
				"  \"website\": \"http://google.com\",\r\n" + 
				"  \"language\": \"French-IN\"\r\n" + 
				"}" ;
	}
	
	public static String getCourseData() {
		return "{\r\n" + 
				"  \"dashboard\": {\r\n" + 
				"    \"purchaseamount\": 450,\r\n" + 
				"    \"website\": \"www.click.academy.com\"\r\n" + 
				"  },\r\n" + 
				"  \"course\": [\r\n" + 
				"    {\r\n" + 
				"      \"title\": \"test1\",\r\n" + 
				"      \"price\": 40,\r\n" + 
				"      \"copies\": 5\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"title\": \"test2\",\r\n" + 
				"      \"price\": 30,\r\n" + 
				"      \"copies\": 6\r\n" + 
				"    }\r\n" + 
				"  ]\r\n" + 
				"}";
		
	}

}
