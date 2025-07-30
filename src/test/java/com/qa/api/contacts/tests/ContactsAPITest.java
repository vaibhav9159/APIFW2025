package com.qa.api.contacts.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.configmanager.ConfigManager;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.ContactsCredentials;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ContactsAPITest extends BaseTest{
	private String tokenID;
	
	@BeforeMethod
	public void generateToken() {
		
		ContactsCredentials creds =	ContactsCredentials.builder()
		.email("mastermanifestor@gmail.com")
		.password("creator")
		.build();
		
	Response response=restclient.post(BASE_URL_CONTACTS, CONTACTS_LOGIN_ENDPOINT, creds, null, null, AuthType.NO_AUTH, ContentType.JSON);
	tokenID = response.jsonPath().getString("token");
	 
	 System.out.println("token id ====>"+ tokenID);
	 
	 ConfigManager.set("bearertoken", tokenID);
	}
	
	@Test
	public void getAllContactsTest() {
		
	Response response =	restclient.get(BASE_URL_CONTACTS, CONTACTS_GET_ENDPOINT, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
	AssertJUnit.assertEquals(response.statusCode(), 200);
	
	
	}

}
