package com.qa.reqres.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ReqResTest extends BaseTest{

	@Test
	public void getAllReqResUsersTest() {
	Map<String,String> queryParams = new HashMap<String,String>();
	queryParams.put("page", "2");
		
	Response response =	restclient.get(BASE_URL_REQRES, REQRES_GET_ENDPOINT, queryParams, null, AuthType.NO_AUTH, ContentType.JSON);
	AssertJUnit.assertEquals(response.statusCode(), 200);
	
	
	}

}
