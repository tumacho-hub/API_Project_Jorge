package com.cybertek.day7;

import com.cybertek.pojo.Spartan;
import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.regex.Matcher.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanPostRequestDemo extends SpartanTestBase {
    /*
    given accept type and Content type is json
    And request json body is:

      {
        "name": "Mike",
        "gender": "Male",
        "phone": 3214231232
      }
    When user sends POST request to /api/spartans
    Then status code 201
    And content type should be application/json
    And json payload?response/body should contain:
    "A Spartan is born!" message
    and same data what is posted
     */
    @Test
    public void postMethod1() {
        //this is how to send a post request from java to api
        //code below is sending our body request
        String requestJsonBody = "  {   \n" +
                "        \"name\": \"Mike\",\n" +
                "        \"gender\": \"Male\",\n" +
                "        \"phone\": 3214231232\n" +
                "      }";

        Response response = given().accept(ContentType.JSON).and()
                .contentType(ContentType.JSON)
                .body(requestJsonBody)
                .when()
                .post("/api/spartans");

        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";
        assertThat(response.path("success"), is(expectedResponseMessage));
        assertThat(response.path("data.name"), is("Mike"));
        assertThat(response.path("data.gender"), is("Male"));
        assertThat(response.path("data.phone"), is(3214231232L));

    }

    @DisplayName("Post with Map to Json")
    @Test
    public void postMethod2() {

        //second way to create a post with Map -->sending a json with Map! :)
        Map<String, Object> requestJsonMap = new LinkedHashMap<>();
        requestJsonMap.put("name", "JayCole");
        requestJsonMap.put("gender", "Male");
        requestJsonMap.put("phone", 3126547891L);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(requestJsonMap).log().all()
                .when()
                .post("/api/spartans");

        //verify status code
        assertThat(response.statusCode(), is(201));
        assertThat(response.contentType(), is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";
        assertThat(response.path("success"), is(expectedResponseMessage));
        assertThat(response.path("data.name"), is("JayCole"));
        assertThat(response.path("data.gender"), is("Male"));
        assertThat(response.path("data.phone"), is(3126547891L));

        response.prettyPrint();
/*
//        Map<String, Object> requestJsonMap = new LinkedHashMap<>();
//        requestJsonMap.put("name", "jayCole");
//        requestJsonMap.put("gender", "Male");
//        requestJsonMap.put("phone", 123456789L);
//
////        Spartan spartan = new Spartan();
////        spartan.setName("jayCole");
////        spartan.setGender("Male");
////        spartan.setPhone(123456789L);
////        System.out.println(spartan);
//
//        Response response = given().accept(ContentType.JSON)
//                .and().contentType(ContentType.JSON)
//                .body(requestJsonMap).log().all()
//                .when()
//                .post("/api/spartans");
//
//        //verify status code
//        assertThat(response.statusCode(), is(201));
//        assertThat(response.contentType(), is("application/json"));
//
//        String expectedResponseMessage = "A Spartan is Born!";
        assertThat(response.path("success"), is(expectedResponseMessage));
        assertThat(response.path("data.name"), is("jayCole"));
        assertThat(response.path("data.gender"), is("Male"));
        assertThat(response.path("data.phone"), is(123456789L));

 */
    }
    /*
    HomeWork
    Create one SpartanUtils class
    create a static method that will return Map<String,Object>
    use faker library (add as a dependency) to assign each time different information
    for name,gender,phone number
    then use your method for creating spartan as a map,
*/
    @DisplayName("Post with Map to Spartan Class ")
    @Test
    public void postMethod3(){

        //create one object from your pojo, send it as a JSON
        Spartan spartan = new Spartan();
        spartan.setName("CardyB");
        spartan.setGender("Female");
        spartan.setPhone(1234567888);

        System.out.println("spartan = " + spartan);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(spartan).log().all()
                .when()
                .post("/api/spartans");

        //verify status code
        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";
        assertThat(response.path("success"),is(expectedResponseMessage));
        assertThat(response.path("data.name"),is("CardyB"));
        assertThat(response.path("data.gender"),is("Female"));
        assertThat(response.path("data.phone"),is(1234567888));

        response.prettyPrint();

    }
    @DisplayName("Post with Map to Spartan Class")
    @Test
    public void postMethod4(){
        //when we send a post request we have to make sure is successfully posted
        // how can we make sure ? code below will get you the results

        //example below we are implementing  serialization creating spartan object sending as a request body
        //also implementing deserialization getting the id, sending get request and saving that body as a response

        // creating new object from pojo and sending it as json
      Spartan spartan = new Spartan();
      spartan.setName("LittleWayne");
      spartan.setGender("Male");
      spartan.setPhone(1234565855L);

        System.out.println("spartan = " + spartan);
        String expectedResponseMessage = "A Spartan is Born!";

        int idFromPost = given().accept(ContentType.JSON).and()
                .contentType(ContentType.JSON)
                .body(spartan).log().all()
                .when()
                .post("/api/spartans")
                //line above is about my post request only

                // code below we are verifying body, and we need to extract the id and save it into a variable
                //then we save it all into a variable
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body("success", is(expectedResponseMessage))
                .extract().jsonPath().getInt("data.id");

        System.out.println("idFromPost = " + idFromPost);

        //then we sand the request to id and do verification
        Spartan spartanPosted = given().accept(ContentType.JSON)
                .and().pathParam("id", idFromPost)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).log().all().extract().as(Spartan.class);

        assertThat(spartanPosted.getName(),is(spartan.getName()));
        assertThat(spartanPosted.getGender(),is(spartan.getGender()));
        assertThat(spartanPosted.getPhone(),is(spartan.getPhone()));
        assertThat(spartanPosted.getId(),is(idFromPost));



    }

}
