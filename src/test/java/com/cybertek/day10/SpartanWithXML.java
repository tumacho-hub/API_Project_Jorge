package com.cybertek.day10;

import com.cybertek.utilities.SpartanAuthBase;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.List;

import static java.util.regex.Matcher.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanWithXML extends SpartanAuthBase {

    @DisplayName("Get request to /api/spartans and verify xml")
    @Test
    public void getSpartanXml(){
        /*
          <item>
        <id>108</id>
        <name>Jack</name>
        <gender>Male</gender>
        <phone>2028884268</phone>
    </item>
         */
        // first thing will we do is to ask for xml response

        given()
                .accept(ContentType.XML)
                .and()
                .auth().basic("admin","admin")
                .when()
                .get("/api/spartans")
                .then()
                //assert status code 200
                .statusCode(200)
                // assert content  type is xml(we got xml response)
                .contentType("application/xml")
                .body("List.item[0].name",is("Jack"))
                .body("List.item[0].gender",is("Male"))
                .log().all();


    }
    @DisplayName("Get request /api/spartans with xmlPath")
    @Test
    public void testXmlPath(){
        /*
        List index2
           <item>
        <id>6</id>
        <name>Tedmund</name>
        <gender>Male</gender>
        <phone>2227140732</phone>
    </item>
         */

        Response response = given().accept(ContentType.XML)
                    .and()
                .auth().basic("admin", "admin")
                    .when()
                .get("/api/spartans");

        //get response xml body/payload and save inside the xmlpath object

        XmlPath xmlPath = response.xmlPath();

        //getting third spartan
          String name = xmlPath.getString("List.item[3].name");
        System.out.println("name = " + name);

        int id = xmlPath.getInt("List.item[3].id");
        System.out.println("id = " + id);

        //how to get all names and save into list of String
        List<String > names = xmlPath.getList("List.item.name");
        System.out.println(names);


    }

}
