package com.qa.api.basicauth.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BasicAuthTest extends BaseTest{

	
	@Test
	public void getBasicAuthTest() {
		
	Response response = restclient.get(BASE_URL_HEROKU_BASICAUTH, BASICAUTH_HEROKU_ENDPOINT, null, null, AuthType.BASIC_AUTH, ContentType.ANY);
	AssertJUnit.assertTrue(response.getBody().asString().contains("Congratulations!"));
	
	}
	
		
	
}
