package com.cybertek.day10;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.regex.Matcher.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class FormulaOneXmlTest {

    @BeforeAll
    public static void setUp(){
        //http://ergast.com/api/f1/drivers/alonso
        baseURI = "http://ergast.com";
        basePath = "/api/f1";

    }

    @DisplayName("How to get attributes")
    @Test
    public void test1(){


        Response response = given()
                .pathParam("driver", "alonso")
                .when()
                //second {driver} is passing alonso's name
                .get("/drivers/{driver}")
                .then().statusCode(200).log().all()
                .extract().response();

             /*
        <?xml version="1.0" encoding="utf-8"?>
<?xml-stylesheet type="text/xsl" href="/schemas/mrd-1.4.xsl"?>
<MRData xmlns="http://ergast.com/mrd/1.4" series="f1" url="http://ergast.com/api/f1/drivers/alonso" limit="30" offset="0" total="1">
    <DriverTable driverId="alonso">
        <Driver driverId="alonso" code="ALO" url="http://en.wikipedia.org/wiki/Fernando_Alonso">
            <PermanentNumber>14</PermanentNumber>
            <GivenName>Fernando</GivenName>
            <FamilyName>Alonso</FamilyName>
            <DateOfBirth>1981-07-29</DateOfBirth>
            <Nationality>Spanish</Nationality>
        </Driver>
    </DriverTable>
</MRData>
         */

        XmlPath xmlPath = response.xmlPath();
        //get given name
        String givenName = xmlPath.getString("MRData.DriverTable.Driver.GivenName");

        System.out.println("givenName = " + givenName);

        //get family name
        String familyName = xmlPath.getString("MRData.DriverTable.Driver.FamilyName");
        System.out.println("familyName = " + familyName);

        //get driver id is attribute, and we need to get attribute value!! how do we extract it
        String driverId = xmlPath.getString("MrData.DriverTable.Driver.@driverId");
        System.out.println("driverId = " + driverId);

        //get code
        String code = xmlPath.getString("MrData.DriverTable.Driver.@code");
        System.out.println("code = " + code);


        //get url
        String url = xmlPath.getString("MrData.DriverTable.Driver.@url");
        System.out.println("url = " + url);


    }
    @DisplayName("Get request to HTML ")
    @Test
    public void test2(){

        //send  a get request to
        //https://data.act.gov/api/views/qm34-pq7e/rows/.xml
        //get all the years
        //get all the unknowns
        //get 2006 other
        //get 2007_address

        Response response = get("https://data.act.gov/api/views/qm34-pq7e/rows.xml").then()
                .statusCode(200).extract().response();

        XmlPath xmlPath = response.xmlPath();

        //get all the years
        List<Integer> years = xmlPath.getList("response.row.row.year");
        System.out.println("years = " + years);

        //get all the unknowns
        List<Integer> unknowns = xmlPath.getList("response.row.row.unknown");
        System.out.println("unknowns = " + unknowns);

        //get 2005 other
        int other2005 = xmlPath.getInt("response.row.row[2].other");
        System.out.println("other2005 = " + other2005);

        //get 2007_address
        String address2007 = xmlPath.getString("response.row.row[4].@address");
        System.out.println("address2007 = " + address2007);



    }

    }
