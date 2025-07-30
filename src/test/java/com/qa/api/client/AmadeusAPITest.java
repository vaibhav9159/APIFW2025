package com.qa.api.client;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.qa.api.base.BaseTest;
import com.qa.api.configmanager.ConfigManager;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.JsonPathValidatorUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AmadeusAPITest extends BaseTest{

	
	private String accessToken;
	
	@BeforeTest
	public void generateOAUTH2Token() {
		
	Response response =	restclient.post(BASE_URL_AMADEUS_OAUTH2, AMADEUS_OAUTH2_TOKEN_ENDPOINT, ConfigManager.get("clientid"), ConfigManager.get("clientsecret"), 
				ConfigManager.get("granttype"), ContentType.URLENC);
	
		accessToken = JsonPathValidatorUtils.read(response, "access_token");
		System.out.println("Access Token ===>"+ accessToken);
		ConfigManager.set("bearertoken", accessToken);
	}
	
	@Test
	public void getFlightDetailsTest() {
		
		Map<String,String>  queryParams = new HashMap<String,String> ();
		queryParams.put("origin", "PAR");
		queryParams.put("maxPrice", "200");
		
		Response response =	restclient.get(BASE_URL_AMADEUS_OAUTH2, AMADEUS_OAUTH2_FLIGHT_ENDPOINT, queryParams, null, AuthType.BEARER_TOKEN, ContentType.ANY);
		AssertJUnit.assertEquals(response.statusCode(), 200);
		
		List<String> totalPrice = JsonPathValidatorUtils.read(response, "$.data[*].price.total");
		System.out.println("prices====>"+totalPrice);
		System.out.println("MIN price====>"+Collections.min(totalPrice));
		
		List<Map<String, String>> DEL = JsonPathValidatorUtils.readListOfMaps(response, "$..dictionaries.locations.DEL");
		System.out.println(DEL);
		for(Map<String, String> e: DEL) {
			String subType = (String) e.get("subType");
			String detailedName = (String) e.get("detailedName");
			AssertJUnit.assertEquals(subType, "AIRPORT");
			AssertJUnit.assertTrue(detailedName.contains("INDIRA"));
		}
			
		List<Number> priceDEL = JsonPathValidatorUtils.readList(response, "$.data[*].[?(@.destination=='DEL')].price.total");
		System.out.println("ticket price for DEL ===>"+priceDEL);
		
		
		
	}
	
	
	
}
