package com.restassured.testcases;

import static io.restassured.RestAssured.*;
import org.hamcrest.Matchers;
import org.jetbrains.annotations.TestOnly;
import org.testng.annotations.Test;

import com.requestObjectModel.GetUsersRequestSpec;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.testng.Assert.assertEquals;

public class GetUsers {

	GetUsersRequestSpec spec=new GetUsersRequestSpec();
	@Test
	public static void verifyResponseCodeForGetAllUsersWithValidMethodNameAndEndpoint() {
		given()
		.when()
		.get("https://fakerestapi.azurewebsites.net/api/v1/Users")
		.then()
		.statusCode(200)
		.log().all();
	}

	@Test
	public static void verifyResponseCodeForGetAllUsersWithInvalidEndpoint() {
		given()
		.when()
		.get("https://fakerestapi.azurewebsites.net/api/v1/User")
		.then()
		.statusCode(404)
		.log().all();
	}

	@Test
	public static void verifyResponseCodeForGetAllUsersWithInvalidMethodName() {
		given()
		.when()
		.post("https://fakerestapi.azurewebsites.net/api/v1/User")
		.then()
		.statusCode(404)
		.log().all();
	}

	@Test
	public static void verifySingleObjectFromResponseForGetAllUsersWithValidMethodNameAndEndpoint() {
		given().when().get("https://fakerestapi.azurewebsites.net/api/v1/Users").then().statusCode(200)
				.body("[0].id", Matchers.equalTo(1))
				.body("[0].userName", Matchers.equalTo("User 1"))
				.body("[0].password", Matchers.equalTo("Password1"))
				.log().all();
	}

	@Test
	public static void verifyEntireResponseForGetAllUsersWithValidMethodNameAndEndpoint() {
		String expectedEntireResponse = "[\n" + "  {\n" + "    \"id\": 1,\n" + "    \"userName\": \"User 1\",\n"
				+ "    \"password\": \"Password1\"\n" + "  },\n" + "  {\n" + "    \"id\": 2,\n"
				+ "    \"userName\": \"User 2\",\n" + "    \"password\": \"Password2\"\n" + "  },\n" + "  {\n"
				+ "    \"id\": 3,\n" + "    \"userName\": \"User 3\",\n" + "    \"password\": \"Password3\"\n"
				+ "  },\n" + "  {\n" + "    \"id\": 4,\n" + "    \"userName\": \"User 4\",\n"
				+ "    \"password\": \"Password4\"\n" + "  },\n" + "  {\n" + "    \"id\": 5,\n"
				+ "    \"userName\": \"User 5\",\n" + "    \"password\": \"Password5\"\n" + "  },\n" + "  {\n"
				+ "    \"id\": 6,\n" + "    \"userName\": \"User 6\",\n" + "    \"password\": \"Password6\"\n"
				+ "  },\n" + "  {\n" + "    \"id\": 7,\n" + "    \"userName\": \"User 7\",\n"
				+ "    \"password\": \"Password7\"\n" + "  },\n" + "  {\n" + "    \"id\": 8,\n"
				+ "    \"userName\": \"User 8\",\n" + "    \"password\": \"Password8\"\n" + "  },\n" + "  {\n"
				+ "    \"id\": 9,\n" + "    \"userName\": \"User 9\",\n" + "    \"password\": \"Password9\"\n"
				+ "  },\n" + "  {\n" + "    \"id\": 10,\n" + "    \"userName\": \"User 10\",\n"
				+ "    \"password\": \"Password10\"\n" + "  }\n" + "]\n";

		String actualResponse = given()
				.when()
				.get("https://fakerestapi.azurewebsites.net/api/v1/Users").then()
				.statusCode(200)
				.extract().asString();
		expectedEntireResponse = removeWhitespace(expectedEntireResponse);
		actualResponse = removeWhitespace(actualResponse);
		assertEquals(expectedEntireResponse, actualResponse);
	}

	@Test
	void verifyGetAllUsersUsingPathAndQueryParam() {
		given()
		.pathParam("myPath", "Users")
		.when()
		.get("https://fakerestapi.azurewebsites.net/api/v1/{myPath}")
		.then()
		.statusCode(200)
		.log().all();
	}

	public static String removeWhitespace(String str) {
		return str.replaceAll("\\s+", ""); // Removes all whitespace characters (spaces, newlines, tabs)
	}

	@Test
	void verifyGetSpecificUsersUsingPathParam() {

		String expectedResponseSinlgeObject = "{\"id\": 1, \"userName\": \"User 1\", \"password\": \"Password1\"}";
		String actualResponse = given()
				.pathParam("myPath", "Users")
				.pathParam("userid", "1").when()
				.get("https://fakerestapi.azurewebsites.net/api/v1/{myPath}/{userid}")
				.then()
				.statusCode(200)
				.extract()
				.asString();
		expectedResponseSinlgeObject = removeWhitespace(expectedResponseSinlgeObject);
		actualResponse = removeWhitespace(actualResponse);
		assertEquals(expectedResponseSinlgeObject, actualResponse);
	}
	
	@Test
	public void VerifyGetUsersWithInvalidUserId() {
		given()
		.when()
		.get("https://fakerestapi.azurewebsites.net/api/v1/Users/1236")
		.then()
		.log().all()
		.assertThat()
		.statusCode(404);
	}
	@Test
	public void VerifyUsersWithInvaliformatOfUserId() {
		given()
		.when()
		.get("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos/<44123>")
		.then()
		.log().all()
		.statusCode(400);
	}
	
	@Test
	public void VerifyUsersWithNegativeUserId() {
		given()
		.when()
		.get("https://fakerestapi.azurewebsites.net/api/v1/Users/-12355")
		.then()
		.statusCode(404);
	}
	
	@Test
	public void VerifyUsersHavingContentTypeJSON() {
		given()
		.when()
		.get("https://fakerestapi.azurewebsites.net/api/v1/Users/1")
		.then()
		.statusCode(200)
		.contentType(ContentType.JSON);
	}
	@Test
	public void verifyGetAllUsersWithRequestSpecification(){
		RequestSpecification request = RestAssured.given()
                .baseUri("https://fakerestapi.azurewebsites.net/api/v1")
                .pathParam("userid", "1");

      Response response = request.get("/Users/{userid}");
      System.out.println(response.statusCode());
	}
	
	@Test
	public void verifyGetUserUsingReqSpec(){
		given(spec.getUsers())
		.get()
		.then().log().all();
	}
}
