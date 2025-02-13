package coverPhotos.testcases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import com.pojo.CoverphotosRequestPOJO;
import com.pojo.CreateUserRequestPOJO;

import io.restassured.http.ContentType;

public class CreateCoverphotos {

	@Test
	public void verifyCreateCoverphotsWithValidDataWithJson() throws FileNotFoundException {
		
		// Load the external JSON file from the specified path
		
					File f = new File(System.getProperty("user.dir")+ "\\src\\test\\resources\\requestPayload\\createCoverphotosRequestbody.json");
					// Open a file input stream to read the content of the file
					FileInputStream fileInputStream = new FileInputStream(f);
					// Create a JSONTokener object that parses the JSON content from the file input stream
					JSONTokener jsonTokener = new JSONTokener(fileInputStream);
				    // Create a JSONObject from the JSONTokener, which reads the JSON content into a structured format
					JSONObject requestData = new JSONObject(jsonTokener);
					
		given()
		.when()
		.body(requestData.toString())
		.contentType(ContentType.JSON)
		.post("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos")
		.then().log().all()
		.assertThat()
		.body("id",Matchers.equalTo(732))
		.body("idBook",Matchers.equalTo(732))
		.body("url",Matchers.equalTo("https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 72&w=250&h=350"))
		.body("size()", greaterThan(0))
		.header("Content-Type", "application/json; charset=utf-8; v=1.0");
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
		.header("Content-Type", "application/json; charset=utf-8; v=1.0")
		.statusCode(200);

	}
	@Test
	public void verifyCreateCoverphotosUsingPOJOClass() {
		CoverphotosRequestPOJO reuestDataPOJO=new CoverphotosRequestPOJO();
		reuestDataPOJO.setId(458);
		reuestDataPOJO.setIdBook(458);
		reuestDataPOJO.setUrl("https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 72&w=250&h=350");
		given()
		.contentType(ContentType.JSON)
		.body(reuestDataPOJO)
		.when()
		.post("https://fakerestapi.azurewebsites.net/api/v1/Coverphotos")
		.then()
		.statusCode(200)
		.header("Content-Type", "application/json; charset=utf-8; v=1.0")
		.log().all();
	}
	
	@Test
	public void verifyCreateCoverphotosUsingHashMap() {
		HashMap<Object, Object> data=new HashMap<Object, Object>();
		data.put("id",789);
		data.put("idBook",789);
		data.put("url","https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 72&w=250&h=350");
		
		given()
		  .contentType(ContentType.JSON)
		  .body(data)
		.when()
		.post("https://fakerestapi.azurewebsites.net/api/v1/Coverphotos")
		.then()
		.statusCode(200)
		.header("Content-Type", "application/json; charset=utf-8; v=1.0")
		.log().all();
		
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
