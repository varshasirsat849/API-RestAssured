package coverPhotos.testcases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CoverphotosApiChaining {
	
	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/api/v1";
	}
	 private static int coverphotoId; // Store ID for chaining

	 
	 
	@Test(priority = 1)
	public void verifyCreateCoverphotos() throws FileNotFoundException {
		// Load the external JSON file from the specified path

		File f = new File(System.getProperty("user.dir")
				+ "\\src\\test\\resources\\requestPayload\\createCoverphotosForChainningRequestbody.json");
		// Open a file input stream to read the content of the file
		FileInputStream fileInputStream = new FileInputStream(f);
		// Create a JSONTokener object that parses the JSON content from the file input
		// stream
		JSONTokener jsonTokener = new JSONTokener(fileInputStream);
		// Create a JSONObject from the JSONTokener, which reads the JSON content into a
		// structured format
		JSONObject requestData = new JSONObject(jsonTokener);
		
		Response response = 
		given()
		.when()
		.body(requestData.toString())
		.contentType(ContentType.JSON)
		.post("/CoverPhotos")
		.then().log().all()
		.assertThat()
		.body("id", Matchers.equalTo(72))
		.body("idBook", Matchers.equalTo(72))
		.body("url",Matchers.equalTo("https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 72&w=250&h=350"))
		.body("size()", greaterThan(0)).header("Content-Type", "application/json; charset=utf-8; v=1.0")
		.extract().response();
		
		coverphotoId = response.jsonPath().getInt("id");  // Store ID for next tests
		System.out.println("authorId: "+coverphotoId);
        Assert.assertNotNull(coverphotoId, "Author ID should not be null");
	}
	
	@Test(priority = 2,dependsOnMethods="verifyCreateCoverphotos")
	public void verifyGetCoverphotos(){
		 given()
         .when()
         .get("/Coverphotos/" + coverphotoId)
         .then()
         .log().all()
         .assertThat()
         .statusCode(200)
         .body("id", Matchers.equalTo(coverphotoId))
         .body("idBook", Matchers.equalTo(72))
		 .body("url", Matchers.equalTo("https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 72&w=250&h=350"));
		 
}
	
	 @Test(priority = 3, dependsOnMethods = "verifyGetCoverphotos")
	    public void verifyUpdateCoverPhotos() throws FileNotFoundException {
		 
			File f = new File(System.getProperty("user.dir")+ "\\src\\test\\resources\\requestPayload\\updateCoverphotosRequestbody.json");
			// Open a file input stream to read the content of the file
			FileInputStream fileInputStream = new FileInputStream(f);
			// Create a JSONTokener object that parses the JSON content from the file input stream
			JSONTokener jsonTokener = new JSONTokener(fileInputStream);
		    // Create a JSONObject from the JSONTokener, which reads the JSON content into a structured format
			JSONObject requestData = new JSONObject(jsonTokener);
			
			
			given().when()
			.body(requestData.toString())
			.contentType(ContentType.JSON)
			 .put("/Coverphotos/" + coverphotoId)
			.then().log().all()
			.assertThat()
			.body("id",Matchers.equalTo(7632))
			.body("idBook",Matchers.equalTo(7632))
			.body("url",Matchers.equalTo("https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 7632&w=250&h=350"))
			.body("size()", greaterThan(0));
		}

		 
             
	    
	 
	 @Test(priority = 4, dependsOnMethods = "verifyUpdateCoverPhotos")
	    public void verifyDeleteAuthor() {
	        		given()
	                .when()
	                .delete("/Coverphotos/" + coverphotoId)
	                .then()
	                .log().all()
	                .assertThat()
	                .statusCode(200);
	    }

		
	}


