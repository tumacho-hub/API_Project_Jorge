package com.cybertek.day6;

import com.cybertek.pojo.Employee;
import com.cybertek.pojo.Region;
import com.cybertek.pojo.Regions;
import com.cybertek.utilities.HrTestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.regex.Matcher.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSPojoGetRequestTest extends HrTestBase {
    @Test
    public void regionTest(){
        JsonPath jsonPath = get("/regions").then().statusCode(200).extract().jsonPath();

        Region region = jsonPath.getObject("items[0]",Region.class);
        System.out.println("region = " + region);
        System.out.println("region.getRegionId() = " + region.getRegionId());
        System.out.println("region.getRegion_name() = " + region.getRegion_name());
        System.out.println("region.getLinks().get(0).getHref() = " + region.getLinks().get(0).getHref());


    }

    @DisplayName("Get request /employees and only get couple of values as a Pojo Class")
    @Test
    public void employeeGet(){
        Employee employee = get("/employees").then().statusCode(200)
                .extract().jsonPath().getObject("items[0]", Employee.class);
        System.out.println("employee = " + employee);


    }
    /*
    send  a get request to regions
    verify that region ids are 1,2,3,4
    verify that regions names Europe, Americas ,Asia Middle East and Africa
    verify that count is 4
    try to use pojo as much as possible
    ignore non use fields
     */

    @Test
    public void regionPojoTest(){
        //send a get request and save everything inside the regions object
        //since we prepare pojo also for the all properties we dont need to use any path so
        // as() method is enough

        Regions regions = get("/regions").then().statusCode(200).extract().as(Regions.class);

        //verify count is 4
        assertThat(regions.getCount(),is(4));

        //create empty list to store values
        List<String> regionNames = new ArrayList<>();
        List<Integer> regionIds = new ArrayList<>();

        //get list of regions out of regions object
        List<Region> items = regions.getItems();

        //loop thorugh each of the region, save their ids and names to empty list that we prepare
        for(Region region : items){
            regionIds.add(region.getRegionId());
            regionNames.add(region.getRegion_name());
        }

        System.out.println("regionIds = " + regionIds);
        System.out.println("regionNames = " + regionNames);

        List<Integer> expectedRegionIds = Arrays.asList(1,2,3,4);
        List<String> expectedRegionNames = Arrays.asList("Europe","Americas","Asia","Middle East and Africa");

        assertThat(regionIds,is(expectedRegionIds));
        assertThat(regionNames,is(equalTo(expectedRegionNames)));



    }

}
