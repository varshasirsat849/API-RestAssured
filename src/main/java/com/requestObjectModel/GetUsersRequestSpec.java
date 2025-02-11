package com.requestObjectModel;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class GetUsersRequestSpec {
	final static String baseURL = "https://fakerestapi.azurewebsites.net";
	final static RequestSpecBuilder builder = new RequestSpecBuilder();

public static RequestSpecification getUsers(){
			
		builder.setBaseUri("/api/v1/Users/");
		builder.setBasePath("");		
		RequestSpecification reqSpec=builder.build();
		return reqSpec;
	}

}
