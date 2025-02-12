package com.restassured.testcases;

import static io.restassured.RestAssured.*;

import java.util.HashMap;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.requestObjectModel.CreateUserRequestPOJO;

import io.restassured.http.ContentType;

public class CreateUser {

	@Test
	public void verifyCreateUserWithValidUserId() {
		given()
		.when()
		.body("{\n"
				+ "  \"id\": 13,\n"
				+ "  \"userName\": \"newUser1\",\n"
				+ "  \"password\": \"password1\"\n"
				+ "}")
		.contentType(ContentType.JSON)
		.post("https://fakerestapi.azurewebsites.net/api/v1/Users")
		.then()
		.statusCode(200);
	}
	@Test
	public void verifyCreateUserResponseDataWithValidRequest() {
		given()
		.when()
		.body("{\n"
				+ "  \"id\": 13,\n"
				+ "  \"userName\": \"newUser1\",\n"
				+ "  \"password\": \"password1\"\n"
				+ "}")
		.contentType(ContentType.JSON)
		.post("https://fakerestapi.azurewebsites.net/api/v1/Users")
		.then()
		.statusCode(200)
		.assertThat()
		.body("id",Matchers.equalTo(13))
		.body("userName",Matchers.equalTo("newUser1"))
		.body("password",Matchers.equalTo("password1"));
		
	}
	@Test
	public void verifyCreateUserUsingHashMap() {
		HashMap<Object, Object> data=new HashMap<Object, Object>();
		data.put("id", 1);
		data.put("userName", "newUser2");
		data.put("password", "password2");
		
		given()
		  .contentType(ContentType.JSON)
		  .body(data)
		.when()
		.post("https://fakerestapi.azurewebsites.net/api/v1/Users")
		.then()
		.statusCode(200)
		.header("Content-Type", "application/json; charset=utf-8; v=1.0")
		.log().all();
		
	}
	@Test
	public void verifyCreateUserUsingPOJOClass() {
		CreateUserRequestPOJO reuestDataPOJO=new CreateUserRequestPOJO();
		reuestDataPOJO.setUserid(14);
		reuestDataPOJO.setUsername("username3");
		reuestDataPOJO.setPassword("password3");
		given()
		  .contentType(ContentType.JSON)
		  .body(reuestDataPOJO)
		.when()
		.post("https://fakerestapi.azurewebsites.net/api/v1/Users")
		.then()
		.statusCode(200)
		.header("Content-Type", "application/json; charset=utf-8; v=1.0")
		.log().all();
	}
	
	@Test
	public void VerifyCreateUserWithEmptyUserId() {
		given().when()
		.body("{\n"
				+ "  \"id\": ,\n"
				+ "  \"userName\": \"string11\",\n"
				+ "  \"password\": \"string22\"\n"
				+ "}")
		.contentType(ContentType.JSON)
		.post("https://fakerestapi.azurewebsites.net/api/v1/Users")
		.then()
		.assertThat()
		.statusCode(400);
	}
	
	@Test
	public void VerifyCreateUsersWithInvaliUrl() {
		given().when()
		.body("{\n"
				+ "  \"id\": 0,\n"
				+ "  \"userName\": \"string\",\n"
				+ "  \"password\": \"string\"\n"
				+ "}")
		.contentType(ContentType.JSON)
		.post("https://fakerestapi.azurewebsites.net/api/v1/Userss").then().log()
		.all()
		.assertThat().statusCode(404);
	}
	@Test
	public void VerifyCreateUserWithInvlaidRequestFormat() {
		given().when()
		.body("{\n"
				+ "  \"id\": <1222>,\n"
				+ "  \"userName\": \"<string11>\",\n"
				+ "  \"password\": \"string22\"\n"
				+ "}")
		.contentType(ContentType.JSON)
		.post("https://fakerestapi.azurewebsites.net/api/v1/Users")
		.then()
		.assertThat()
		.statusCode(400);
	}
	@Test
	public void VerifyCreateUserWithNegativeUserId() {
		given().when()
		.body("{\n"
				+ "  \"id\": -11,\n"
				+ "  \"userName\": \"string11\",\n"
				+ "  \"password\": \"string22\"\n"
				+ "}")
		.contentType(ContentType.JSON)
		.post("https://fakerestapi.azurewebsites.net/api/v1/Users")
		.then()
		.assertThat()
		.statusCode(200);
	}
	
	
}
