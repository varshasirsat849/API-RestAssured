package author.testcases;
import static io.restassured.RestAssured.given;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.hamcrest.Matchers.*;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.requestObjectModel.AuthorRequestSpec;
import com.requestObjectModel.UserRequestSpec;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetAuthor {

	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/api/v1";
	}

	
	@Test
	public void verifyGetAuthorUsingReqSpec(){
		AuthorRequestSpec spec=new AuthorRequestSpec();
		given(spec.getAuthor())
		.get("/10")
		.then()
		.statusCode(200)
		.log().body();
	}
	

	@Test
	public void verifyHeadercontentUsingReqSpec() {
		AuthorRequestSpec spec=new AuthorRequestSpec();
		given(spec.getAuthor())
		.then()
		.log()
		.headers()
	    .header("Content-Type", "application/json; charset=utf-8; v=1.0")
        .header("Transfer-Encoding", "chunked")
	    .header("Server", "Kestrel");
	}
	
	
	@Test
	public void verifyTheAPIReturn200StatusCode() {
		given()
		.when()
		.get("/Authors")
		.then()
		.statusCode(200)
		.log()
		.status();

	}

	@Test
	public void verifyHeadercontent() {
		given().when().get("/Authors")
		.then()
		.log()
		.headers()
	    .header("Content-Type", "application/json; charset=utf-8; v=1.0")
        .header("Transfer-Encoding", "chunked")
	    .header("Server", "Kestrel");
	}

	@Test
	public static void verifyInvalidEndpointReturns404() {
		given()
		.when()
		.get("/Authors888")
		.then()
		.statusCode(404)
		.log().all();
	}

	@Test
	public void verifyTheAPIReturnsListOfAuthorsSuccessfully() {
		given()
		.when()
		.get("/Authors")
		.then().statusCode(200)
		.body("size()", greaterThan(0))
		.log().body();
	}

	@Test
	public void verifyCorrectIdFirstnameFromResponse() {
		// to print Response on console
		Response response = given()
				.when()
				.get("/Authors")
				.then().
				 statusCode(200)
				.body("[0].firstName", Matchers.equalTo("First Name 1")).extract().response();
		// Extract firstName using JsonPath
		JsonPath jsonPath = response.jsonPath();
		String firstName = jsonPath.getString("[0].firstName");
		System.out.println("First Name: " + firstName);
		assertTrue(firstName.contains("First Name 1"));

	}

	@Test
	public void verifyResponseBodyByID() {
		Response response = given().when().get("/Authors").then().statusCode(200).body("[1].id", Matchers.equalTo(2))
				.body("[1].lastName", Matchers.equalTo("Last Name 2")).extract().response();
		JsonPath jsonPath = response.jsonPath();
		String authorWithId = jsonPath.getString("[0].id");
		System.out.println("Author with ID: " + authorWithId);
		assertTrue(authorWithId.contains("1"));
	}

	@Test
	public void verifyUserCanFetchDataByUsingAuthorByID() {
		Response response = given()

				.when().get("/Authors/19").then().statusCode(200).extract().response();
		response.prettyPrint();
		String actualResponse = response.asString();
		assertTrue(actualResponse.contains("First Name 19"));
	}

	@Test
	public void verifyFirstAuthorHasExpectedFields() {
		Response response = given().when().get("/Authors").then().statusCode(200).extract().response();
		int firstAuthorId = response.jsonPath().getInt("[0].id");
		System.out.println(firstAuthorId);
		String firstName = response.jsonPath().getString("[0].firstName");
		System.out.println(firstName);
		String lastName = response.jsonPath().getString("[0].lastName");
		System.out.println(lastName);
		assertTrue(firstAuthorId > 0, "Author ID should be positive");
		assertNotNull(firstName, "First name should not be null");
		assertNotNull(lastName, "Last name should not be null");
	}

	@Test
	public void verifyResponseForInvalidHTTPMethod() {
		given().when().post("/Authors").then().statusCode(415).log().status();

	}

	@Test
	public void verifyResponseForInvalidBookID() {
		given().when().get("/Authors/authors/books/-90").then().statusCode(200).body(equalTo("[]")).log().all();

	}

	@Test
	public void verifyStringIdBookReturn404() {
		given().when().get("/Authors/authors/books/BOOK").then().statusCode(400).log().body();

	}

}
