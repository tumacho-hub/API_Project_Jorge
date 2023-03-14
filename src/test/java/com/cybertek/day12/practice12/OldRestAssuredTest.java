package com.cybertek.day12.practice12;

import com.cybertek.utilities.SpartanNewBase;
import io.restassured.filter.log.LogDetail;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.regex.Matcher.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;


public class OldRestAssuredTest extends SpartanNewBase {
    @Test
    public void getAllSpartan(){

        given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin","admin")
                .log().all()
                .when()
                .get("/spartans")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id[0]",is(10))
                .body("id[5]",is(199))
                .log().all();



    }

@Test
    public void test2(){


    given()
            .accept(ContentType.JSON)
                .and()
                .auth().basic("admin","admin")
                .log().all()
        .expect()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .body("id[0]",is(10))
                .body("id[0]",is(155))
                .logDetail(LogDetail.ALL)
                .when()
                .get("/spartans");





}




}
