package com.restassured.testcases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class CreateCoverphotos {

	@Test
	public void verifyCreateCoverphotsWithValidData() {
		given().when()
		.body("{\r\n"
				+ "        \"id\": 732,\r\n"
				+ "        \"idBook\": 732,\r\n"
				+ "        \"url\": \"https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 72&w=250&h=350\"\r\n"
				+ "    }")
		.contentType(ContentType.JSON)
		.post("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos").then().log().all()
		.assertThat()
		.body("id",Matchers.equalTo(732))
		.body("idBook",Matchers.equalTo(732))
		.body("url",Matchers.equalTo("https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 72&w=250&h=350"))
		.body("size()", greaterThan(0));
	}


	@Test
	public void verifyCreateCoverPhotosStatusCode() {
		given().when()
		.body("{\r\n"
				+ "        \"id\": 7532,\r\n"
				+ "        \"idBook\": 7532,\r\n"
				+ "        \"url\": \"https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 72&w=250&h=350\"\r\n"
				+ "    }")
		.contentType(ContentType.JSON)
		.post("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos").then().log().all()
		.assertThat()
		.statusCode(200);

	}


	@Test
	public void VerifyCreateCoverphotosWithEmptyId() {
		given().when()
		.body("{\r\n"
				+ "        \"id\":  ,\r\n"
				+ "        \"idBook\": 7532,\r\n"
				+ "        \"url\": \"https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 72&w=250&h=350\"\r\n"
				+ "    }")
		.contentType(ContentType.JSON)
		.post("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos").then().log().all()
		.assertThat()
		.statusCode(400);
	}

	@Test
	public void VerifyCreateCoverphotosWithInvaliUrl() {
		given().when()
		.body("{\r\n"
				+ "        \"id\":7532,\r\n"
				+ "        \"idBook\": 7532,\r\n"
				+ "        \"url\": \"https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 72&w=250&h=350\"\r\n"
				+ "    }")
		.contentType(ContentType.JSON)
		.post("https://fakerestapi.azurewebsites.net/api/v1/CoverPhouiytuygioyltos").then().log()
		.all()
		.assertThat().statusCode(404);
	}

	@Test
	public void VerifyCreateCoverphotosWithInvaliformatOfID() {
		given().when()
		.body("{\r\n"
				+ "        \"id\":<12345>,\r\n"
				+ "        \"idBook\": 12345,\r\n"
				+ "        \"url\": \"https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 72&w=250&h=350\"\r\n"
				+ "    }")
		.contentType(ContentType.JSON)
		.post("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos").then().log().all()
		.assertThat()
		.statusCode(400);
	}

	
	//bug for negative testcase
	@Test
	public void VerifyCreateCoverphotosWithInvaliformatOfIdWithNegative() {
		given().when()
		.body("{\r\n"
				+ "        \"id\":-12345,\r\n"
				+ "        \"idBook\": 12345,\r\n"
				+ "        \"url\": \"https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 72&w=250&h=350\"\r\n"
				+ "    }")
		.contentType(ContentType.JSON)
		.post("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos").then()
		.assertThat()
		.statusCode(200);
	}

	@Test
	public void VerifyCreateCoverphotosWithNegativeIdBook() {
		given().when()
		.body("{\r\n"
				+ "        \"id\":12345,\r\n"
				+ "        \"idBook\": -12345,\r\n"
				+ "        \"url\": \"https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 72&w=250&h=350\"\r\n"
				+ "    }")
		.contentType(ContentType.JSON)
		.post("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos/books/covers").then()
		.log().all()
		.assertThat()
		.statusCode(404);
	}

	@Test
	public void VerifyCreateCoverphotosWithInvalididfomatOfIdBook() {
		given().when()
		.body("{\r\n"
				+ "        \"id\":12345,\r\n"
				+ "        \"idBook\":<12345>,\r\n"
				+ "        \"url\": \"https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 72&w=250&h=350\"\r\n"
				+ "    }")
		.contentType(ContentType.JSON)
		.post("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos/books/covers")
		.then()
		.assertThat()
		.statusCode(404).log().all();
	}


	@Test
	public void VerifyCreateCoverphotosWithInvalidNonexistingidIdBook() {
		given().when()
		.body("{\r\n"
				+ "        \"id\":1237889965996232536,\r\n"
				+ "        \"idBook\":<12345>,\r\n"
				+ "        \"url\": \"https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 72&w=250&h=350\"\r\n"
				+ "    }")
		.contentType(ContentType.JSON)
		.post("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos/books/covers")
		.then().statusCode(404).log().all();
	}

	@Test
	public void VerifyCreateCoverphotosWithEmptyIdBook() {
		given().when()
		.body("{\r\n"
				+ "        \"id\":12345,\r\n"
				+ "        \"idBook\": ,\r\n"
				+ "        \"url\": \"https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 72&w=250&h=350\"\r\n"
				+ "    }")
		.contentType(ContentType.JSON)
		.get("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos/books/covers").then().log().all()
		.assertThat().statusCode(404);
	}
	
	@Test
	public void VerifyCreateCoverphotosWithEmptyparamter() {
		given().when()
		.body("{\r\n"
				+ "        \"id\": ,\r\n"
				+ "        \"idBook\": ,\r\n"
				+ "        \"url\":  "
				+ "    }")
		.contentType(ContentType.JSON)
		.post("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos/books/covers").then().log().all()
		.assertThat().statusCode(404);
	}
	
	@Test
	public void VerifyCreateCoverphotosWithEmptyUrl() {
		given().when()
		.body("{\r\n"
				+ "        \"id\":12345,\r\n"
				+ "        \"idBook\": ,\r\n"
				+ "        \"url\":  "
				+ "    }")
		.contentType(ContentType.JSON)
		.post("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos/books/covers").then().log().all()
		.assertThat().statusCode(404);
	}
	
	

}
