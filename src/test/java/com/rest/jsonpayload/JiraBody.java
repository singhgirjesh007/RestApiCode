package com.rest.jsonpayload;

public class JiraBody {
	
	public static String getSessionBody() {
		
		String sh = "{\r\n" + 
				"\"username\":\"singhgirjesh007\",\r\n" + 
				"\"password\":\"Cimple@007\"\r\n" + 
				"}";
		return sh;
	}
	
	public static String addComment(String add_comment) {
		
		String sh1 = "{\r\n" + 
				"    \"body\": \""+add_comment+".\",\r\n" + 
				"    \"visibility\": {\r\n" + 
				"        \"type\": \"role\",\r\n" + 
				"        \"value\": \"Administrators\"\r\n" + 
				"    }\r\n" + 
				"}";
		return sh1;
	}

}
