package com.cybertek.day11;

import com.cybertek.utilities.ExcelUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MethodSourceParameterizedTest {

    @ParameterizedTest
    @MethodSource("getNames")
    public void testPrintNames(String name){

        System.out.println("name = " + name);
    }
// you can get value from anywhere almost anytype and return to your test
    //DB- Excel- other API
    public static List<String> getNames(){
        List<String>nameList = Arrays.asList("Parvin","Nasim","Nadir","Saim","Gurhan","Murodil");
        return nameList;
    }
    public static List<Map<String,String>> getExcelData(){

        ExcelUtil vyTrackFile = new ExcelUtil("src/test/resources/Vytracktestdata.xlsx","QA3-short");
        return vyTrackFile.getDataList();

    }


    //reading from excel sheet with DDT (data driven testing
    @ParameterizedTest
    @MethodSource("getExcelData")
    public void excelParamTest(Map<String,String> userInfo){

        System.out.println("userInfo.get(\"firstname\") = " + userInfo.get("firstname"));
        System.out.println("userInfo.get(\"lastname\") = " + userInfo.get("lastname"));



    }


}
