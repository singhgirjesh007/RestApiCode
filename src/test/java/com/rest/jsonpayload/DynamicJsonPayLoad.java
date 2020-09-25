package com.rest.jsonpayload;

public class DynamicJsonPayLoad {
	
	public static String dynamicjson(String isbn , String aisle) {
		
		//Two Values passing through variables
		String sh = "{\r\n" + 
				"\r\n" + 
				"\"name\":\"Learn Appium Automation with Java\",\r\n" + 
				"\"isbn\":\""+isbn+"\",\r\n" + 
				"\"aisle\":\""+aisle+"\",\r\n" + 
				"\"author\":\"John foe\"\r\n" + 
				"}\r\n" + 
				"";
		return sh;
	}

}
