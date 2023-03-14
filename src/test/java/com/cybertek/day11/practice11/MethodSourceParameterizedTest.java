package com.cybertek.day11.practice11;

import com.cybertek.utilities.ExcelUtil;
import com.cybertek.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.regex.Matcher.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

public class MethodSourceParameterizedTest {

    @ParameterizedTest
    /*
    @MethodSource("getNames") //<-- this method allow us to access our getNames() method below  <-- below
    and prints it out as it if its forEach loop very useful method
     */
    @MethodSource("getNames") //<-- this method allow us to access our getNames() <-- below

    public void testPrintNames(String name){
        System.out.println("name = " + name);
    }

    public static List<String> getNames(){
        List<String > nameList = Arrays.asList("Juan", "Carlos", "Manuel", "Dimas", "Saul", "Mario", "Luis",
                "Edgar", "Edwin");
        return nameList;

    }

    public static List<Map<String, String>> getExcelData(){
        ExcelUtil vyTrackFile = new ExcelUtil("src/test/resources/Vytracktestdata.xlsx", "QA3-all");
        return vyTrackFile.getDataList();
    }

@ParameterizedTest
    @MethodSource("getExcelData")
    public void excelParamTest(Map<String,String> userInfo){
    System.out.println("userInfo.get(\"firstname\") = " + userInfo.get("firstname"));
    System.out.println("userInfo.get(\"lastname\") = " + userInfo.get("lastname"));


}





}
