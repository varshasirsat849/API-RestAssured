package com.restassured.testcases;
import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class DeleteAuthor {
	@BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/api/v1";
    }

	@Test
	public void verifyDeleteAuthorWithValidData() {
		given().when()
		.delete("/Authors/10").then().log().all()
		.assertThat()
		.statusCode(200);
	}

	@Test
	public void VerifyDeleteAuthorWithInvaliUrl() {
		given().when()
		.delete("/Authorsrrrr/10").then().log().all()
		.assertThat().statusCode(404);
	}

	@Test
	public void VerifyDeleteAuthorWithInvaliformatOfID() {
		given().when()
		.delete("/Authors/<10>").then().log().all()
		.assertThat()
		.statusCode(400);
	}
	//bug 
	@Test
	public void VerifyDeleteAuthorWithInvaliformatOfIdWithNegative() {
		given().when()
		.delete("/Authors/-100").then().log().all()
		.assertThat()
		.statusCode(200);
	}

	@Test
	public void VerifyDeleteAuthorWithNegativeIdBook() {
		given().when()
		.body("{\r\n"
				+ "        \"id\": 1,\r\n"
				+ "        \"idBook\": -1233,\r\n"
				+ "        \"firstName\": \"First Name 1\",\r\n"
				+ "        \"lastName\": \"Last Name 1\"\r\n"
				+ "    }")
		.contentType(ContentType.JSON)
		.delete("Authors/10").then().log().all()
		.log().all()
		.assertThat()
		.statusCode(200);
	}


	@Test
	public void VerifyDeleteAuthorWithInvalidNonexistingidId() {
		given()
		.when()
		.delete("/Authors/10327889567678788")
		.then().statusCode(400).log().all();
	}

	@Test
	public void VerifyDeleteAuthorWithEmptyId() {
		given()
		.when()
		.delete("/Authors/ ").then().log().all()
		.assertThat().statusCode(405);
	}


	@Test
	public void verifyDeleteAuthorWithDeletedDuplicateId() {
		given()
		.when()
		.delete("Authors/10").then().log().all()
		.assertThat()
		.statusCode(200);
	}
	

}
