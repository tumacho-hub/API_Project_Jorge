package com.cybertek.day7;

import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.regex.Matcher.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class PutAndPatchRequestDemo extends SpartanTestBase {

    @DisplayName("Put request to one spartant for update with Map")
    @Test
    public void PutRequest(){
        //just like post request qwe have different options to send bode, we will go with map

        Map<String,Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("name","LittleWayne");
        putRequestMap.put("gender", "Male");
        putRequestMap.put("phone",1234565773L);

        given().and()
                .contentType(ContentType.JSON)
                .body(putRequestMap).log().all()
                .and().pathParam("id", 155)
                .when().put("/api/spartan/{id}")
                .then()
                .statusCode(204);

        //send a Get request after update, make sure updated filed changed, or the new info matching
        //with request Body that we send




    }
    @DisplayName("PATCH request to one spartan for partial update with map")
    @Test
    public void PatchRequest(){
        // just like post request we have different options to send body, we will go with map
        Map<String,Object> putRequestMap = new LinkedHashMap<>();

        putRequestMap.put("phone",1234565773L);

        given().and()
                .contentType(ContentType.JSON)
                .body(putRequestMap).log()
                .body()
                .and().pathParam("id", 155)
                .when().patch("/api/spartan/{id}")
                .then()
                .statusCode(204);

    }

    @DisplayName("Deteled request")
    @Test
    public void deleteRequest(){

        int idToDetele = 5;
        given().pathParam("id",idToDetele)
                .when().delete("/api/spartan/{id}")
                .then().statusCode(204);

       // send a get request and make sure you are getting 404

        }


    }




