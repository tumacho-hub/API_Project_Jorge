package com.cybertek.day11;


import io.restassured.http.ContentType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static java.util.regex.Matcher.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class CvsSourceParametrizedTest {

    //test first number + second number = third number
    //  1,  3,  4
    //  2,  3,  5
    //  8,  7,  15
    // 10,  9,  19

    @ParameterizedTest
    @CsvSource({" 1,  3,  4","2,  3,  5","8,  7,  15","10,  9,  19"})
    public void testAddition(int num1,int num2,int sum){

// csv coma separate values

        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);
        System.out.println("sum = " + sum);


        assertThat(num1 + num2,equalTo(sum));

    }
    // Write a parameterized test for this request
    // GET https://api.zippopotam.us/us/{state}/{city}
    /*
        "NY, New York",
        "CO, Denver",
        "VA, Fairfax",
        "VA, Arlington",
        "MA, Boston",
        "NY, New York",
        "MD, Annapolis"
     */
     // verify place name contains your city name
    //print number of places for each request


    @ParameterizedTest
    @CsvSource({ "NY, New York",
            "CO, Denver",
            "VA, Fairfax",
            "VA, Arlington",
            "MA, Boston",
            "NY, New York",
            "MD, Annapolis"})
    public void zipCodeMultiInputsTest(String state, String city){

        System.out.println("state = " + state);
        System.out.println("city = " + city);

        int placeNumber = given()
                .baseUri("https://api.zippopotam.us")
                .accept(ContentType.JSON)
                .pathParam("state", state)
                .pathParam("city", city)
                .log().all()
                .when()
                .get("/us/{state}/{city}")
                .then()
                .statusCode(200)
                .and()
                //when there is a space in our path we use '' to make up for the value
                //example below
                .body("places.'place name'", everyItem(containsStringIgnoringCase(city)))
               // .log().all()
                .extract()
                .jsonPath().getList("places").size();

        System.out.println("placeNumber = " + placeNumber);


    }





}
