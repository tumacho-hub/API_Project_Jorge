package com.cybertek.day5;

import com.cybertek.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.regex.Matcher.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
public class ORDSHamcrestTest extends HrTestBase {

    @DisplayName("Get")
    @Test
    public void employeesTest(){
        //send a get request to employees endpoint with query parameter job_id IT_PROG
        //verify each job_id is IT_PROG
        //verify first names are..(find proper method to check list against list)
        //verify emails without checking order (provide emails in different order,just make sure it has same emails)

        //expected names
     //  List<String> names = Arrays.asList("Alexander","Neena","Lex","Alexander","Bruce");

        given().accept(ContentType.JSON)
                .and().queryParam("q","{\"job_id\" : \"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id",everyItem(equalTo("IT_PROG")))
               // .body("items.first_name",containsInRelativeOrder("Alexander","Neena","Lex","Alexander","Bruce"))
                .body("items.email",containsInRelativeOrder("AHUNOLD", "BERNST", "DAUSTIN", "VPATABAL", "DLORENTZ"));
             //   .body("items.first_name",equalToObject(names));

        //its failing double check
        // "Steven","Neena","Lex","Alexander","Bruce"
//"SKING","NKOCHHAR","LDEHAAN","AHUNOLD","BERNST"
    }
@Test
    public void test2() {
    //we want to chain and also get response object
    Response response = given().accept(ContentType.JSON)
            .and().queryParam("q", "{\"job_id\" : \"IT_PROG\"}")
            .when()
            .get("/employees")
            .then()
            .statusCode(200)
            .extract().response();

    response.prettyPrint();

}
@Test
    public void test3(){

    int statuscode= given().accept(ContentType.JSON)
            .and().queryParam("q", "{\"job_id\" : \"IT_PROG\"}")
            .when()
            .get("/employees")
            .then()
            .statusCode(200)
            .extract().statusCode();

    System.out.println(statuscode);
}

@Test
    public void test4(){

    JsonPath jsonPath = given().accept(ContentType.JSON)
            .and().queryParam("q", "{\"job_id\" : \"IT_PROG\"}")
            .when()
            .get("/employees")
            .then()
            .statusCode(200)
            .extract().jsonPath();

    //with jsonPath we can also do assert!
    // we can use extract() --> method that allows us to get response object after we use then() method

            //assert that we have only 5 firstNames
            assertThat(jsonPath.getList("items.first_name"),hasSize(5));

            //assert firstNames orders
            assertThat(jsonPath.getList("items.first_name"),containsInRelativeOrder("Alexander","Bruce","David","Valli","Diana"));




}
}


