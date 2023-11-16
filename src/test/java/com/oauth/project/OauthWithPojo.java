package com.oauth.project;

import static io.restassured.RestAssured.given;

import java.beans.AppletInitializer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pojoOAuth.project.Api;
import com.pojoOAuth.project.ParentCourseExample;
import com.pojoOAuth.project.WebAutomation;
import com.rest.jsonpayload.ReusableMethod;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class OauthWithPojo {
	
	@Test
	public void oAuthtest2() {
		
		//To generate url
		//https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss
		
		String url = "https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2F4QFoaEwjI8OT7jrXIyD6XV9WiQ2uJSzczIO9tfQpo05OCLHQ6CPadzvBqyPTPSAKqiRNQftJfud5aQwfk7KV7As&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none#";
		String split_string =url.split("&code=")[1];
		String code =split_string.split("&scope")[0];
		System.out.println(code);
		
		
		
		//.urlEncodingEnabled(false) - if the string has some special character , rest assured framewrok automatically
		//convert it into numerical value so we need to use this method with flase value
		//code = 4%2F4QGsJSGG-jdb5CwCQ9SZwOy7XKDJTCNDBSdh_926ARi-eFlMbrYMDK1y1tSNHofVo8YlzBLqc-P5_XA3a5g-fa8
		//% is sepcial character
		
		String accessTockenResponse =given().urlEncodingEnabled(false)
				 .queryParams("code",code)    

                 .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")

                      .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")

                      .queryParams("grant_type", "authorization_code")

                      .queryParams("state", "verifyfjdss")

                      .queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")

                   // .queryParam("scope", "email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email")

                     

                      .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")

                      .when().log().all()

                      .post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		JsonPath js =ReusableMethod.rawToJson(accessTockenResponse);
		String access_token =js.getString("access_token");
		System.out.println(access_token);
		
		//.expect().defaultParser(Parser.JSON) - When Response expected as JSON only use this method as header does not have 
		//content type - application/json
		//So that POJO Classes uses that JSON format in their classes and we can use that vlaue
		
		//Major file to support Pojo - jackson and gson for JSON file
		//By just providing Java class - whole response will chnage and we can play with response with java logic
		// .expect().defaultParser(Parser.JSON) - To parse in JSON contect as per header
		
		ParentCourseExample pojo_object =given().queryParam("access_token", access_token).expect().defaultParser(Parser.JSON).when()
		.get("https://rahulshettyacademy.com/getCourse.php").as(ParentCourseExample.class);
		
		System.out.println(pojo_object.getInstructor());
		
		List<Api> apiCourse = pojo_object.getCourses().getApi();
		for (int i= 0; i<apiCourse.size();i++) {
			
			if (apiCourse.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webserive Testing"))
			System.out.println(apiCourse.get(i).getPrice());
		}
		
		//Compare two List and assert
		String[] arr = {"Selenium course","Appium corse"};
		//Convert Array into arrayList or lis
		List<String> bl = Arrays.asList(arr);
		
		//Add all tile in the list
		ArrayList<String> al = new ArrayList<String>();
		
		
		List<WebAutomation> gwa = pojo_object.getCourses().getWebAutomation();
		for(int j=0;j<gwa.size();j++) {
			al.add(gwa.get(j).getCourseTitle());
		}
		
		//Comparision of two List and asserstion
		Assert.assertTrue(al.equals(bl));
		
		
		
	}


}
