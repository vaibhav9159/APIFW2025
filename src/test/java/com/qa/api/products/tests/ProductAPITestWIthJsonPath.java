package com.qa.api.products.tests;



import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.Product;

import com.qa.api.utils.JsonPathValidatorUtils;
import com.qa.api.utils.JsonUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductAPITestWIthJsonPath extends BaseTest{


	@Test
	public void GetqauctsTest() {
	
		Response response= restclient.get(BASE_URL_qaUCTS, qaUCTS_GET_ENDPOINT, null, null, AuthType.NO_AUTH, ContentType.ANY);
		Assert.assertEquals(response.statusCode(),200);
		
		List<Number> prices = JsonPathValidatorUtils.readList(response, "$.[*].[?(@.price>50)].price");
		System.out.println(prices);
		
		List<Number> ids = JsonPathValidatorUtils.read(response, "$.[*].[?(@.price>50)].id");
		System.out.println(ids);
		
		List<Number> rates = JsonPathValidatorUtils.read(response, "$.[*].[?(@.price>50)].rating.rate");
		System.out.println(rates);
		
		List<Map<String, Object>> idTitles = JsonPathValidatorUtils.readListOfMaps(response, "$.[*].[?(@.price>50)].['id','title']");
		System.out.println("id and Titles===>"+idTitles);
		
		Double minPrice = JsonPathValidatorUtils.read(response, "min($.[*].price)");
		System.out.println("min price ===>"+minPrice);
		
		Integer lastID = JsonPathValidatorUtils.read(response, "last($.[*]).id");
		System.out.println("last id  ===>"+lastID);
		
		
		Product qauct[] = JsonUtils.deserialize(response, Product[].class);
		for(Product p:qauct) {
		Assert.assertNotNull(p.getId());
	//	System.out.println(p.getId() +" , "+p.getTitle() +" , "+p.getCategory() +" , " +p.getPrice()+" , " +p.getRating().getRate());	
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
