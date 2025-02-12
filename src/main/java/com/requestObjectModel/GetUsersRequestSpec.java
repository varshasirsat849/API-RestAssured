package com.requestObjectModel;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class GetUsersRequestSpec {
	final static String baseURL = "https://fakerestapi.azurewebsites.net";
	final static RequestSpecBuilder builder = new RequestSpecBuilder();

	
public RequestSpecification getUsers(){
			
		builder.setBaseUri(baseURL+"/api/v1/Users/");
		builder.setBasePath("1");		
		RequestSpecification reqSpec=builder.build();
		System.out.println(reqSpec);
		return reqSpec;
	}

}
