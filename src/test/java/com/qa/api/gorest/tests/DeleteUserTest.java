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
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.configmanager.ConfigManager;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteUserTest extends BaseTest{

	private String tokenid;
	
	@BeforeClass
	public void getToken(){
		tokenid = "ab8ea62738b5dbb0c82767e19e6100cb65c67d03e3b6ccb1dabac9c238a42c05";
		ConfigManager.set("bearertoken",tokenid);
	}
	
	@Test
	public void deleteUser() {
		
		//User user = new User("ZainabMishra","female",StringUtils.getRandomEmail(),"active");
		//create user using builder pattern
		 User user = User.builder().name("AfreenMishra")
						.gender("female")
						.email(StringUtils.getRandomEmail())
						.status("active")
						.build();
		 
		Response postResponse = restclient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		
		AssertJUnit.assertEquals(postResponse.jsonPath().getString("name"), "AfreenMishra");
		String userid=postResponse.jsonPath().getString("id");
		
		System.out.println("userid====>"+userid);
		
		//get user
		Response getResponse = restclient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/"+userid, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		AssertJUnit.assertEquals(getResponse.jsonPath().getString("id"), userid);
	
		
		//delete user
		Response deleteResponse = restclient.delete(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/"+userid, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		AssertJUnit.assertTrue(deleteResponse.statusLine().contains("No Content"));
		
		
		//get delete user test
		Response getDeletedResponse = restclient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/"+userid, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		
		AssertJUnit.assertTrue(getDeletedResponse.statusLine().contains("Not Found"));
		
		AssertJUnit.assertEquals(getDeletedResponse.statusCode(),404);
		AssertJUnit.assertEquals(getDeletedResponse.jsonPath().getString("message"), "Resource not found");	
		
	}
	
	
}
