package com.cybertek.day5;

import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.regex.Matcher.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanHamcrestTest extends SpartanTestBase {

    @DisplayName("Get spartan/search amd chainin together")
    @Test
    public void test1() {

        //along this statement i want to to save names inside the List<String>

        List<String> names = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "j", "gender", "Male")
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .and()
                .body("totalElement", is(6))
                .extract().response().jsonPath().getList("content.name");

        System.out.println(names.toString());


    }

    @DisplayName("Get spartan/search amd chainin together")
    @Test
    public void test2() {

        // save status code

        int statusCode = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "j", "gender", "Male")
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .and()
                .body("totalElement", is(6))
                .extract().response().statusCode();

        System.out.println(statusCode);


    }
}
