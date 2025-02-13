
package coverPhotos.testcases;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.response.ValidatableResponseOptions;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import com.requestObjectModel.CoverphotosRequestSpec;


	public class GetCoverphotos {
		
		 CoverphotosRequestSpec spec=new CoverphotosRequestSpec(); 
		
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
		
		
		//Using RequserSpecification
		@Test
		public void VerifyGetCoverphotosWihReqSpec() {
			given(spec.getCoverphotos())
			.get()
			.then().log().all();
		}
		
		@Test
		public void verifyGetAllCoverphotosWithRequestSpecification(){//using chatgpt
			RequestSpecification request = RestAssured.given()
	                .baseUri("https://fakerestapi.azurewebsites.net/api/v1")
	                .pathParam("coverphotoid", "1");

	      Response response = request.get("/Coverphotos/{coverphotoid}");
	      System.out.println(response.statusCode());
		}
		
		public static String removeWhitespace(String str) {
			return str.replaceAll("\\s+", ""); // Removes all whitespace characters (spaces, newlines, tabs)
		}
		
		@Test
		void verifyGetSpecificUsersUsingPathParam() {

			String expectedResponseSinlgeObject = "{\"id\": 1, \"idBook\":1,\"url\": \"https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 1&w=250&h=350\"}";
			String actualResponse = given()
					.pathParam("myPath", "Coverphotos")
					.pathParam("userid", 1).when()
					.get("https://fakerestapi.azurewebsites.net/api/v1/{myPath}/{userid}")
					.then()
					.statusCode(200)
					.extract()
					.asString();
			expectedResponseSinlgeObject = removeWhitespace(expectedResponseSinlgeObject);
			actualResponse = removeWhitespace(actualResponse);
			assertEquals(expectedResponseSinlgeObject, actualResponse);
		}
		
		@Test
		void verifyGetAllUsersUsingPathAndQueryParam() {
			given()
			.pathParam("myPath", "Coverphotos")
			.when()
			.get("https://fakerestapi.azurewebsites.net/api/v1/{myPath}")
			.then()
			.statusCode(200)
			.log().all();
		}
		
		@Test
		public static void verifyEntireResponseForGetAllCoverPhotoissWithValidMethodNameAndEndpoint() throws FileNotFoundException {
			// Load the external JSON file from the specified path
			
			File f = new File(System.getProperty("user.dir")+ "\\src\\test\\resources\\ResponsePayload\\getCoverphotosResponeBody.json");
			// Open a file input stream to read the content of the file
			FileInputStream fileInputStream = new FileInputStream(f);
			// Create a JSONTokener object that parses the JSON content from the file input stream
			JSONTokener jsonTokener = new JSONTokener(fileInputStream);
		    // Create a JSONObject from the JSONTokener, which reads the JSON content into a structured format
			//JSONObject requestData = new JSONObject(jsonTokener);
			JSONArray requestData = new JSONArray(jsonTokener);
			
			
			String expectedEntireResponse =requestData.toString();
			System.out.println(expectedEntireResponse);
			String actualEntireResponse = given()
					.when()
					.get("https://fakerestapi.azurewebsites.net/api/v1/CoverPhotos").then()
					.statusCode(200)
					.extract().asString();
			assertEquals(expectedEntireResponse, actualEntireResponse);
			
		}





	
		
		
		
		

		

	}
	


