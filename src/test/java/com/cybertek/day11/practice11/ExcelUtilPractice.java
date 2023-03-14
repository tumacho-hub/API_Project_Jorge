package com.cybertek.day11.practice11;

import com.cybertek.utilities.ExcelUtil;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class ExcelUtilPractice {
    /*
    In order to use a method from Util class we need to create abn object from it
    The how do we use this file?
    It accepts two arguments
    argument#1- location of the file (PATH)
    argument#2- sheet that we want to open
    vyTrackFile.getDataList()- this method returns all the rows from the file

     */

@Test
    public void test1(){

    ExcelUtil vyTrackFile = new ExcelUtil("src/test/resources/Vytracktestdata.xlsx", "QA3-all");
    List<Map<String, String>> dataList = vyTrackFile.getDataList();
    for (Map<String, String> rowMap : dataList) {
        System.out.println("rowMap = " + rowMap);

    }


}





}
