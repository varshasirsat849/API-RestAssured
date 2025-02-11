
package com.restassured.testcases;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.response.ValidatableResponseOptions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;


	public class GetCoverphotos {
		
		@Test
		public void verifyGetCoverphotsWithValidData() {
			given().when().get("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos").then().log().all()
			.assertThat()
			.body("[0].id",Matchers.equalTo(1))
			.body("[0].idBook",Matchers.equalTo(1))
			.body("[0].url",Matchers.equalTo("https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 1&w=250&h=350"))
			.statusCode(200)
			.body("size()", greaterThan(0));
		}
		
		@Test
		public void verifyGetCoverPhotosContentType() {
			given().when().get("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos").then().log().all()
					.contentType("application/json");
		}
		
		@Test
		public void verifyGetCoverPhotosStatusCode() {
			given().when().get("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos").then()
			.log()
			.all()
			.assertThat()
			.statusCode(200);
			
		}
		
		@Test
		public void verifyGetCoverphotosById() {
			given().when().get("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos/1").then().log()
			.all()
			.assertThat()
			.body("id",Matchers.equalTo(1))
			.body("idBook",Matchers.equalTo(1))
			.body("url",Matchers.equalTo("https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 1&w=250&h=350"))
			.statusCode(200);
		}
		
		@Test
		public void VerifyGetCoverphotosWithNonExistingId() {
			given().when().get("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos/1236").then().log().all().assertThat()
			.statusCode(404);
		}
		@Test
		public void VerifyGetCoverphotosWithEmptyId() {
			given().when().get("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos/").then().log().all().assertThat()
			.statusCode(200);
		}
		
		
		@Test
		public void VerifyGetCoverphotosWithInvaliUrl() {
			given().when().get("https://fakerestapi.azurewebsites.net/api/v1/CoverPhouiytuygioyltos/1236").then().log().all()
			.assertThat().statusCode(404);
		}
		
		@Test
		public void VerifyGetCoverphotosWithInvaliformatOfID() {
			given().when().get("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos/<123>").then().log().all()
			.statusCode(400);
		}
		@Test
		public void VerifyGetCoverphotosWithInvaliformatOfIDInNegative() {
			given().when().get("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos/-123").then()
			.statusCode(404);
		}
		
		@Test
		public void VerifyGetCoverphotosWithIdBook() {
			given().when().get("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos/books/covers/1").then()
			.log().all()
			.assertThat()
			.body("[0].id",Matchers.equalTo(1))
			.body("[0].idBook",Matchers.equalTo(1))
			.body("[0].url",Matchers.equalTo("https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 1&w=250&h=350"))
			.statusCode(200).body("size()", greaterThan(0));		
		}
		
		@Test
		public void VerifyGetCoverphotosWithInvalidIdBook() {
			given().when().get("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos/books/covers/<123>").then()
			.statusCode(400).body("size()", greaterThan(0)).log().all();		
		}
		
		@Test
		public void VerifyGetCoverphotosWithNegativeidIdBook() {
			given().when().get("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos/books/covers/-123773473778844877").then()
			.statusCode(400).log().all();
		}
		//this is bug actually for negative case = 400 response not for -1234
		
		@Test
		public void VerifyGetCoverphotosWithNonexistingidIdBook() {
			given().when().get("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos/books/covers/1237889965996232536").then()
			.statusCode(400).log().all();
		}
		
		@Test
		public void VerifyGetCoverphotosWithEmptyIdBook() {
			given().when().get("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos/books/covers/ ").then().log().all()
			.assertThat().statusCode(404);
		}
		
		@Test
		public void VerifyGetCoverphotosWithInvalidHttp() {
			given().when().post("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos/books/covers/1").then().log().all()
			.assertThat().statusCode(405);//method not allowed
		}


	
		
		
		
		

		

	}
	


