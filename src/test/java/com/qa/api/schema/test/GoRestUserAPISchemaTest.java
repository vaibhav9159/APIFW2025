package com.qa.api.schema.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.configmanager.ConfigManager;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.SchemaValidator;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GoRestUserAPISchemaTest extends BaseTest{

	@Test
	public void getUserSchemaTest() {
		ConfigManager.set("bearertoken", "ab8ea62738b5dbb0c82767e19e6100cb65c67d03e3b6ccb1dabac9c238a42c05");
	Response response =	restclient.get(BASE_URL_GOREST,  GOREST_USERS_ENDPOINT, null, null, AuthType.BEARER_TOKEN, ContentType.ANY);
	AssertJUnit.assertTrue(	SchemaValidator.validateSchema(response, "schema/getuserschema.json"));
	
		
	}
}
