package com.qa.api.base;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.api.client.RestClient;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

@Listeners(ChainTestListener.class)
public class BaseTest {
	
	protected RestClient restclient;
	
	////// API BAse URLS //////////
	protected final static String BASE_URL_GOREST = "https://gorest.co.in";
	protected final static String BASE_URL_CONTACTS = "https://thinking-tester-contact-list.herokuapp.com";
	protected final static String BASE_URL_REQRES = "https://reqres.in";
	protected final static String BASE_URL_HEROKU_BASICAUTH = "https://the-internet.herokuapp.com";
	protected final static String BASE_URL_PRODUCTS = "https://fakestoreapi.com";
	protected final static String BASE_URL_AMADEUS_OAUTH2 = "https://test.api.amadeus.com";

	
//////API EndPoint URLS //////////
	protected final static String GOREST_USERS_ENDPOINT = "/public/v2/users";
	protected final static String CONTACTS_LOGIN_ENDPOINT = "/users/login";
	protected final static String CONTACTS_GET_ENDPOINT = "/contacts";
	protected final static String REQRES_GET_ENDPOINT = "/api/users";
	protected final static String BASICAUTH_HEROKU_ENDPOINT = "/basic_auth";
	protected final static String PRODUCTS_GET_ENDPOINT = "/products";
	protected final static String AMADEUS_OAUTH2_TOKEN_ENDPOINT = "/v1/security/oauth2/token";
	protected final static String AMADEUS_OAUTH2_FLIGHT_ENDPOINT = "/v1/shopping/flight-destinations";
	
	
	@BeforeSuite
	public void setupAllure() {
		RestAssured.filters(new AllureRestAssured());
	}
	
	@BeforeTest
	public void setup() {
		restclient = new RestClient();
		
	}
}
