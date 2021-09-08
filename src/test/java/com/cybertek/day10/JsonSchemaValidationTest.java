package com.cybertek.day10;

import com.cybertek.utilities.SpartanAuthBase;
import io.restassured.http.ContentType;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static java.util.regex.Matcher.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class JsonSchemaValidationTest extends SpartanAuthBase {

    @DisplayName("Get request to verify one spartan against to schema")
    @Test
    public void schemaValidation(){

        given().accept(ContentType.JSON)
                .and()
                .pathParam("id",35)
                .and()
                .auth().basic("admin","admin")
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                //how to verify the response we are getting against the schema we save in the resource  file
                //code below will help us do it but schema file has to be under resources file
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanSchema.json")).log().all();

    }

    @DisplayName("Get request to all spartans and verify schema")
    @Test
    public void allSpartanSchemaTest(){
        // if we have the schema file on the same level as a class, to do verification
        //we need to pass the path of the file where is located

        given().accept(ContentType.JSON)
                .auth().basic("admin","admin")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                //below is the line we need to change while making verification when the schema file is not under resources file
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/com/cybertek/day10/allSpartansSchema.json"))).log().all();
    }

    //homework
    //put your post json schema under day10
    //post one spartan using dynamic input(name,gender,phone)
    //verify your post response matching with json schema
}
