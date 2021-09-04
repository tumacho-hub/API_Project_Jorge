package com.cybertek.day5;

import com.cybertek.utilities.SpartanTestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static java.util.regex.Matcher.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;


public class JsonToJavaTest extends SpartanTestBase {

    @DisplayName("Get one Spartan and deserialize to map")
    @Test
    public void oneSpartanToMap(){

        Response response = given().pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).extract().response();

        //get the json and convert it to Map
        Map<String,Object> jsonMap = response.as(Map.class);

        System.out.println(jsonMap);

        //after we got the map, we can use hamcrest or junit assertions to do assertion
        String actualName = (String) jsonMap.get("name");
        assertThat(actualName,is("Meta"));

        /*
        java.lang.IllegalStateException:
        Cannot parse object because no JSON deserializer found in classpath.
        Please put either Jackson (Databind) or Gson in the classpath.

        ** This error we are getting because we have to add Gson dependencies
        Jackson Databind » 2.12.4
         */

    }
    @DisplayName("Get all spartans to Java data structure")
    @Test
    public void getAllSpartan(){

        Response response = get("/api/spartans").then().statusCode(200).extract().response();

        //we need to convert json to java which is deserialized how?

        List<Map<String,Object>> jsonList = response.as(List.class);
        System.out.println(jsonList.get(1).get("name"));

        Map<String,Object> spartan3 = jsonList.get(2);
        System.out.println(spartan3);

    }



}
