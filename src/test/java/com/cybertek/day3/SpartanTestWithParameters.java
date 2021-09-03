package com.cybertek.day3;


import com.sun.org.apache.xpath.internal.operations.And;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;



public class SpartanTestWithParameters {

    //basically we are creating static method that will execute our baseUrl code line
    @BeforeAll
    public static void init() {
        baseURI = "http://100.26.48.87:8000";
    }

        @DisplayName("Get request to /api/spartans/{id}")
        @Test
        public void test1 () {

        /*
        Given accept ty is json
        And Id parameter value is 5
        When user sends get request to /api/spartans/{id}
        Then response status code should be 200
        And response content-type: application/json
        And "Blythe" should be in response payload
         */
            // sentence below cover first two steps
            // Given accept ty is json
            // And Id parameter value is 5
            Response response = given().accept(ContentType.JSON)
                    .and().pathParam("id", 5)
                    .when()
                    .get("/api/spartans/{id}");

            // Then response status code should be 200
            assertEquals(200,response.statusCode());

            //And response content-type: application/json
            assertEquals("application/json",response.contentType());


            //verify body contains Blythe or they can say verify payload contains Blythe same thing!!
            assertTrue(response.body().asString().contains("Blythe"));

            response.prettyPrint();
    }
 /*
        Given accept type is json
        And Id parameter value is 500
        When user sends get request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json
        And "Not Found" should be in response payload
         */

    @DisplayName("Get request to /api/spartans/{id} ")
    @Test
    public void test(){
        //  Given accept type is json
        //  And Id parameter value is 500
        //  When user sends get request to /api/spartans/{id}
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",500)
                .when()
                .get("/api/spartans/{id}");

        //Then response status code should be 404
        assertEquals(404,response.statusCode());

        //And response content-type: application/json
        assertEquals("application/json",response.contentType());

        //  And "Not Found" should be in response payload
        assertTrue(response.body().asString().contains("Not Found"));

        response.prettyPrint();


    }
     /*
        Given accept type is json
        And query parameter value are:
        gender/Female
        nameContains/e
        When user sends get request to   /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
         */


    @DisplayName("Get request to /api/spartans/search with query Params ")
    @Test
    public void test3(){
        // Given accept type is json
        //        And query parameter value are:
        //        gender/Female
        //        nameContains/e
        //        When user sends get request to   /api/spartans/search
        Response response = given().log().all()
                .accept(ContentType.JSON)
                .and().queryParam("gender", "Female")
                .and().queryParam("nameContains", "e")
                .when()
                .get("/api/spartans/search");

        //        Then response status code should be 200
        assertEquals(200,response.statusCode());

        //And response content-type: application/json
        assertEquals("application/json",response.contentType());


        //  And "Female" should be in response payload
        assertTrue(response.body().asString().contains("Female"));

        //And "Janette" should be in response payload
        assertTrue(response.body().asString().contains("Janette"));



    }

    @DisplayName("Get request to /api/spartans/search with query Params (Map)")
    @Test
    public void test4(){
        // in this case we can also create a Map and get the same result as if were using query params logic

        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("nameContains","e");
        queryMap.put("gender","Female");

        Response response = given().log().all()
                .accept(ContentType.JSON)
                .and().queryParams(queryMap)
                    .when()
                .get("/api/spartans/search");

        assertEquals(200,response.statusCode());

        assertEquals("application/json",response.contentType());

        assertTrue(response.body().asString().contains("Female"));

        assertTrue(response.body().asString().contains("Janette"));


    }




}

