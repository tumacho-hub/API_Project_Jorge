package com.cybertek.day11.practice11;


import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.regex.Matcher.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class CsvFileSourceParameterizedTest {
/*
Write a parameterized test for this request
Get the data from CSV source
Get GET https://api.zippopotam.us/us/{state}/{city}
 */
@ParameterizedTest
    @CsvFileSource(resources ="/zipcode.csv",numLinesToSkip = 1)
    public void zipCodeTestWithFile(String state, String city, int zipCount){
    System.out.println("state = " + state);
    System.out.println("city = " + city);
    System.out.println("zipCount = " + zipCount);

    //send a request and verify place number matches with zipCount

        given()
                .baseUri("https://api.zippopotam.us/")
                .accept(ContentType.JSON)
                .pathParam("state", state)
                .pathParam("city",city)
                .log().all()
                .when()
                .get("/us/{state}/{city}")
                .then()
                .statusCode(200)
                .body("places",hasSize(zipCount));




}



}
