package com.cybertek.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class SpartanNegativeGetTest {

    //basically we are creating static method that will execute our baseUrl code line
    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://100.26.48.87:8000";

}
/*
Given Accept type application/XML
When user send Get request to /api/spartans/10 end point
Then status code must be 406
Then response Content type must be application/xml;charset=UTF-8
 */

@DisplayName("Get application/xml")
@Test
public void test1(){

    Response response = given().accept(ContentType.XML)
            .when()
            .get("/api/spartans/10");

    //verify status code
    assertEquals(406,response.statusCode());

    //verify content type
    assertEquals("application/xml;charset=UTF-8",response.contentType());





}
}
