package com.cybertek.day5;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.util.regex.Matcher.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class HamcrestApiTest {
    @DisplayName("one spartan with Hamcrest and chaining")
    @Test
    public void test1(){


        /*
        Given accept type is json
        And path param id is 15
        When user sends a get request to "api/spartan/{id}"
        Then status code is 200
        And content type is "application/json"
        And json data has following:
            id is 15,
          "id": 15,
    "name": "Meta",
    "gender": "Female",
    "phone": 1938695106
         */

        given().accept(ContentType.JSON)
                .and().pathParam("id",15)
                .when()
                     .get("http://100.26.48.87:8000/api/spartans/{id}")
                //code above us is just send request like we have be learning

                //code below is our response for the rest of the code
                .then()
                      .statusCode(200)
                      .and().assertThat()
                      .contentType("application/json")
                      .and()
                      .body("id",equalTo(15),
                                "name",is("Meta"),
                                "gender",is("Female"),
                                 "phone",is(1938695106));

    }
    @DisplayName("CBTraining Teacher request with chaining and matchers")
    @Test
    public void teacherData(){

        given().accept(ContentType.JSON)
                .and()
                .pathParam("id",10423)
                .when()
                .get("http://api.cybertektraining.com/teacher/{id}")
                //request on top

                //structure divides into two parts..

                //response under
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json;charset=UTF-8")
                .and()
                .header("Content-Length",is("236"))
                .and().assertThat()
                .body("teachers[0].firstName",is("Alexander"))
                .body("teachers[0].lastName",is("Syrup"))
                .body("teachers[0].gender",equalTo("male"));
    }

@DisplayName("Get request to teacher/all and chaining")
    @Test
    public void teachersTest(){

        //verify Alexander,Darleen,Sean inside the all teachers using hamcrest

    given().accept(ContentType.JSON)
            .and()
            .when()
            .get("http://api.cybertektraining.com/teacher/all")
            .then()
            .statusCode(200)
            .and()
            .body("teachers.firstName",hasItems("Alexander","Darleen","Sean","Jamal"));




}



}
