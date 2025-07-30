package com.qa.api.client;

import static io.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.util.Base64;
import java.util.Map;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.api.configmanager.ConfigManager;
import com.qa.api.constants.AuthType;
import com.qa.api.exceptions.APIException;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RestClient {

	private ResponseSpecification responseSpec200 = expect().statusCode(200);
	private ResponseSpecification responseSpec201 = expect().statusCode(201);
	private ResponseSpecification responseSpec400 = expect().statusCode(400);
	private ResponseSpecification responseSpec404 = expect().statusCode(404);
	private ResponseSpecification responseSpec201or200 = expect().statusCode(anyOf(equalTo(200), equalTo(201)));
	private ResponseSpecification responseSpec200or404 = expect().statusCode(anyOf(equalTo(200), equalTo(404)));
	private ResponseSpecification responseSpec204 = expect().statusCode(204);

	private RequestSpecification setupRequest(String baseURL, AuthType authType, ContentType contentType) {
		
		ChainTestListener.log("baseURL===>" +baseURL);
		ChainTestListener.log("authType===>" +authType.toString());
		
		RequestSpecification request = RestAssured.given().log().all().baseUri(baseURL).contentType(contentType);

		switch (authType) {
		case BEARER_TOKEN:
			request.header("Authorization", "Bearer " + ConfigManager.get("bearertoken"));
			break;
		case BASIC_AUTH:
			request.header("Authorization", "Basic " + generateBasicAuthToken());
			break;
		case APIKEY:
			request.header("Authorization", "APIKEY");
			break;
		case NO_AUTH:
			System.out.println("Auth is not required...");
			break;

		default:
			System.out.println("Invalid auth....please pass valid auth");
			throw new APIException("Invalid auth Exception....");
		}
		return request;

	}
	
	///// to generate basic auth
	private String generateBasicAuthToken() {
	String credentials =ConfigManager.get("basicauthusername") + ":"+ ConfigManager.get("basicauthpassword");
	return Base64.getEncoder().encodeToString(credentials.getBytes());
		
	}

	private void addParams(RequestSpecification request, Map<String, String> queryParams,
			Map<String, String> pathParams) {

		ChainTestListener.log("queryParams===>" +queryParams);
		ChainTestListener.log("pathParams===>" +pathParams);
		
		if (queryParams != null) {
			request.queryParams(queryParams);
		}
		if (pathParams != null) {
			request.queryParams(pathParams);
		}
	}

	//// CRUD
	/**
	 * This method is used to call GET api's
	 * 
	 * @param baseURL
	 * @param endPoint
	 * @param queryParams
	 * @param pathParams
	 * @param authType
	 * @param contentType
	 * @return it returns GET api calls response
	 */

	public Response get(String baseURL, String endPoint, Map<String, String> queryParams,
			Map<String, String> pathParams, AuthType authType, ContentType contentType) {

		RequestSpecification request = setupRequest(baseURL, authType, contentType);
		addParams(request, queryParams, pathParams);

		Response response = request.get(endPoint).then().spec(responseSpec200or404).extract().response();
		response.prettyPrint();
		return response;

	}

	// post - supports string and pojo body type
	public <T> Response post(String baseURL, String endPoint, T body, Map<String, String> queryParams,
			Map<String, String> pathParams, AuthType authType, ContentType contentType) {

		RequestSpecification request = setupRequest(baseURL, authType, contentType);
		addParams(request, queryParams, pathParams);

		Response response = request.body(body).post(endPoint).then().spec(responseSpec201or200).extract().response();
		response.prettyPrint();
		return response;
	}

	//// post with file body data type
	public Response post(String baseURL, String endPoint, File fileBody, Map<String, String> queryParams,
			Map<String, String> pathParams, AuthType authType, ContentType contentType) {

		RequestSpecification request = setupRequest(baseURL, authType, contentType);
		addParams(request, queryParams, pathParams);

		Response response = request.body(fileBody).post(endPoint).then().spec(responseSpec201or200).extract().response();
		response.prettyPrint();
		return response;
	}
	
	
	///////////// post call for oauth2
	
	public <T> Response post(String baseURL, String endPoint, String clientID, String clientSecret, String grantType, ContentType contentType) {

		Response response = RestAssured.given().contentType(contentType)
							.formParam("grant_type", grantType)
							.formParam("client_id", clientID)
							.formParam("client_secret", clientSecret)
							.when().post(baseURL+endPoint);
		response.prettyPrint();
		return response;
	}

	public <T> Response put(String baseURL, String endPoint, T body, Map<String, String> queryParams,
			Map<String, String> pathParams, AuthType authType, ContentType contentType) {

		RequestSpecification request = setupRequest(baseURL, authType, contentType);
		addParams(request, queryParams, pathParams);

		Response response = request.body(body).put(endPoint).then().spec(responseSpec200).extract().response();
		response.prettyPrint();
		return response;
	}

	
	public <T> Response patch(String baseURL, String endPoint, T body, Map<String, String> queryParams,
			Map<String, String> pathParams, AuthType authType, ContentType contentType) {

		RequestSpecification request = setupRequest(baseURL, authType, contentType);
		addParams(request, queryParams, pathParams);

		Response response = request.body(body).patch(endPoint).then().spec(responseSpec200).extract().response();
		response.prettyPrint();
		return response;
	}
	
	public <T> Response delete(String baseURL, String endPoint, Map<String, String> queryParams,
			Map<String, String> pathParams, AuthType authType, ContentType contentType) {

		RequestSpecification request = setupRequest(baseURL, authType, contentType);
		addParams(request, queryParams, pathParams);

		Response response = request.delete(endPoint).then().spec(responseSpec204).extract().response();
		response.prettyPrint();
		return response;
	}
}
