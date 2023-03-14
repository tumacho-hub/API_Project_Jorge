package com.cybertek.utilities;

import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;


public class SpartanNewBase {

    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;
    public static RequestSpecification userSpec;
    //basically we are creating static method that will execute our baseUrl code line
    @BeforeAll
    public static void init() {
        baseURI = "http://44.195.19.167";
        //baseURI = "http://54.92.248.102:8000";
        port = 8000;
        basePath = "/api";

        /*
        Basically what we are doing here is to breaking our request, response and verification code into three reusable
        variables which we will be declaring as instance variables sso they can be call anywhere
        this wil gives a cleaner code and reusable code

           given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin","admin")
                .log().all()
                .pathParam("id",15)
                .when()
                .get("/spartans/{id}")
                .then()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);

                in other words we are doing some Page object model with RestAssured
                we are breaking the code into reusable methods that will allow us to reuse them in other classes
         */



        requestSpec = given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin", "admin")
                .log().all();


        userSpec =given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("user", "user")
                .log().all();

        responseSpec = expect().statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .logDetail(LogDetail.ALL);  //logging with response specficiation

    }

    @AfterAll
    public static void close(){
        //reset the info we set above
        reset();
    }
}
