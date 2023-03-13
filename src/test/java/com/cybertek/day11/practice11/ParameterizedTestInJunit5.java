package com.cybertek.day11.practice11;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;



public class ParameterizedTestInJunit5 {
    /*
    @ValueSource is one of the simplest possible sources. It lets you specify a single array of literal values and can only be used for providing a single argument per parameterized test invocation.

The following types of literal values are supported by @ValueSource.
short-byte-int-long-float-double-char-boolean
java.lang.String
java.lang.Class
For example, the following @ParameterizedTest method will be invoked three times, with the values 1, 2, and 3 respectively.
     */

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0})
    public void testWithValueSource(int number) {
        System.out.println("number = " + number);
        Assertions.assertTrue(number > 5);
    }


    @ParameterizedTest
    @ValueSource(strings = {"Maria", "Juan", "carlos", "Zeus"})
    public void testMultipleNames(String names) {
        System.out.println("names = " + names);
    }

    /*
    SEND GET REQUEST TO https://api.zippopotam.us/us/{zipcode}
    with these zipcodes 22030, 22031, 22032, 22033, 22034, 22035, 22036
    check status code 200
     */

    /*
    the example below is an example of  DDT(Data Driven testing) with JUnit5 I know how to provide parameters and
    run the same test case with different test input
     */
    @ParameterizedTest
    @ValueSource(ints = {22030, 22031, 22032, 22033, 22034, 22035, 22036})
    public void zipCodeTest(int zipCode) {


        given().baseUri("https://api.zippopotam.us")
                .pathParam("zipcode",zipCode)
                .log().all()
                .when()
                .get("/us/{zipcode}")
                .then().log().all()
                .statusCode(200);


    }
}