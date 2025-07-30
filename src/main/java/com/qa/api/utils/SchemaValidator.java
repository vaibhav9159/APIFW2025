package com.qa.api.utils;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class SchemaValidator {

	public static boolean validateSchema(Response resp, String schemaFile) {
		
		try {
		resp.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaFile));
		System.out.println("schema validation is passed for "+ schemaFile);
		return true;
		}catch(Exception e) {
			System.out.println("schema validation failed ..."+ e.getMessage());
			return false;
		}
	}
	
	
}
