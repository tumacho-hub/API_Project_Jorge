package com.cybertek.day4;

import com.cybertek.utilities.HrTestBase;

import com.cybertek.utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ORDSApiWithJsonPath extends HrTestBase {

    @DisplayName("Get request to Countries")
    @Test
    public void test1(){

        // with jsonPath we are simplifying our code next line we are sending  whole request to dataBase
        Response response = get("/countries");

        //then to continue use jsonPath we have to assign response to jsonPath how? nextLine
        JsonPath jsonPath = response.jsonPath();

        //get second country name with JsonPath
        String secondCountry = jsonPath.getString("items[1].country_name");
        System.out.println("secondCountry = " + secondCountry);

        //get all countries IDs
        //items.county_id
        List<Object> allCountriesIDs = jsonPath.getList("items.country_id");
        System.out.println("allCountriesIDs = " + allCountriesIDs);

        //get all countries names where their region id is equal to 2 with jsonPath
        //("items.findAll{it.region_id==2}.country_name");--> we can implement this line of code anywhere
        //("items.findAll{it.region_id==2}.country_name");--> it works like a mini loop!!!
        List<Object> countryNameWithRegionId2 = jsonPath.getList("items.findAll{it.region_id==2}.country_name");
        System.out.println("countryNameWithRegionId2 = " + countryNameWithRegionId2);


    }

    @DisplayName("Get request /employees with query param ")
    @Test
    public void test2(){

        //we added limit query param to get 107 employees
        //basically we just reset it because it had a value of 25, and it jumped back to 107
        Response response = given().queryParam("limit", 107)
                .when().get("/employees");

        //before using jsonPath we need to pass response value to jsonPath
        JsonPath jsonPath = response.jsonPath();

        //get me all the email of employees who is working as IT_PROG
        //again we use the wonderful method from jsonPath mini loop
        List<Object> employeeITProgs = jsonPath.getList("items.findAll{it.job_id==\"IT_PROG\"}.first_name");
        System.out.println(employeeITProgs);

        //get me first name of employees who is making more than 10000
        List<String> salary = jsonPath.getList("items.findAll{it.salary<10000}.first_name");
        System.out.println(salary);


        String kingFirstName = jsonPath.getString("items.max {it.salary}.first_name");
        System.out.println(kingFirstName);


    }


}
