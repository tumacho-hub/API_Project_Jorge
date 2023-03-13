package com.cybertek.day11.practice11;

import org.junit.jupiter.api.*;

public class TestLifeCycleAnnotations {


    @BeforeAll
    public static void initialization(){
        System.out.println("Before all");
    }

    /*
    For TestNG, we use @BeforeMethod() to run something before each method
    but foe JUNIT we use  @BeforeEach() annotation which is the same and does not have to be static
     */
    @BeforeEach()
    public void initializationEach(){
        System.out.println("Before each is running");
    }

    @AfterEach()
    public void closeEach(){
        System.out.println("After each is running");
    }

    @Test
    public void test1() {
        System.out.println("Test 1 running");
    }
/*
@Disabled //<-- this annotation disables the test will not be executed for JUNIT,
and for TestNG we use @Ignore() annotation
 */
    @Disabled
    @Test
    public void test2() {
        System.out.println("Test 2 running");


    }

    @AfterAll()
    public static void close(){
        System.out.println("After all is running");
    }
}