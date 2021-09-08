package com.cybertek.day12;

import com.cybertek.utilities.SpartanNewBase;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;


import static java.util.regex.Matcher.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class OldRestAssuredTest extends SpartanNewBase {

    @Test
    public void getAllSpartan(){

        //below code is one of the newest verification we have so far
        given()
                .accept(ContentType.JSON)
                .and()
                .auth()
                .basic("admin","admin")
                .log().all()
                .when().get("/spartans")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id[8]",is(12))
                .log().all();
    }

    @Test
    public void test2(){
        // old way verification
        //second way will fail but it will run both scenarios

        /*
            in previous version of Restassured, the given when then style
            was actually written in given expect when format.
            it will assert all the result and give one answer and does not fail whole thing
            if first one fail unlike new structure.

         */
        given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin","admin")
                .log().all().
        expect()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .body("id[0]",is(8))
                .body("id[2]",is(11))
                .logDetail(LogDetail.ALL).//long way using with expect
        when()
                .get("/spartans");
    }


}
