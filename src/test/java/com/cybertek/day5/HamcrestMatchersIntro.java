package com.cybertek.day5;

import com.sun.xml.internal.ws.server.ServerRtException;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

import static java.util.regex.Matcher.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class HamcrestMatchersIntro {

    @Test
    public void test1(){

        //actual 5+5
       assertThat(5+5,is(10));
       assertThat(5+5,equalTo(10));
       assertThat(5+5,is(equalTo(10)));
       //matchers has 2 overloaded version
       //first that accept actual value
       //second that accept another matchers
       //below examples is method is accepting another matchrs aqual to make it reable

        assertThat(5+5,not(9));
        assertThat(5+5,is(not(9)));
        assertThat(5+5,is(not(equalTo(9))));

        //number comparison
       // greaterThan()
       // greaterThanOrEqualTo()
        //  lessThan()
        //lessThanOrEqualTo()

        assertThat(5+5,is(greaterThan(9)));



    }

    @DisplayName("Assertion with String" )
    @Test
    public void stringHamcrest() {
        String text = "B22 is learning Hamcrest";

        // checking for equality is same as number
        assertThat(text, is("B22 is learning Hamcrest"));
        assertThat(text, equalTo("B22 is learning Hamcrest"));
        assertThat(text, is(equalTo("B22 is learning Hamcrest")));

        //check if this text with B22
        assertThat(text, startsWith("B22"));
        //insensitive manner

        assertThat(text, startsWithIgnoringCase("b22"));

        // endsWith
        assertThat(text, endsWith("rest"));

        //check if text contains String learning
        assertThat(text, containsString("learning"));

        //with ignoring case
        assertThat(text, containsStringIgnoringCase("Learning"));

        String str = " ";
        //check if above str is blank
        assertThat(str, blankString());

        //check if trimmed str is empty string
        assertThat(str.trim(), emptyString());
    }

        @DisplayName("Hamcrest for collection")
        @Test
        public void testCollection(){

            List<Integer> listOfNumbers = Arrays.asList(1,4,5,6,32,54,66,77,45,23);

            //check size of the list
            assertThat(listOfNumbers,hasSize(10));

            //check if this list hasItem77
            assertThat(listOfNumbers,hasItem(77));

            //checvk if this list hasItems 77,54,23
            assertThat(listOfNumbers,hasItems(77,54,23));

            //check if all numbers greater than 0
            assertThat(listOfNumbers,everyItem(greaterThan(0)));







    }

}
