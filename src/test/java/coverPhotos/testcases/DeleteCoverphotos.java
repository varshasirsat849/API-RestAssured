package coverPhotos.testcases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

@Test
public class DeleteCoverphotos {
	

	@Test
	public void verifyDeleteCoverphotsWithValidData() {
		given().when()
		.delete("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos/21").then().log().all()
		.assertThat()
		.statusCode(200);
	}

	@Test
	public void VerifyDeleteCoverphotosWithInvaliUrl() {
		given().when()
		.delete("https://fakerestapi.azurewebsites.net/api/v1/CoverPhouiytuygioyltos/11").then().log()
		.all()
		.assertThat().statusCode(404);
	}

	@Test
	public void VerifyDeleteCoverphotosWithInvaliformatOfID() {
		given().when()
		.delete("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos/<33>").then().log().all()
		.assertThat()
		.statusCode(400);
	}

	
	//bug for negative testcase
	@Test
	public void VerifyDeleteCoverphotosWithInvaliformatOfIdWithNegative() {
		given().when()
		.delete("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos/-77").then()
		.assertThat()
		.statusCode(200);
	}

	@Test
	public void VerifyDeleteCoverphotosWithNegativeIdBook() {
		given().when()
		.body("{\r\n"
				+ "        \"id\":12345,\r\n"
				+ "        \"idBook\": -12345,\r\n"
				+ "        \"url\": \"https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 72&w=250&h=350\"\r\n"
				+ "    }")
		.contentType(ContentType.JSON)
		.delete("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos/44").then()
		.log().all()
		.assertThat()
		.statusCode(200);
	}


	@Test
	public void VerifyDeleteCoverphotosWithInvalidNonexistingidId() {
		given()
		.when()
		.delete("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos/327889567678788")
		.then().statusCode(400).log().all();
	}

	@Test
	public void VerifyDeleteCoverphotosWithEmptyId() {
		given()
		.when()
		.delete("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos/ ").then().log().all()
		.assertThat().statusCode(405);
	}


	@Test
	public void verifyDeleteCoverphotsWithDeletedDuplicateId() {
		given()
		.when()
		.delete("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos/21").then().log().all()
		.assertThat()
		.statusCode(200);
	}


}
