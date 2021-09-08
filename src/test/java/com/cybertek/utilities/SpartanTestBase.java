package com.cybertek.utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class SpartanTestBase {

    //basically we are creating static method that will execute our baseUrl code line
    @BeforeAll
    public static void init() {
        baseURI = "http://100.26.48.87:8000";

        String dbUrl = "jdbc:oracle:thin:@100.26.48.87:1521:xe";
        String dbUsername = "SP";
        String dbPassword = "SP";

      // DBUtils.createConnection(dbUrl,dbUsername,dbPassword);
}
@AfterAll
    public static void tearDown(){
      //  DBUtils.destroy();
}
}
