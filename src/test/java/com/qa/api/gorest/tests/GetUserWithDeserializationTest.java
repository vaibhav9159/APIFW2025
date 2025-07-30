package com.qa.api.gorest.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.configmanager.ConfigManager;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.JsonUtils;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetUserWithDeserializationTest extends BaseTest {
	private String tokenid;
	
	@BeforeClass
	public void getToken(){
		tokenid = "ab8ea62738b5dbb0c82767e19e6100cb65c67d03e3b6ccb1dabac9c238a42c05";
		ConfigManager.set("bearertoken",tokenid);
	}
	
	@Test
	public void getUser() {
		
		User user = User.builder()
					.name("AfreenSaxena")
					.email(StringUtils.getRandomEmail())
					.gender("female")
					.status("active")
					.build();
		
		Response response= restclient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);	
		
		User userResponse = JsonUtils.deserialize(response, User.class);
		Assert.assertNotNull(userResponse.getId());
		
		int userid = userResponse.getId();
		
		
		
		///get call
		Response getResponse = restclient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/"+userid, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(getResponse.statusCode(), 200);
		
		User userGetResponse = JsonUtils.deserialize(getResponse, User.class);
		Assert.assertEquals(userGetResponse.getId(), userid);
		
		Assert.assertEquals(userGetResponse.getName(), user.getName());
		Assert.assertEquals(userGetResponse.getEmail(), user.getEmail());
		Assert.assertEquals(userGetResponse.getStatus(), user.getStatus());
		Assert.assertEquals(userGetResponse.getGender(), user.getGender());
		
	}
	
		
		
}
