package com.cybertek.day11.practice11;

import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.regex.Matcher.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class CvsSourceParameterizedTest {
    /*
    Test fist number + second number = third number
    1, 2, 4
    2, 3, 5
    8, 7, 15
    10, 9 19
     */



  @ParameterizedTest
  //CSV <-- comma separate values
    @CsvSource({"1, 3, 4",
                "2, 3, 5",
                "8, 7, 15",
                "10, 9, 19"})
    public void testAddition(int num1, int num2, int sum){
      System.out.println("num1 = " + num1);
      System.out.println("num2 = " + num2);
      System.out.println("sum = " + sum);

      //assert num1 + num2 = 4
        assertThat(num1+num2,equalTo(sum));



  }

    /*
       // Write a parameterized test for this request
    // GET https://api.zippopotam.us/us/{state}/{city}
    /*
        "NY, New York",
        "CO, Denver",
        "VA, Fairfax",
        "VA, Arlington",
        "MA, Boston",
        "NY, New York",
        "MD, Annapolis"
     */
    //verify place name contains your city name
    //print number of places for each request

    @ParameterizedTest
    @CsvSource({
            "NY, New York",
            "CO, Denver",
            "VA, Fairfax",
            "VA, Arlington",
            "MA, Boston",
            "NY, New York",
            "MD, Annapolis"
    })
    public void zipCodeMultipleInputTest(String state, String city){
      System.out.println("state = " + state);
      System.out.println("city = " + city);

        int placeNumber  = given()
                .baseUri("https://api.zippopotam.us") //<-- our url raw because we haven't created a method for it
                .accept(ContentType.JSON)//<-- our format
                .pathParam("state", state) //<-- parameter we are sending to postman
                .pathParam("city", city)//<-- parameter we are sending to postman
                .log().uri()//<-- it returns the url
                .when()//<--syntactic sugar method
                .get("/us/{state}/{city}")//<-- our end point to postman
                .then()//<-- Returns a validatable response that's lets you validate the response. In other words
                // a continues to keep chaining and be abl;e to validate in the same response
                .statusCode(200)//<-- our expected result
                .and()//<-- syntactic sugar
                /*
                for the line below this is what is happening
.body("places.'place name'", everyItem(containsStringIgnoringCase(city)))
    .body <-- this method goes inside our postman to retrieve certain information
    "places. <-- is the main json-object
    'place name' <-- places name with single quotation means we are retrieving the specify value
    and since this value has a space in between that's the reason we are using single quotations to make up
    for the space in between
    everyItem method <-- is the correct method to choose because we are diving into a json-object list
    and since we want to iterate through each item is like using for each loop
    containsStringIgnoringCase method <-- just accepting any values upper or lower cases
    city variable <-- is one of the arguments from our method above
    the rest of the code is business as usual and for the last line
    below is an example of how we are navigating through the json-object
    {
    "country abbreviation": "US",
    "places": [
        {
            "place name": "New York City",
            "longitude": "-73.6731",
            "post code": "10000",
            "latitude": "40.7069"
        },



     .getList("places").size();
    .getList method <-- will get us the entire list from our json-object
     .size() method <-- will get us the number of elements in the list
                 */
                .body("places.'place name'", everyItem(containsStringIgnoringCase(city)))
              //  .log().all()
                .extract()
                .jsonPath()
                .getList("places").size();

        System.out.println("placeNumber = " + placeNumber);

    }












}
