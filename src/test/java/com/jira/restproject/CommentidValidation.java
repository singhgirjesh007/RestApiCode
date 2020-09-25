package com.jira.restproject;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.rest.jsonpayload.JiraBody;
import com.rest.jsonpayload.ReusableMethod;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class CommentidValidation {

	@Test
	public void jiraapicomment_test() {
		
		//Login seession
		//SessionFilter api used for seesion 
		RestAssured.baseURI = "http://localhost:8080";
		SessionFilter session = new SessionFilter();
		given().header("Content-Type","application/json")
		.body(JiraBody.getSessionBody()).filter(session)
		.when().post("/rest/auth/1/session")
		.then().assertThat().statusCode(200);
		
		//Add comment 
		String response1 =given().pathParam("issueid", "QAC-1").header("Content-Type" , "application/json")
		.body(JiraBody.addComment("Validation final comment for QAC1")).filter(session)
		.when().post("/rest/api/2/issue/{issueid}/comment")
		.then().statusCode(201).extract().response().asString();
			JsonPath js2 =ReusableMethod.rawToJson(response1);
			String actual_id =js2.getString("id");
			System.out.println(actual_id);
		
		
		//get issue - By default this will return whole response with all the fileds
		/*String sh3 =given().filter(session).pathParam("issueid", "QAC-1")
		.when().get("/rest/api/2/issue/{issueid}")
		.then().log().all().extract().response().asString(); */
		
		//Using query param as per api doc will limit the response - like comment validation
		String sh3 =given().filter(session).pathParam("issueid", "QAC-1")
				.queryParam("fields", "comment")
		.when().get("/rest/api/2/issue/{issueid}")
		.then().extract().response().asString();
		
		JsonPath js1 =ReusableMethod.rawToJson(sh3);
		int size= js1.getInt("fields.comment.comments.size()");
		System.out.println(size);
		
		for (int i=0;i<size;i++) {
			String sh4 =js1.get("fields.comment.comments["+i+"].id").toString();
			if(sh4.equalsIgnoreCase(actual_id))
				System.out.println(js1.get("fields.comment.comments["+i+"].body").toString());
		}
		
		
		
	}
}
