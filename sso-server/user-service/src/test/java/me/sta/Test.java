package me.sta;

import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, String> params = new HashMap<String, String>();
        params.put("grant_type", "password");
        params.put("client_id", "sso-auth");
        params.put("username", "gao");
        params.put("password", "123456");
		 Response response = RestAssured.given().auth().preemptive().basic("sso-auth", "123456").and().with().params(params).when().post("http://127.0.0.1:3000/oauth/token");
	     System.out.println(response.getStatusCode());
		 String token = response.jsonPath().getString("access_token");
	     System.out.println(token);
		
	}

}
