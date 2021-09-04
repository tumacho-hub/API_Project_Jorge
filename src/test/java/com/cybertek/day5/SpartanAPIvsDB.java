package com.cybertek.day5;

import com.cybertek.utilities.DBUtils;
import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.xml.ws.Response;
import java.util.Map;

import static java.util.regex.Matcher.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;


public class SpartanAPIvsDB extends SpartanTestBase {

    @DisplayName("Get one spartan from api and dataBase")
    @Test
    public void test1(){

        //get id, gender,phone from database
        //get id, gender,phone from API
        // compare

         // 1- first thing we need to do is to Establish connection with database sending our query

        String query = "select spartan_id,name,gender,phone from spartans\n" +
                "where spartan_id = 15";

        //next step save data inside the Map

        Map<String,Object> dbMap = DBUtils.getRowMap(query);
        System.out.println(dbMap);

        // 2- next we are getting info from API
        Map apiMap = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .and().contentType("application/json")
                .extract().response().as(Map.class);
        System.out.println(apiMap);

        // we are implementing deserialization --> json to java with jackson objectMapper

        //3- compare database vs api --> we assume expected result is database
        assertThat(apiMap.get("id").toString(),is(dbMap.get("SPARTAN_ID").toString()));
        assertThat(apiMap.get("name").toString(),is(dbMap.get("NAME").toString()));
        assertThat(apiMap.get("gender").toString(),is(dbMap.get("GENDER").toString()));
        assertThat(apiMap.get("phone").toString(),is(dbMap.get("PHONE").toString()));





    }
}
