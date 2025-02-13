package com.requestObjectModel;

import io.restassured.builder.RequestSpecBuilder;
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

}
