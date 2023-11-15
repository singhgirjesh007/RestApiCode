package com.jira.restproject;

import org.testng.annotations.Test;

import com.rest.jsonpayload.JiraBody;
import com.rest.jsonpayload.ReusableMethod;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class CreateSessionAddTicket {
	
	@Test
	public void jiraapi_test() {
		
		//Login seession
				//SessionFilter api used for seesion , it will store complete session and
				//no need to paas auth key wiht this logic//
				//other way login and extratc authkey throgh Json path
		RestAssured.baseURI = "http://localhost:8080";
		SessionFilter session = new SessionFilter();
		given().log().all().header("Content-Type","application/json")
		.body(JiraBody.getSessionBody()).filter(session)
		.when().post("/rest/auth/1/session")
		.then().log().all().assertThat().statusCode(200);
		
		//Add comment 
		given().pathParam("issueid", "QAC-1").header("Content-Type" , "application/json")
		.body(JiraBody.addComment("Test Created with comment")).filter(session)
		.when().post("/rest/api/2/issue/{issueid}/comment")
		.then().log().all().statusCode(201);
		
		//Add attachment
		//multipart method is used for attachment
		//header in that case with Content-Type will be diffrent
		//header("Content-Type","multipart/form-data")
		//.multiPart("File", new File("E:\\Automation\\Maven Project Example\\RestProject\\src\\test\\java\\Jira.txt"))
		
		given().header("X-Atlassian-Token","no-check").filter(session).pathParam("issueid", "QAC-1")
		.header("Content-Type","multipart/form-data")
		.multiPart("File", new File("E:\\Automation\\Maven Project Example\\RestProject\\src\\test\\java\\Jira.txt"))
		.when().post("/rest/api/2/issue/{issueid}/attachments")
		.then().log().all().statusCode(200);
		
		//get issue - By default this will return whole response with all the fileds
		String sh3 =given().filter(session).pathParam("issueid", "QAC-1")
		.when().get("/rest/api/2/issue/{issueid}")
		.then().log().all().extract().response().asString(); 
		
		//Using query param as per api doc will limit the response -
		//like comment validation
		//jira provide fields query paramaetr to limit response filed to be 
		//extracted , So path parameter willl provide all filed for issue id and 
		// query parameter will sort/filer the response
		
		String sh4 =given().filter(session).pathParam("issueid", "QAC-1")
				.queryParam("fields", "comment")
		.when().get("/rest/api/2/issue/{issueid}")
		.then().log().all().extract().response().asString();
		
		JsonPath js1 =ReusableMethod.rawToJson(sh4);
		js1.get("fields.comment.comments");
		
		
		
	}

}
