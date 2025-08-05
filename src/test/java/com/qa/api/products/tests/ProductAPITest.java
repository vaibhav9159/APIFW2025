package com.qa.api.products.tests;


import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.Product;
import com.qa.api.utils.JsonUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductAPITest extends BaseTest{


	@Test
	public void createUser() {
	
		Response response= restclient.get(BASE_URL_PRODUCTS, PRODUCTS_GET_ENDPOINT, null, null, AuthType.NO_AUTH, ContentType.ANY);
		AssertJUnit.assertEquals(response.statusCode(),200);
		
		Product product[] = JsonUtils.deserialize(response, Product[].class);
		for(Product p:product) {
		System.out.println(p.getId() +" , "+p.getTitle() +" , "+p.getCategory() +" , " +p.getPrice()+" , " +p.getRating().getRate());
			
			
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
