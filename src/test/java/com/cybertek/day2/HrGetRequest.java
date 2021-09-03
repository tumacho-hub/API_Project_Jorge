package com.cybertek.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HrGetRequest {

    //basically we are creating static method that will execute our baseUrl code line
    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://100.26.48.87:1000/ords/hr";
    }
    @DisplayName("Get reques to regions")
    @Test
    public void test1(){

        //because we created the method @BeforeAll --public static void init() above we just need to pass our endPoint
        //request
        Response response = RestAssured.get("regions");

        System.out.println("response = " + response);

    }
    /*
    Given accept type is application/json
    When user sends get request to /regions/2
         Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get("/regions/2");

    Then response statues code must be 200
    And content type equals to application/json
    And response body contains Americas
     */

    @Test
    public void test2(){
        Response response =  given().accept(ContentType.JSON)
                .when()
                .get("/regions/2");

        //verify status code
        assertEquals(200,response.statusCode());

        //verify content type
        assertEquals("application/json",response.contentType());

        //verify body contains Americas
        assertEquals(response.body().asString().contains("Americas"),true);

        response.prettyPrint();


    }
}
