package author.testcases;
import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.greaterThan;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.requestObjectModel.AuthorRequestSpec;
import com.requestObjectModel.CreateAuthorRequestByPOJO;
import com.requestObjectModel.CreateUserRequestPOJO;
import com.requestObjectModel.UserRequestSpec;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateAuthor {
	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/api/v1";
	}
	
	@Test
    public void createAuthourUsingExtenalJsonFile() throws Exception {
		// Load the external JSON file from the specified path
		
		File f = new File(System.getProperty("user.dir")+ "\\src\\test\\resources\\requestPayload\\createAuthorRequestbody.json");
		// Open a file input stream to read the content of the file
		FileInputStream fileInputStream = new FileInputStream(f);
		// Create a JSONTokener object that parses the JSON content from the file input stream
		JSONTokener jsonTokener = new JSONTokener(fileInputStream);
	    // Create a JSONObject from the JSONTokener, which reads the JSON content into a structured format
		JSONObject requestData = new JSONObject(jsonTokener);

		given()
        .contentType(ContentType.JSON)
        .body(requestData.toString()) //data in json object convert to data to string format
        .when()
        .post("/Authors")
        .then()
        .log().all()
        .assertThat()
        .statusCode(200); 
}
	
	@Test
    public void createAuthorByUsingDynamicJson() {
        // Create JSON Request Body Dynamically | it provides built-in methods for adding, modifying, and retrieving JSON data.
        JSONObject data = new JSONObject();
        data.put("id", 101);
        data.put("idBook", 200);
        data.put("firstName", "John");
        data.put("lastName", "Doe");
        given()
            .contentType(ContentType.JSON)
            .body(data.toString())  // Convert JSONObject to String
        .when()
            .post("/Authors")
        .then()
            .log().body()
            .assertThat()
            .statusCode(200); 
    }
   
	@Test
	public void CreateAuthorUsingPOJOClass() {
		CreateAuthorRequestByPOJO reuestDataPOJO=new CreateAuthorRequestByPOJO();
		reuestDataPOJO.setId(14);
		reuestDataPOJO.setIdBook("100");
		reuestDataPOJO.setFirstName("MyBOOK");
		reuestDataPOJO.setLastName("MindSet");
		given()
		  .contentType(ContentType.JSON)
		  .body(reuestDataPOJO)
		.when()
		.post("/Authors")
		.then()
		.statusCode(200)
		.header("Content-Type", "application/json; charset=utf-8; v=1.0")
		.log().all();
	}
	
	@Test
	public void createAuthorUsingRequestSpec() {
		AuthorRequestSpec spec=new AuthorRequestSpec();
		Response response = given()
                .spec(spec.createAuthor()) 
                .contentType(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(200)
                .log().body()
                .extract().response();
		 assertEquals(200, response.getStatusCode(), "Expected status code is 200");
	     assertNotNull(response.getBody().asString(), "Response body should not be null");
	     assertTrue(response.getBody().asString().contains("John"), "Response should contain 'John'");
	}
	
	@Test
	public void createAuthorDynamicallyUsingRequestSpec() {
		AuthorRequestSpec spec=new AuthorRequestSpec();
		Response response = given()
                .spec(spec.createAuthorDyanmically(12, 100, "API", "Book")) 
                .contentType(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(200)
                .log().body()
                .extract().response();
		 assertEquals(200, response.getStatusCode(), "Expected status code is 200");
	     assertNotNull(response.getBody().asString(), "Response body should not be null");
	     assertTrue(response.getBody().asString().contains("API"), "Response should contain 'John'");
	}
	
	
	@Test
	public void verifyCreateAuthorWithValidData() {
		given().when()
				.body("{\r\n" + "  \"id\": 11,\r\n" + "  \"idBook\": 100,\r\n" + "  \"firstName\": \"Book1\",\r\n"
						+ "  \"lastName\": \"Book2\"\r\n" + "}")
				.contentType(ContentType.JSON)
				.post("/Authors")
				.then()
				.log()
				.all()
				.assertThat()
				.body("id", Matchers.equalTo(11))
				.body("idBook", Matchers.equalTo(100))
				.body("firstName", Matchers.equalTo("Book1"))
				.body("size()", greaterThan(0));
	}

	@Test
	public void verifyCreateAuthorStatusCodeShould200() {
		given().when()
				.body("{\r\n" + "  \"id\": 11,\r\n" + "  \"idBook\": 100,\r\n" + "  \"firstName\": \"Book1\",\r\n"
						+ "  \"lastName\": \"Book2\"\r\n" + "}")
				.contentType(ContentType.JSON).post("/Authors")
				.then()
				.log()
				.all()
				.assertThat()
				.statusCode(200);

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
				.contentType(ContentType.JSON)
				.post("/Authorsffgggg")
				.then()
				.log()
				.all()
				.assertThat()
				.statusCode(404);
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
