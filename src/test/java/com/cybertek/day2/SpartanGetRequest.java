package com.cybertek.day2;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpartanGetRequest {
    String baseUrl = "http://100.26.48.87:8000";
    //  Given Accept type application/json
    //  When user send Get request to api/spartans end point
    //  Then status code (200)
    //  And response Content Type must be application/json
    //  And response body should include spartan result

    @Test
    public void test1(){

         // first state case done
        //this is how you do tasks with RestAssured!

        //  Given Accept type application/json
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(baseUrl + "/api/spartans");

        // printing status code from response object
        System.out.println("response.statusCode() = " + response.statusCode());

        // printing response content type from response object
        System.out.println("response.contentType() = " + response.contentType());

        response.prettyPrint();

        //how to do API testing?
        //verify status code is 200
        Assertions.assertEquals(response.statusCode(),200);



    }
    /*
    1-Given accept is application/json
        this means you go to post man and enter the values (("Accept" under key) and
        ("application/json" under Value) under Headers)

    2-When user sends a get request to api/spartans/3
        go to Get new request postMan and add the end point --> {{spartans1}}/api/spartans/3

    3-Then status code should be (200)
    status code should be "200"

    4-And content type should be application/json
        Then check the headers inside the response body "application/json"

    5-And json body should contain BruceWayne
        and json body should contain "BruceWayne" "id": 3,
        "name": "BruceWayne",
        "gender": "Male",
        "phone": 8811111111

     */
    @DisplayName("Get one spartan /api/spartans/3 and verify")
    @Test
    public void test2(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(baseUrl + "/api/spartans/3");

        //when they said verify they talk about Assertions!
        //Then status code should be (200) below is the verification for this task
        Assertions.assertEquals(response.statusCode(),200);

        //verify content type
        //And content type should be application/json
        Assertions.assertEquals(response.contentType(),"application/json");

        //And json body should contain BruceWayne
        Assertions.assertTrue(response.body().asString().contains("BruceWayne"));

        response.prettyPrint();


    }
    /*
    Given no headers provided
        this time we don't provided .accept(ContentType.JSON) because we have no headers provided from documentation
        Response response = RestAssured.when().get(baseUrl + "/api/hello");

    When User sends Get request to /api/hello

    Then response status code should be 200
            Assertions.assertEquals(response.statusCode(),200);

    And Content type header should be "text/plain;charset=UTF-8"
            Assertions.assertEquals("text/plain;charset=UTF-8",response.contentType());

    And header should contain date
            Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

    And Content-Length should be 17
            Assertions.assertEquals("17",response.header("Content-Length")
            System.out.println("response.header(\"Content-Length\") = " + response.header("Content-Length"));

    And body should be "Hello from Sparta"
     */
    @DisplayName("Get request to /api/hello")
    @Test
    public void test3(){

        //send request and save response inside object
        Response response = RestAssured.when().get(baseUrl + "/api/hello");

        //verify status code is 200
        Assertions.assertEquals(response.statusCode(),200);

        //verify content type
        Assertions.assertEquals("text/plain;charset=UTF-8",response.contentType());

        //verify we have headers named date
        //we use hasHeaderWithName method to verify header exist or not
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

        // how to get header from response using header key?
        //we use response.header (String headerName) method to get any header value
        System.out.println("response.header(\"Content-Length\") = " + response.header("Content-Length"));

        System.out.println(response.header("Date"));

        //verify content length is 17
        Assertions.assertEquals("17",response.header("Content-Length"));

        //verify body is "Hello from Sparta"
        Assertions.assertEquals("Hello from Sparta",response.body().asString());

        response.prettyPrint();


    }


















}
