package com.cybertek.day11;

import com.cybertek.utilities.ExcelUtil;
import com.sun.javafx.collections.MappingChange;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class ExcelUtilPractice {

    @Test
    public void test1(){
        // in order to use ExcelUtil we need to create an object from it
        // how to use excelUtil file?
        // it accepts two arguments
        // argument 1: location of the file (path)
        // argument 2: sheet that we want to open
        ExcelUtil vyTrackFile = new ExcelUtil("src/test/resources/Vytracktestdata.xlsx","QA3-short");

        //method for returning list of map
        vyTrackFile.getDataList();

        List<Map<String, String>> dataList = vyTrackFile.getDataList();
        for (Map<String,String> rowMap : dataList)
            System.out.println(rowMap);


    }
}
