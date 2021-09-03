package com.cybertek.day3;


import com.sun.javafx.collections.MappingChange;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SpartansTestsWithParameters {

    //basically we are creating static method that will execute our baseUrl code line
    @BeforeAll
    public static void init() {
        baseURI = "http://100.26.48.87:1000/ords/hr";
    }
       /*
        Given accept type is json
        And parameters: q = {"region_id":2}
        When user sends Get request to "/countries"
        Then response status code is 200
        And response content-type: application/json
        And payload should contain "United States of America"
         */

    @DisplayName("Get request to  \"/countries\" ")
    @Test
    public void test1(){

        Map<String,Object> queryPam = new HashMap<>();
        queryPam.put("q","{\"region_id\":2}");

        Response response = given().log().all()
                .accept(ContentType.JSON)
                .and().queryParams(queryPam)
                    .when()
                .get("/countries");

        assertEquals(200,response.statusCode());


//          we can use either or to get content type is the same structure
//        assertEquals("application/json",response.header("Content-type"));
//        assertEquals("application/json",response.contentType());
        assertEquals("application/json",response.header("Content-type"));

        assertTrue(response.body().asString().contains("United States of America"));

    }
    @DisplayName("Get request to /employees")
    @Test
    public void test2(){

        // send a Get request to employees and get only employees whose works as an IT_PROG

        Map<String,Object>queryMap = new HashMap<>();
        queryMap.put("q","{\"job_id\": \"IT_PROG\"}");

        Response response = given().log().all()
                .accept(ContentType.JSON)
                .and().queryParams(queryMap)
                    .when()
                .get("/employees");

        assertEquals(200,response.statusCode());

        assertEquals("application/json",response.contentType());

        assertTrue(response.body().asString().contains("IT_PROG"));

        response.prettyPrint();


    }


}
