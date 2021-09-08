package com.cybertek.day6;

import com.cybertek.pojo.Search;
import com.cybertek.pojo.Spartan;
import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;


public class SpartanPojoGetRequestTest extends SpartanTestBase {
    @DisplayName("get one spartan and convert it to Spartans Object")
    @Test
    public void oneSpartanPojo(){

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .log().all()
                .extract().response();

        //deserialization -->json to pojo (plain old java object) -->(java custom class)
        //we have two different ways to deserialization
        //1- using as() method
        //we convert json response to spartant object with the help of jackson
        //as() method uses jackson to deserialization (converting json to java class
        Spartan spartan15 = response.as(Spartan.class);
        System.out.println(spartan15);
        System.out.println(spartan15.getId());
        System.out.println(spartan15.getPhone());

        //second way of deserialization to java
        //2- using jsonPath to deserialize to custom class
        JsonPath jsonPath = response.jsonPath();

        Spartan s15 = jsonPath.getObject("",Spartan.class);
        System.out.println(s15);
        System.out.println(s15.getPhone());
        System.out.println(s15.getId());


    }

    @Test
    public void spartanSearchWithPojo(){

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and().queryParams("nameConatains", "a",
                        "gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().jsonPath();

        //get the first spartan from content list and put inside spartan object
        Spartan spartan = jsonPath.getObject("content[0]",Spartan.class);
        System.out.println(spartan);
        System.out.println(spartan.getName());
        System.out.println(spartan.getGender());

    }
    @Test
    public void test3(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "a",
                        "gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(200)
                .extract().response();

        Search searchResult = response.as(Search.class);
        searchResult.getContent().get(0).getName();
        System.out.println(searchResult.getContent().get(0).getName());


    }
    @DisplayName("Get /spartant/search and save as List<Spartan>")
    @Test
    public void test4(){

        List<Spartan> spartanList = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "a",
                        "gender", "Male")
                .when().get("/api/spartans/search")
                .then().statusCode(201)
                .extract().jsonPath().getList("content", Spartan.class);

        System.out.println(spartanList.get(1).getName());


    }


}
