package com.requestObjectModel;
import org.json.JSONObject;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class AuthorRequestSpec {
	
	final static String baseURL = "https://fakerestapi.azurewebsites.net";
	final static RequestSpecBuilder builder = new RequestSpecBuilder();

	public RequestSpecification getAuthor() {

		builder.setBaseUri(baseURL + "/api/v1/Authors/");
		// builder.setBasePath("11");
		RequestSpecification reqSpec = builder.build();
		System.out.println(reqSpec);
		return reqSpec;
	}

	public RequestSpecification createAuthor() {
		builder.setBaseUri(baseURL + "/api/v1/Authors/");

		JSONObject requestBody = new JSONObject();
		requestBody.put("id", 11);
		requestBody.put("idBook", 1);
		requestBody.put("firstName", "John");
		requestBody.put("lastName", "Doe");
		builder.setBody(requestBody.toString());
		RequestSpecification reqSpec = builder.build();
		System.out.println(reqSpec);
		return reqSpec;

	}
	//create Author dyanamically
	public RequestSpecification createAuthorDyanmically(int id, int idBook, String firstName, String lastName) {
	    builder.setBaseUri(baseURL + "/api/v1/Authors/");
	    
	    // Create the request body dynamically based on the method parameters
	    JSONObject requestBody = new JSONObject();
	    requestBody.put("id", id);
	    requestBody.put("idBook", idBook);
	    requestBody.put("firstName", firstName);
	    requestBody.put("lastName", lastName);

	    
	    builder.setBody(requestBody.toString());
	    RequestSpecification reqSpec = builder.build();
	    return reqSpec;
	}
}
