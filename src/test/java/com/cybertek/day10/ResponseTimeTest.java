package com.cybertek.day10;

import com.cybertek.utilities.SpartanAuthBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.util.regex.Matcher.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;


public class ResponseTimeTest extends SpartanAuthBase {

    @DisplayName("Time verification")
    @Test
    public void test1(){

        Response response = given().auth().basic("admin", "admin")
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans")
                .then()
                .time(both(greaterThan(500L)).and(lessThanOrEqualTo(1200L)))
                .extract().response();

        System.out.println("response.getTime() = " + response.getTime());


    }

}
