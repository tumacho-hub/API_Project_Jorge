package com.cybertek.day11;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static java.util.regex.Matcher.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class ParameterizedTestInJunit5 {
//we are learning junit5 annotations
    @ParameterizedTest
    @ValueSource(ints={1,2,3,4,5,6,7,8,9,10})
    public void testMultipleNumbers (int number){

        System.out.println("number = " + number);

        Assertions.assertTrue(number>5);

    }

    @ParameterizedTest
    @ValueSource(strings = {"jorge","Maria","Leo"})
    public void testMultipleNames(String names){
        System.out.println("names = " + names);

    }

    //SEND GET REQUEST TO https://api.zippopotam.us/us/{zipcode}
    //with these zipcodes 22030,22031,22032,22033,22034,22035,22036,
    //check status code  200

    @ParameterizedTest
    @ValueSource(ints = {22030,22031,22032,22033,22034,22035,22036})
    public void zipCodeTest(int zipCode){

        given()
                .baseUri("https://api.zippopotam.us")
                .pathParam("zipCode", zipCode)
                .log().all()
                .when()
                .get("/us/{zipCode}")
                .then().log().all()
                .statusCode(200);
//        given()
//                .baseUri("https://api.zippopotam.us")
//                .pathParam("zipcode",zipCode) //we got zipcode from valueSource
//                .log().all() //request log
//                .when()
//                .get("/us/{zipcode}")
//                .then().log().all() //response log
//                .statusCode(200);

    }
}

