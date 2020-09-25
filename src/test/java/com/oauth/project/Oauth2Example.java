package com.oauth.project;

import org.testng.annotations.Test;

import com.rest.jsonpayload.ReusableMethod;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import static io.restassured.RestAssured.*;

public class Oauth2Example {
	
	@Test
	public void oAuthtest() {
		
		//To generate url
		//https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss
		
		String url = "https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2F4QFbFhxe8qVpUs3J__iBBwIS7vgMswlrvWwoySZ6aGV7iyOfqixc9FWL49fd_fB_Ws8KxQ2LagtJoBeOtw8lWOc&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none#";
		String split_string =url.split("&code=")[1];
		String code =split_string.split("&scope")[0];
		System.out.println(code);
		
		//.urlEncodingEnabled(false) - if the string has some special character , rest assured framewrok automatically
		//convert it into numerical value so we need to use this method with flase value
		//code = 4%2F4QGsJSGG-jdb5CwCQ9SZwOy7XKDJTCNDBSdh_926ARi-eFlMbrYMDK1y1tSNHofVo8YlzBLqc-P5_XA3a5g-fa8
		
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
		System.out.println("Access Token -->"+accessTockenResponse);
		JsonPath js =ReusableMethod.rawToJson(accessTockenResponse);
		String access_token =js.getString("access_token");
		System.out.println(access_token);
		
		Response res = given().queryParam("access_token", access_token).when()
		.get("https://rahulshettyacademy.com/getCourse.php").then().log().all().assertThat().statusCode(200).extract().response();
		
		
		ResponseBody resBody = res.getBody();
		
		
		
		String response =given().queryParam("access_token", access_token).when()
		.get("https://rahulshettyacademy.com/getCourse.php").then().log().all().assertThat().statusCode(200).extract().response()
		.asString();
		
		System.out.println(response);
		
		
	}

}
