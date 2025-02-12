package author.testcases;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AuthorApiChaining {
	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "https://fakerestapi.azurewebsites.net/api/v1";
	}
	 private static int authorId; // Store ID for chaining

	    @Test(priority = 1)
	    public void verifyCreateAuthor() {
	        Response response = 
	        		 given()
	                .body("{\r\n"
	                		+ "    \"id\": 11,\r\n"
	                		+ "    \"idBook\": 100,\r\n"
	                		+ "    \"firstName\": \"First Name 11\",\r\n"
	                		+ "    \"lastName\": \"Last Name 11\"\r\n"
	                		+ "}")
	                .contentType(ContentType.JSON)
	                .when()
	                .post("/Authors")
	                .then()
	                .log().all()
	                .assertThat()
	                .statusCode(200)
	                .body("id", Matchers.equalTo(11))
	                .body("idBook", Matchers.equalTo(100))
	                .body("firstName", Matchers.equalTo("First Name 11"))
	                .extract().response();

	        authorId = response.jsonPath().getInt("id");  // Store ID for next tests
	        System.out.println("authorId: "+authorId);
	        Assert.assertNotNull(authorId, "Author ID should not be null");
	    }

	    @Test(priority = 2, dependsOnMethods = "verifyCreateAuthor")
	    public void verifyGetAuthor() {
	        given()
	                .when()
	                .get("/Authors/" + authorId)
	                .then()
	                .log().all()
	                .assertThat()
	                .statusCode(200)
	                .body("id", Matchers.equalTo(authorId))
	                .body("firstName", Matchers.equalTo("First Name 11"));
	    }

	    @Test(priority = 3, dependsOnMethods = "verifyGetAuthor")
	    public void verifyUpdateAuthor() {
	        String updatedBody = "{ \"id\": " + authorId + ", \"idBook\": 101, \"firstName\": \"JAVA\", \"lastName\": \"Programming\" }";

	        given()
	                .body(updatedBody)
	                .contentType(ContentType.JSON)
	                .when()
	                .put("/Authors/" + authorId)
	                .then()
	                .log().all()
	                .assertThat()
	                .statusCode(200)
	                .body("id", Matchers.equalTo(authorId))
	                .body("idBook", Matchers.equalTo(101))
	                .body("firstName", Matchers.equalTo("JAVA"));
	    }

	    @Test(priority = 4, dependsOnMethods = "verifyUpdateAuthor")
	    public void verifyDeleteAuthor() {
	        given()
	                .when()
	                .delete("/Authors/" + authorId)
	                .then()
	                .log().all()
	                .assertThat()
	                .statusCode(200);
	    }

//	    @Test(priority = 5, dependsOnMethods = "verifyDeleteAuthor")
//	    public void verifyAuthorDeletion() {
//	        given()
//	                .when()
//	                .get("/Authors/" + authorId)
//	                .then()
//	                .log().all()
//	                .assertThat()
//	                .statusCode(404);  // Author should be deleted FINAL validation
//	    }
}
