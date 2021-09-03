package com.cybertek.utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class  HrTestBase{

    //basically we are creating static method that will execute our baseUrl code line
    @BeforeAll
    public static void init() {
        baseURI = "http://100.26.48.87:1000/ords/hr";
}
}
