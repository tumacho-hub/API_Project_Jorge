package com.cybertek.day12;

import com.cybertek.utilities.BookItTestBase;
import com.cybertek.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static java.util.regex.Matcher.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class BookItSpecTest extends BookItTestBase {


    @Test
    public void test1() {
        //send a get request to /api/users/me endpoint as a teacher
        //verify status code and content type
        given()
                .spec(teacherReqSpec)
                .when()
                .get("/api/users/me")
                .then()
                .spec(responseSpec);


    }

    @Test
    public void test2() {


            //send a get request to /api/users/me endpoint as a teacher
            //verify status code and content type
//            given()
//                    .spec(studentMemberReqSpec)
//                    .when()
//                    .get("/api/users/me")
//                    .then()
//                    .spec(getDynamicResSpec(200))
//                    .body("firstName",is("Lorette"));



//        given()
//                .spec(studentMemberReqSpec)
//                .when()
//                .get("/api/users/me")
//                .then()
//                .spec(userCheck("Lorette","Bradnum"));

        given()
                .spec(userReqSpec("student-member"))
                .when()
                .get("/api/users/me")
                .then()
                .spec(userCheck("Lorette","Bradnum"));


    }
    }



