package com.cybertek.day4;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.DatabaseMetaData;

import static io.restassured.RestAssured.baseURI;

public class CBTrainingApiWithJsonPath {

    //basically we are creating static method that will execute our baseUrl code line
    @BeforeAll
    public static void init() {
        baseURI = "http://api.cybertektraining.com";
    }
    @DisplayName("Get Request to individual student")
    @Test
    public void test1(){
        /*
        send a get request to student id 23401 as apath parameter and accept header application/json
        verify status code 200 /content type=application/json;charset=UTF-8 /Content-Encoding ==gzip
        verify Date header exists
        assert that:
        firstName Vera
        batch 14
        section 12
        emailAddress aaa@gmail.com
        companyName Cybertek
        state il
        zipCode 60606

        using JsonPath
         */
    }
}
