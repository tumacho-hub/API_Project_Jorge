package com.cybertek.day11;

import org.junit.jupiter.api.*;

public class TestLifeCycleAnnotations {

    @Test
    public void test(){

        System.out.println("Test 1 is running");

    }
    @Test
    public void test2(){
        System.out.println("Test 2 is running");

    }

    @BeforeAll
    public static void init(){

        System.out.println("Before all is running");
    }

    @AfterAll
    public static void close(){

        System.out.println("After all is running");
    }

    @BeforeEach
    public void initEach(){
        System.out.println("\tBefore each is running");

    }
    @AfterEach
    public  void closeEach(){
        System.out.println("\tAfter each is running");
    }

}
