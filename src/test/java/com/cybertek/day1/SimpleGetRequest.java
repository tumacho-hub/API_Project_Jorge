package com.cybertek.day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class SimpleGetRequest {

    String url = "http://100.26.48.87:8000/api/spartans";

    @Test
    public void test1(){
        // send a get request and save response inside the response object
        Response response = RestAssured.get(url);

        //print the status code
        System.out.println("response.statusCode() = " + response.statusCode());

        //print response body
        response.prettyPrint();


    }



}
