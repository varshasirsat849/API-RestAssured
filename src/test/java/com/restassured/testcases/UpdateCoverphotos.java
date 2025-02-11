package com.restassured.testcases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

@Test
public class UpdateCoverphotos {
	
	@Test
	public void verifyUpdateCoverphotsWithValidData() {
		given().when()
		.body("{\r\n"
				+ "        \"id\": 732,\r\n"
				+ "        \"idBook\": 732,\r\n"
				+ "        \"url\": \"https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 72&w=250&h=350\"\r\n"
				+ "    }")
		.contentType(ContentType.JSON)
		.put("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos/21").then().log().all()
		.assertThat()
		.body("id",Matchers.equalTo(732))
		.body("idBook",Matchers.equalTo(732))
		.body("url",Matchers.equalTo("https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 72&w=250&h=350"))
		.body("size()", greaterThan(0));
	}


	@Test
	public void verifyUpdateCoverPhotosStatusCode() {
		given().when()
		.body("{\r\n"
				+ "        \"id\": 7532,\r\n"
				+ "        \"idBook\": 7532,\r\n"
				+ "        \"url\": \"https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 72&w=250&h=350\"\r\n"
				+ "    }")
		.contentType(ContentType.JSON)
		.put("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos/22").then().log().all()
		.assertThat()
		.statusCode(200);

	}


	@Test
	public void VerifyUpdateCoverphotosWithEmptyId() {
		given().when()
		.body("{\r\n"
				+ "        \"id\":  ,\r\n"
				+ "        \"idBook\": 7532,\r\n"
				+ "        \"url\": \"https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 72&w=250&h=350\"\r\n"
				+ "    }")
		.contentType(ContentType.JSON)
		.put("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos/12").then().log().all()
		.assertThat()
		.statusCode(400);
	}

	@Test
	public void VerifyUpdateCoverphotosWithInvaliUrl() {
		given().when()
		.body("{\r\n"
				+ "        \"id\":7532,\r\n"
				+ "        \"idBook\": 7532,\r\n"
				+ "        \"url\": \"https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 72&w=250&h=350\"\r\n"
				+ "    }")
		.contentType(ContentType.JSON)
		.put("https://fakerestapi.azurewebsites.net/api/v1/CoverPhouiytuygioyltos/11").then().log()
		.all()
		.assertThat().statusCode(404);
	}

	@Test
	public void VerifyUpdateCoverphotosWithInvaliformatOfID() {
		given().when()
		.body("{\r\n"
				+ "        \"id\":<12345>,\r\n"
				+ "        \"idBook\": 12345,\r\n"
				+ "        \"url\": \"https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 72&w=250&h=350\"\r\n"
				+ "    }")
		.contentType(ContentType.JSON)
		.put("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos/33").then().log().all()
		.assertThat()
		.statusCode(400);
	}

	
	//bug for negative testcase
	@Test
	public void VerifyUpdateCoverphotosWithInvaliformatOfIdWithNegative() {
		given().when()
		.body("{\r\n"
				+ "        \"id\":-12345,\r\n"
				+ "        \"idBook\": 12345,\r\n"
				+ "        \"url\": \"https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 72&w=250&h=350\"\r\n"
				+ "    }")
		.contentType(ContentType.JSON)
		.put("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos/77").then()
		.assertThat()
		.statusCode(200);
	}

	@Test
	public void VerifyUpdateCoverphotosWithNegativeIdBook() {
		given().when()
		.body("{\r\n"
				+ "        \"id\":12345,\r\n"
				+ "        \"idBook\": -12345,\r\n"
				+ "        \"url\": \"https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 72&w=250&h=350\"\r\n"
				+ "    }")
		.contentType(ContentType.JSON)
		.put("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos/44").then()
		.log().all()
		.assertThat()
		.statusCode(200);
	}

	@Test
	public void VerifyUpdateCoverphotosWithInvalididfomatOfIdBook() {
		given().when()
		.body("{\r\n"
				+ "        \"id\":12345,\r\n"
				+ "        \"idBook\":<12345>,\r\n"
				+ "        \"url\": \"https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 72&w=250&h=350\"\r\n"
				+ "    }")
		.contentType(ContentType.JSON)
		.put("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos/66")
		.then()
		.assertThat()
		.statusCode(400).log().all();
	}


	@Test
	public void VerifyUpdateCoverphotosWithInvalidNonexistingidIdBook() {
		given().when()
		.body("{\r\n"
				+ "        \"id\":1237889965996232536,\r\n"
				+ "        \"idBook\":<12345>,\r\n"
				+ "        \"url\": \"https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 72&w=250&h=350\"\r\n"
				+ "    }")
		.contentType(ContentType.JSON)
		.put("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos/32")
		.then().statusCode(400).log().all();
	}

	@Test
	public void VerifyUpdateCoverphotosWithEmptyIdBook() {
		given().when()
		.body("{\r\n"
				+ "        \"id\":12345,\r\n"
				+ "        \"idBook\": ,\r\n"
				+ "        \"url\": \"https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 72&w=250&h=350\"\r\n"
				+ "    }")
		.contentType(ContentType.JSON)
		.put("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos/66").then().log().all()
		.assertThat().statusCode(400);
	}
	
	@Test
	public void VerifyUpdateCoverphotosWithEmptyparamter() {
		given().when()
		.body("{\r\n"
				+ "        \"id\": ,\r\n"
				+ "        \"idBook\": ,\r\n"
				+ "        \"url\":  "
				+ "    }")
		.contentType(ContentType.JSON)
		.put("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos/67").then().log().all()
		.assertThat().statusCode(400);
	}
	
	@Test
	public void VerifyUpdateCoverphotosWithEmptyUrl() {
		given().when()
		.body("{\r\n"
				+ "        \"id\":12345,\r\n"
				+ "        \"idBook\": ,\r\n"
				+ "        \"url\":  "
				+ "    }")
		.contentType(ContentType.JSON)
		.put("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos/99").then().log().all()
		.assertThat().statusCode(400);
	}
	
	@Test
	public void verifyUpdateCoverphotsWithDuplicateValidData() {
		given().when()
		.body("{\r\n"
				+ "        \"id\": 732,\r\n"
				+ "        \"idBook\": 732,\r\n"
				+ "        \"url\": \"https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 72&w=250&h=350\"\r\n"
				+ "    }")
		.contentType(ContentType.JSON)
		.put("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos/21").then().log().all()
		.assertThat()
		.statusCode(200).body("size()", greaterThan(0));
	}



}
