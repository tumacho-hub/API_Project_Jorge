package com.cybertek.day4;


import com.cybertek.utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpartanWithJsonPath extends SpartanTestBase {

    @DisplayName("Get one spartan with JsonPath")
    @Test
    public void test1(){
         /*
        Given accept type is json
        And path param id is 10
        When user sends a get request to "api/spartan/{id}"
        Then status code is 200
        And content type is "application/json"
        And response paylod values match the following:
            id is 10,
            name is "Lorenza".
            gender is "Female"
            phone is 3312820936
         */

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",10 )
                .when()
                .get("/api/spartans/{id}");

        System.out.println(response.path("name").toString());

        //**NOW WE ARE SWITCHING TO JSONPATH  ANOTHER METHOD FROM RESTASSURED CLASS
        //so far we are getting same results as we were working with RestAssured
        //EXAM:

        JsonPath jsonPath = response.jsonPath();

        int id = jsonPath.getInt("id");
        String name = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        long phone = jsonPath.getLong("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);


    }
}
