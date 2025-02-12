package author.testcases;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class UpdateAuthor {
	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/api/v1";
	}

	@Test
	public void verifyUpdateCoverphotsWithValidData() {
		given().when()
				.body("{\r\n" + "        \"id\": 1,\r\n" + "        \"idBook\": 100,\r\n"
						+ "        \"firstName\": \"First Name 19\",\r\n" + "        \"lastName\": \"Last Name 19\"\r\n"
						+ "    }")
				.contentType(ContentType.JSON).put("/Authors/19").then().log().all().assertThat()
				.body("id", Matchers.equalTo(1)).body("idBook", Matchers.equalTo(100))
				.body("firstName", Matchers.equalTo("First Name 19"))
				.body("lastName", Matchers.equalTo("Last Name 19"));
	}

	@Test
	public void verifyUpdateAuthorStatusCode() {
		given().when()
				.body("{\r\n" + "        \"id\": 1,\r\n" + "        \"idBook\": 100,\r\n"
						+ "        \"firstName\": \"First Name 19\",\r\n" + "        \"lastName\": \"Last Name 19\"\r\n"
						+ "    }")
				.contentType(ContentType.JSON).put("/Authors/19").then().log().all().assertThat().statusCode(200);
	}

	@Test
	public void VerifyUpdateAuthorWithEmptyId() {
		given().when()
				.body("{\r\n" + "        \"id\": ,\r\n" + "        \"idBook\": 100,\r\n"
						+ "        \"firstName\": \"First Name 19\",\r\n" + "        \"lastName\": \"Last Name 19\"\r\n"
						+ "    }")
				.contentType(ContentType.JSON).put("/Authors/19").then().log().all().assertThat().statusCode(400);
	}

	// bug
	@Test
	public void VerifyUpdateAuthorWithInvaliFirstName() {
		given().when()
				.body("{\r\n" + "        \"id\": 1,\r\n" + "        \"idBook\": 100,\r\n"
						+ "        \"firstName\": \"--First Name 19\",\r\n"
						+ "        \"lastName\": \"Last Name 19\"\r\n" + "    }")
				.contentType(ContentType.JSON).put("/Authors/19").then().log().all().assertThat().statusCode(200);
	}

	@Test
	public void VerifyUpdateAuthorWithInvaliformatOfID() {
		given().when()
				.body("{\r\n" + "        \"id\": <1>,\r\n" + "        \"idBook\": 100,\r\n"
						+ "        \"firstName\": \"--First Name 19\",\r\n"
						+ "        \"lastName\": \"Last Name 19\"\r\n" + "    }")
				.contentType(ContentType.JSON).put("/Authors/19").then().log().all().assertThat().statusCode(400);
	}

	@Test
	public void VerifyUpdateAuthorWithInvaliformatOfIdWithNegative() {
		given().when()
				.body("{\r\n" + "        \"id\": --10,\r\n" + "        \"idBook\": 100,\r\n"
						+ "        \"firstName\": \"--First Name 19\",\r\n"
						+ "        \"lastName\": \"Last Name 19\"\r\n" + "    }")
				.contentType(ContentType.JSON).put("/Authors/19").then().log().all().assertThat().statusCode(400);
	}

	@Test
	public void VerifyUpdateAuthorWithNegativeIdBook() {
		given().when()
				.body("{\r\n" + "        \"id\": 19,\r\n" + "        \"idBook\": -100,\r\n"
						+ "        \"firstName\": \"First Name 19\",\r\n" + "        \"lastName\": \"Last Name 19\"\r\n"
						+ "    }")
				.contentType(ContentType.JSON).put("/Authors/19").then().log().all().assertThat().statusCode(200);
	}

	@Test
	public void VerifyUpdateAuthorWithInvalidIDfomatOfIdBook() {
		given().when()
				.body("{\r\n" + "        \"id\": 19,\r\n" + "        \"idBook\": <100>,\r\n"
						+ "        \"firstName\": \"First Name 19\",\r\n" + "        \"lastName\": \"Last Name 19\"\r\n"
						+ "    }")
				.contentType(ContentType.JSON).put("/Authors/19").then().log().all().assertThat().statusCode(400).log()
				.all();
	}

	@Test
	public void VerifyUpdateAuthorWithInvalidNonexistingidIdBook() {
		given().when()
				.body("{\r\n" + "        \"id\": 196789029834647,\r\n" + "        \"idBook\": 100,\r\n"
						+ "        \"firstName\": \"First Name 19\",\r\n" + "        \"lastName\": \"Last Name 19\"\r\n"
						+ "    }")
				.contentType(ContentType.JSON).put("/Authors/19").then().log().all().statusCode(400).log().all();
	}

	@Test
	public void VerifyUpdateAuthorWithEmptyIdBook() {
		given().when()
				.body("{\r\n" + "        \"id\": 196789029834647,\r\n" + "        \"idBook\": ,\r\n"
						+ "        \"firstName\": \"First Name 19\",\r\n" + "        \"lastName\": \"Last Name 19\"\r\n"
						+ "    }")
				.contentType(ContentType.JSON).put("/Authors/19").then().log().all().assertThat().statusCode(400);
	}

	@Test
	public void VerifyUpdateAuthorWithEmptyparamter() {
		given().when().body("{\r\n" + "        \"id\": ,\r\n" + "        \"idBook\": ,\r\n" + "    }")
				.contentType(ContentType.JSON).put("/Authors/19").then().log().all().assertThat().statusCode(400);
	}

	@Test
	public void verifyUpdateCoverphotsWithDuplicateValidData() {
		given().when()
				.body("{\r\n" + "        \"id\": 190,\r\n" + "        \"idBook\": 190,\r\n"
						+ "        \"firstName\": \"First Name 19\",\r\n" + "        \"lastName\": \"Last Name 19\"\r\n"
						+ "    }")
				.contentType(ContentType.JSON).put("/Authors/19").then().log().all().assertThat().statusCode(200)
				.body("size()", greaterThan(0));
	}
}
