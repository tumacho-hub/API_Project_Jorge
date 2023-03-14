package com.cybertek.day12.practice12;

import org.junit.jupiter.api.Test;
import com.cybertek.utilities.SpartanNewBase;
import io.restassured.filter.log.LogDetail;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import io.restassured.http.ContentType;

public class SpartanSpecTest extends SpartanNewBase{
    /*
    all test in this class will use admin credentials
    all test in this class will expect json result from response
    all test in this class response status code 200
    all test in this class response content type header is json
     */

    @Test
    public  void test(){
        given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin","admin")
                .log().all()
                .when()
                .get("/spartans")
                .then()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);
    }

    @Test
    public void test2(){
        /*
        In this test we are refactoring our code base on the modifications we made on the SpartanNewBase
         */
        given().spec(requestSpec)
                .pathParam("id",15)
        .when()
                .get("/spartans/{id}")
                .then()
                .spec(responseSpec);

    }

    @Test
    public void test3(){

        given()
                .spec(requestSpec)
                .and()
                .queryParams("nameContaions", "j",
                        "gender","Female")
                .when()
                .get("/spartans/search")
                .then()
                .spec(responseSpec)
                .body("numberOfElement",is(9));



    }




}
