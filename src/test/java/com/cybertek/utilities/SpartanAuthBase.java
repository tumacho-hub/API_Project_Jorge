package com.cybertek.utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class SpartanAuthBase {

    //basically we are creating static method that will execute our baseUrl code line
    @BeforeAll
    public static void init() {
     //baseURI = "http://54.92.248.102:7000";
        baseURI = "http://54.92.248.102:8000";

    }
}
