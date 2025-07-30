package com.qa.api.gorest.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.configmanager.ConfigManager;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.JsonUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetUserTest extends BaseTest{
	
private String tokenid;
	
	@BeforeClass
	public void getToken(){
		tokenid = "ab8ea62738b5dbb0c82767e19e6100cb65c67d03e3b6ccb1dabac9c238a42c05";
		ConfigManager.set("bearertoken",tokenid);
	}
	
	
	@Test
	public void getAllUsersTest() {
		Response response = restclient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		AssertJUnit.assertTrue(response.statusLine().contains("OK"));
		
		User userGetResponse[] = JsonUtils.deserialize(response, User[].class);
		
		for(User u :userGetResponse) {
			Assert.assertNotNull(u.getId());
			System.out.println(u.getName() +"---"+ u.getEmail() +"---"+ u.getGender() +"---"+ u.getStatus() +"---"+u.getId());
		}
		
				
	}
	
	@Test
	public void getAllUsersQueryParamsTest() {
		
		Map<String,String> queryParams = new HashMap<String,String>();
		queryParams.put("name", "ZainabSaxena");
		queryParams.put("status", "active");
		Response response = restclient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, queryParams, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		AssertJUnit.assertTrue(response.statusLine().contains("OK"));
		
		
	}
	
	@Test
	public void getSingleUserTest() {
		String userid = "8038315";
		Response response = restclient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/"+userid, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		AssertJUnit.assertTrue(response.statusLine().contains("OK"));
		AssertJUnit.assertEquals(response.jsonPath().getString("id"), userid);
	}

}
