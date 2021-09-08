package com.cybertek.day8;


import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.util.regex.Matcher.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
public class BookItTest {

    //basically we are creating static method that will execute our baseUrl code line
    @BeforeAll
    public static void init() {
        baseURI = "https://cybertek-reservation-api-qa.herokuapp.com";


    }
    //first thing to do is to provided our token and make it instance variable so it could be access anywhere
    String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMzkiLCJhdWQiOiJzdHVkZW50LXRlYW0tbGVhZGVyIn0._vM1-eRoS7SsHu6T-QPdJoEdA8LSwnxUvvTTbhV-8ms";

    @DisplayName("Get all campuses")
    @Test
    public void testAuth(){
        //this is the way to pass our token with the access header



        given().header("Authorization",token)
                .and().accept(ContentType.JSON)
                .when()
                .get("/api/campuses")
                .then()
                .statusCode(200).log().all();

        //create bookItUtil create a method, that accepts email and password return token bearer + your token as a string


/*
      //  http://100.26.48.87:1000
        http://100.26.48.87:1000/ords/hr       /employees?limit=100


 */



    }
    }
