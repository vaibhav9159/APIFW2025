package com.qa.api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;

public class JsonUtils {

	private static ObjectMapper map = new ObjectMapper();
	
	public static <T> T deserialize(Response response, Class<T> targetClass) {
		
		try {
				return map.readValue(response.getBody().asString(), targetClass);
		} catch(Exception e) {
			throw new RuntimeException("deserializatoin failed...."+ targetClass.getName());
		}
		
	}
	
	
}
