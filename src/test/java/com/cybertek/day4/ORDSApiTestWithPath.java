package com.cybertek.day4;



import com.cybertek.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sun.management.snmp.jvminstr.JvmOSImpl;

import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ORDSApiTestWithPath extends HrTestBase {

    @DisplayName("Get request to countries with path Method")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":2}")

                .when().get("/countries");

        assertEquals(200, response.statusCode());

        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        //print first country id
        String firstCountryId = response.path("items[0].country_id");
        System.out.println("firstCountryId = " + firstCountryId);

        //print second county name

        String secondCountyName = response.path("items[1].country_name");
        System.out.println("secondCountyName = " + secondCountyName);

        //print "http://100.26.48.87:1000/ords/hr/countries/CA"

        String hrefLinks = response.path("items[2].links[0].href");
        System.out.println("hrefLinks = " + hrefLinks);

        //get me all the countries names
        // when there is an ArrayList and you don't point the index it will return the entire list of the request
        List<String> countryNames = response.path("items.country_name");
        System.out.println("countryNames = " + countryNames);

        //assert that all regions' id s are equal to 2
        //for task about we need to use a ListArray to retrieve all the regions value,
        // and we are able to achieve it because we are not declaring any indexes
        List<Integer> allRegionsIDs = response.path("items.region_id");
        System.out.println("allRegionsIDs = " + allRegionsIDs);

        // then to get assertions we use a eachLoop because we already are working with ArrayList
        for (Integer eachRegionIDs : allRegionsIDs) {
            //we are making doing individual verification everytime the loops alterate
            System.out.println("eachRegionIDs = " + eachRegionIDs);
            assertEquals(2, eachRegionIDs);

        }
    }

    @DisplayName("Get request to /employees with query param")
    @Test
    public void test2() {

        // send a Get request to employees and get only employees whose works as an IT_PROG

        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("q", "{\"job_id\": \"IT_PROG\"}");

        Response response = given().log().all()
                .accept(ContentType.JSON)
                .and().queryParams(queryMap)
                .when()
                .get("/employees");

        assertEquals(200, response.statusCode());

        assertEquals("application/json", response.contentType());

        assertTrue(response.body().asString().contains("IT_PROG"));

        //make sure we have only IT_PROG as a job_ID
        List<String> allJobIds = response.path("items.job_id");
        for (String jobId : allJobIds) {
            System.out.println("jobId = " + jobId);
            assertEquals("IT_PROG", jobId);

        }
    }

    //task
    //print each name of IT_PROGs
    @DisplayName("Get request to /employees with query param")
    @Test
    public void test3() {

        // send a Get request to employees and get only employees whose works as an IT_PROG

        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("q", "{\"job_id\": \"IT_PROG\"}");

        Response response = given().log().all()
                .accept(ContentType.JSON)
                .and().queryParams(queryMap)
                .when()
                .get("/employees");

        assertEquals(200, response.statusCode());

        assertEquals("application/json", response.contentType());

        assertTrue(response.body().asString().contains("IT_PROG"));
        //task
        //print each name of IT_PROGs

        //make sure we have only IT_PROG names
        List<String> firstName = response.path("items.first_name");
        for (String name : firstName) {
            System.out.println("IT_PROG " + name);



        }

        }

            //task
            //print each name of IT_PROGs

        }











