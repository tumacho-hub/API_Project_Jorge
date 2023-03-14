package com.cybertek.day12.practice12;

import com.cybertek.utilities.SpartanNewBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class SpartanSpecTestOriginal extends SpartanNewBase {


    @Test
    public  void test(){
       /*
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
                the code below does the same thing as the code above
        */


        given()
                .spec(requestSpec)
                .when()
                .get("/spartans")
                .then()
                .spec(responseSpec);





    }

    @Test
    public void test2() {
        /*
        In this test we are refactoring our code base on the modifications we made on the SpartanNewBase
         */
        given().spec(requestSpec)
                .pathParam("id", 15)
                .when()
                .get("/spartans/{id}")
                .then()
                .spec(responseSpec);


    }
}
