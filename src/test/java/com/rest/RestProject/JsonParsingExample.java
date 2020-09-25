package com.rest.RestProject;

import org.testng.annotations.Test;

import com.rest.jsonpayload.AddPlaceJsonBody;
import com.rest.jsonpayload.ReusableMethod;

import io.restassured.path.json.JsonPath;

public class JsonParsingExample {
	
	
	@Test
	public void getCourseSet() {
		
		JsonPath js = ReusableMethod.rawToJson(AddPlaceJsonBody.getCourseData());
		int course_number =js.getInt("course.size()");
		int sum =0;
		
		//Print Course title and price
		for(int i=0 ;i<course_number;i++) {
			System.out.println(js.get("course["+i+"].title"));
			System.out.println(js.get("course["+i+"].price").toString());
			
		}
		
		for(int i=0 ;i<course_number;i++) {
			String value = js.get("course["+i+"].title");
			if (value.equalsIgnoreCase("test2"))
			System.out.println(js.get("course["+i+"].price"));
		}
		
		for(int i=0 ;i<course_number;i++) {
			
			int price =js.get("course["+i+"].price");
			int copy = js.get("course["+i+"].copies");
			int totalPrice = price*copy;
			sum = sum + totalPrice;
			
		}
		
		System.out.println("Total Price --->"+sum+"");
		
	}

}
