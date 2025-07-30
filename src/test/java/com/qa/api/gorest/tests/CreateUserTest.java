package com.qa.api.gorest.tests;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.configmanager.ConfigManager;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtils;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@Epic("Epic 1: GO Rest Create User API feature")
@Story("IND 1: feature go rest api - create user")
public class CreateUserTest extends BaseTest{
	private String tokenid;
	
	@BeforeClass
	public void getToken(){
		tokenid = "ab8ea62738b5dbb0c82767e19e6100cb65c67d03e3b6ccb1dabac9c238a42c05";
		ConfigManager.set("bearertoken",tokenid);
	}
	
	@DataProvider
	public Object[][] getUserData() {
		return new Object[][] {
			{"Shivani Srivastava","female","active"},
			{"Zainab Saxena","female","active"},
			{"Afreen Mishra","female","active"},
			{"Vaibhav Srivastava","male","active"},
		};
	}
	
	@Test(dataProvider="getUserData")
	public void createUser(String name, String gender, String status) {
		User user = new User(null,name,gender,StringUtils.getRandomEmail(),status);
	Response response= restclient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.jsonPath().getString("name"), name);
		Assert.assertEquals(response.jsonPath().getString("gender"), gender);
		Assert.assertEquals(response.jsonPath().getString("status"), status);
		Assert.assertNotNull("id");
	}
	
	@Test
	public void createUserWithFile() throws IOException {
	
	//	File userfile = new File("./src/test/resources/jsons/user.json");
		String rawJson = new String(Files.readAllBytes(Paths.get("./src/test/resources/jsons/user.json")));
		String updatedJson = rawJson.replace("{{email}}", StringUtils.getRandomEmail());
		
	Response response= restclient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, updatedJson, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		AssertJUnit.assertEquals(response.jsonPath().getString("name"), "ZainabMishra");
		Assert.assertNotNull("id");
	}
	
}
