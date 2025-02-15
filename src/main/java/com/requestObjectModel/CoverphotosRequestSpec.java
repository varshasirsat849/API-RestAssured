package com.requestObjectModel;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class CoverphotosRequestSpec {
	final static String baseURL = "https://fakerestapi.azurewebsites.net";
	final static RequestSpecBuilder builder = new RequestSpecBuilder();

	
public RequestSpecification getCoverphotos(){
			
		builder.setBaseUri(baseURL+"/api/v1/CoverPhotos");
		builder.setBasePath("1");		
		RequestSpecification reqSpec=builder.build();
		System.out.println(reqSpec);
		return reqSpec;
	}

public RequestSpecification createCoverphotos(){
	
	builder.setBaseUri(baseURL+"/api/v1/CoverPhotos");
	builder.setBody("{\r\n"
			+ "        \"id\": 7532,\r\n"
			+ "        \"idBook\": 7532,\r\n"
			+ "        \"url\": \"https://placeholdit.imgix.net/~text?txtsize=33&txt=Book 72&w=250&h=350\"\r\n"
			+ "    }");
	builder.setContentType(ContentType.JSON);		
	RequestSpecification reqSpec=builder.build();
	System.out.println(reqSpec);
	return reqSpec;
}



}
