package com.restassured.testcases;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateAuthor {
	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/api/v1";
	}

	@Test
	public void verifyCreateAuthorWithValidData() {
		given().when()
				.body("{\r\n" + "  \"id\": 11,\r\n" + "  \"idBook\": 100,\r\n" + "  \"firstName\": \"Book1\",\r\n"
						+ "  \"lastName\": \"Book2\"\r\n" + "}")
				.contentType(ContentType.JSON).post("/Authors").then().log().all().assertThat()
				.body("id", Matchers.equalTo(11)).body("idBook", Matchers.equalTo(100))
				.body("firstName", Matchers.equalTo("Book1")).body("size()", greaterThan(0));
	}

	@Test
	public void verifyCreateAuthorStatusCodeShould200() {
		given().when()
				.body("{\r\n" + "  \"id\": 11,\r\n" + "  \"idBook\": 100,\r\n" + "  \"firstName\": \"Book1\",\r\n"
						+ "  \"lastName\": \"Book2\"\r\n" + "}")
				.contentType(ContentType.JSON).post("/Authors").then().log().all().assertThat().statusCode(200);

	}

	@Test
	public void verifyShouldNotCreateAuthorwithoutBody() {
		given().when().contentType(ContentType.JSON).post("/Authors").then().log().all().assertThat().statusCode(400);

	}

	@Test
	public void verifyCreateAuthorWithEmptyId() {
		given().when()
				.body("{\r\n" + "  \"id\": ,\r\n" + "  \"idBook\": 100,\r\n" + "  \"firstName\": \"Book1\",\r\n"
						+ "  \"lastName\": \"Book2\"\r\n" + "}")
				.contentType(ContentType.JSON).post("/Authors").then().log().all().assertThat().statusCode(400);
	}

	@Test
	public void verifyCreateAuthorWithInvaliUrl() {
		given().when()
				.body("{\r\n" + "  \"id\": 10,\r\n" + "  \"idBook\": 100,\r\n" + "  \"firstName\": \"Book1\",\r\n"
						+ "  \"lastName\": \"Book2\"\r\n" + "}")
				.contentType(ContentType.JSON).post("/Authorsffgggg").then().log().all().assertThat().statusCode(404);
	}

	@Test
	public void verifyCreateAuthorWithInvaliformatOfID() {
		given().when()
				.body("{\r\n" + "  \"id\": <12345>,\r\n" + "  \"idBook\": 100,\r\n" + "  \"firstName\": \"Book1\",\r\n"
						+ "  \"lastName\": \"Book2\"\r\n" + "}")
				.contentType(ContentType.JSON).post("/Authors").then().log().all().assertThat().statusCode(400);
	}

	@Test
	public void VerifyCreateAuthorsWithInvaliformatOfIdWithNegative() {
		given().when()
				.body("{\r\n" + "        \"id\":-12345,\r\n" + "        \"idBook\": 12345,\r\n"
						+ "  \"firstName\": \"Book1\",\r\n" + "  \"lastName\": \"Book2\"\r\n" + "}")
				.contentType(ContentType.JSON).post("/Authors").then().assertThat().statusCode(200);// bug
	}

	@Test
	public void VerifyCreateAuthorWithNegativeIdBook() {
		given().when()
				.body("{\r\n" + "  \"id\": 12345,\r\n" + "  \"idBook\": -100,\r\n" + "  \"firstName\": \"Book1\",\r\n"
						+ "  \"lastName\": \"Book2\"\r\n" + "}")
				.contentType(ContentType.JSON).post("/Authors").then().log().all().assertThat().statusCode(200);// bug
	}

	@Test
	public void VerifyCreateAuthorWithInvalididfomatOfIdBook() {
		given().when()
				.body("{\r\n" + "  \"id\": 12345,\r\n" + "  \"idBook\": <100>,\r\n" + "  \"firstName\": \"Book1\",\r\n"
						+ "  \"lastName\": \"Book2\"\r\n" + "}")
				.contentType(ContentType.JSON).post("/Authors").then().assertThat().statusCode(400).log().all();
	}

	@Test
	public void VerifyCreateAuthorWithInvalidNonexistingidIdBook() {
		given().when()
				.body("{\r\n" + "  \"id\": 1237889965996232536,\r\n" + "  \"idBook\": 100,\r\n"
						+ "  \"firstName\": \"Book1\",\r\n" + "  \"lastName\": \"Book2\"\r\n" + "}")
				.contentType(ContentType.JSON).post("/Authors").then().statusCode(400).log().all();
	}

	@Test
	public void VerifyCreateAuthorWithEmptyIdBook() {
		given().when()
				.body("{\r\n" + "  \"id\": 12,\r\n" + "  \"idBook\": ,\r\n" + "  \"firstName\": \"Book1\",\r\n"
						+ "  \"lastName\": \"Book2\"\r\n" + "}")
				.contentType(ContentType.JSON).post("/Authors").then().assertThat().statusCode(400);
	}

	@Test
	public void VerifyCreateAuthorWithEmptyparamter() {
		given().when()
				.body("{\r\n" + "  \"id\": ,\r\n" + "  \"idBook\": ,\r\n" + "  \"firstName\": ,\r\n"
						+ "  \"lastName\": \r\n" + "}")
				.contentType(ContentType.JSON).post("/Authors").then().log().all().assertThat().statusCode(400);
	}

}
