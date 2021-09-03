package com.cybertek.day3;



import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpartanTestWithPath {
    //basically we are creating static method that will execute our baseUrl code line
    @BeforeAll
    public static void init() {
        baseURI = "http://100.26.48.87:8000";
    }

    @DisplayName("Get one spartant with Path Method")
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

        assertEquals(200,response.statusCode());

        assertEquals("application/json",response.contentType());

        System.out.println("response.path(\"id\".toString()) = " + response.path("id".toString()));
        System.out.println("response.path(\"name\".toString()) = " + response.path("name".toString()));
        System.out.println("response.path(\"gender\".toString()) = " + response.path("gender".toString()));
        System.out.println("response.path(\"phone\".toString()) = " + response.path("phone".toString()));


        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);


        assertEquals(10,id);
        assertEquals("Lorenza",name);
        assertEquals("Female", gender);
        assertEquals(3312820936L, phone);


    }

    @DisplayName("Get specific json values ")
    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");

      //  response.prettyPrint();

        // in order to read specific values of the json body we need to read it with arrays[] index
        // this will jump to the desire point and retrieve the value of it

        int firstId = response.path("id[0]");
        System.out.println("firstId = " + firstId);

        String name = response.path("name[0]");
        System.out.println("name = " + name);

        String lastFirstName = response.path("name[-2]");
        System.out.println("lastFirstName = " + lastFirstName);

        //if you want to retrieve the entire list of names from documentation we have to use List<String>
        //because is going to loop through it

        List<String> names = response.path("name");
        System.out.print("names = " + names);

        // if we want to extract every single name we need to use  each loop

        for (String each : names){
            System.out.println(each);
        }



    }
}
