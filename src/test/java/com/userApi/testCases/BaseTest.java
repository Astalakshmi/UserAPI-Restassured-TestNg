package com.userApi.testCases;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.userApi.utilities.ConfigReader;
import com.userApi.utilities.ExcelReader;
import com.userApi.utilities.LoggerLoad;

public class BaseTest {

    // ExcelReader instance
    public ExcelReader excelReader;

    // Static lists for each operation
    public static List<Map<String, String>> Get;
    public static List<Map<String, String>> Post;
    public static List<Map<String, String>> Put;
    public static List<Map<String, String>> Patch;
    public static List<Map<String, String>> Delete;

    // Configurations
    public static String Username;
    public static String Password;
    public static String BaseURL;
    public static String InBaseURL;
    public static String SearchAllEp;
    public static String SearchByIdEP;
    public static String SearchByNameEP;
    public static String CreateEP;
    public static String UpdateEP;
    public static String PatchEP;
    public static String DeleteByIdEP;
    public static String DeleteByNameEP;
    public static String Json;

    // Encrypted credentials, if applicable
    public static String eUserName = "";
    public static String ePassword = "";

    @BeforeClass
    public void setupExcel() throws IOException {
        LoggerLoad.info("RestAssured USER API Execution Started");
        LoggerLoad.info("Initializing Workbook and Configurations");

        try {
            // Load properties
            Username = ConfigReader.getProperty("username");
            Password = ConfigReader.getProperty("password");
            BaseURL = ConfigReader.getProperty("BaseURL");
            InBaseURL = ConfigReader.getProperty("InvalidBase");
            SearchAllEp = ConfigReader.getProperty("GetAllEP");
            SearchByIdEP = ConfigReader.getProperty("GetByIdEP");
            SearchByNameEP = ConfigReader.getProperty("GetByNameEP");
            CreateEP = ConfigReader.getProperty("PostEP");
            UpdateEP = ConfigReader.getProperty("PutEP");
            PatchEP = ConfigReader.getProperty("PatchEP");
            DeleteByIdEP = ConfigReader.getProperty("DeleteByIdEP");
            DeleteByNameEP = ConfigReader.getProperty("DeleteByNameEP");
            Json = ConfigReader.getProperty("Json");

            // Load Excel data
            String path = ConfigReader.getProperty("Excelpath");
            LoggerLoad.info("Excel Path: " + path);
            excelReader = new ExcelReader();

            Get = excelReader.getData(path, "Get");
            Post = excelReader.getData(path, "Post");
            Put = excelReader.getData(path, "Put");
            Patch = excelReader.getData(path, "Patch");
            Delete = excelReader.getData(path, "Delete");

        } catch (Exception e) {
            LoggerLoad.error("Error during setupExcel: " + e.getMessage());
            throw new RuntimeException("Excel or config initialization failed", e);
        }
    }

    @AfterClass
    public void closeExcel() {
        ExcelReader.closeAllWorkbooks();
        LoggerLoad.info("Excel workbook closed successfully.");
    }
}
