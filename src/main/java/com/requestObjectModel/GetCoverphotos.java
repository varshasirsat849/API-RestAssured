package com.requestObjectModel;

import org.apache.commons.lang3.RandomStringUtils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class GetCoverphotos {
	
public static RequestSpecification getCoverphotos(){
		

		
		RequestSpecBuilder builder=new RequestSpecBuilder();
		
		builder.setBaseUri("https://fakerestapi.azurewebsites.net");
		builder.setBasePath("/api/v1/CoverPhotos");
		
		RequestSpecification reqspecification=builder.build();
		return reqspecification;
	}



	
}
