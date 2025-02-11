package com.restassured.testcases;

import static io.restassured.RestAssured.*;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GetUsers {

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
		String expectedResponseSinlgeObject = "[\n" + "  {\n" + "    \"id\": 1,\n" + "    \"userName\": \"User 1\",\n"
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
		expectedResponseSinlgeObject = removeWhitespace(expectedResponseSinlgeObject);
		actualResponse = removeWhitespace(actualResponse);
		assertEquals(expectedResponseSinlgeObject, actualResponse);
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
}
