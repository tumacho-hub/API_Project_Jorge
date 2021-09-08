package com.cybertek.day11;

import com.cybertek.utilities.ExcelUtil;
import io.restassured.http.ContentType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import static java.util.regex.Matcher.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class BookItParameterized {

    //we are combining excel and api example and sending request

    //code below we are retrieving information from excelSheet and putting it in java

    public static List<Map<String,String>> getExcelData(){
        ExcelUtil bookIt = new ExcelUtil("src/test/resources/BookItQa3.xlsx","QA3");
        return bookIt.getDataList();

    }
    //then we need to create another test
    @ParameterizedTest
    @MethodSource("getExcelData")
    public void bookItTest(Map<String,String> user){

        System.out.println("user.get(\"email\") = " + user.get("email"));
        System.out.println("user.get(\"password\") = " + user.get("password"));

        given()
                .accept(ContentType.JSON)
                .baseUri("https://cybertek-reservation-api-qa3.herokuapp.com")
                .queryParams(user) //I pass map directly because query param keys and map keys are equal
                .when()
                .get("/sign")
                .then()
                .statusCode(200)
                .log().body();



    }


}
