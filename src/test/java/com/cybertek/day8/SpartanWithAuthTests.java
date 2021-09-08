package com.cybertek.day8;

import com.cybertek.utilities.SpartanAuthBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.util.regex.Matcher.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanWithAuthTests extends SpartanAuthBase {

    @DisplayName("Get /api/spartan as a public user(guest)")
    @Test
    public void test1(){
        //we are sending a simple request

        given().accept(ContentType.JSON)
                .when()
                .get("/api/spartans")
                .then().statusCode(401)
                .log().all();

    }
@DisplayName("Get /api/spartans as admin user expect 200")
    @Test
    public void testAdmin(){
        //how to pass admind admind as a username and password?

    given()
            .auth().basic("admin","admin")
            .and().accept(ContentType.JSON)
            .when()
            .get("/api/spartans")
            .then()
            .statusCode(200)
            .log().all();

    }

    @DisplayName("Delete /spartans/{id} as editor user expected 403")
    @Test
    public void testEditorDelete(){

        given()
                .auth().basic("editor","editor")
                .and().accept(ContentType.JSON)
                .and().pathParam("id", 30)
                .when()
                .delete("/api/spartans/{id}")
                .then()
                .statusCode(403).log().all();

/*
As a homework, write a detailed test for Role Base Access Control (RBAC)
in Spartan Auth app (7000)
Admin should be able to take all CRUD
Editor should be able to take all CRUD
other than DELETE

User should be able to only READ data
not update, delete ,create (POST,PUT,PATCH,DELETED)
------------------------------------------------
can guest even read data? 401 for all
 */


    }




}
