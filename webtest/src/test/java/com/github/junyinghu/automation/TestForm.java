package com.github.junyinghu.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;

public class TestForm {
    private WebDriver driver;
    private Properties properties = new Properties();
    private File file = new File("src/test/resources/xpath_data.properties");
    private KeyWords searchKeyword;


    @BeforeClass
    public void beforeClass() {
        try (FileInputStream fis = new FileInputStream(file);
             InputStreamReader reader = new InputStreamReader(fis, StandardCharsets.UTF_8)) {
            properties.load(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver = new FirefoxDriver();
        driver.get("http://google.com.sg");
        searchKeyword = new KeyWords(driver, properties);

    }

    @AfterClass
    public void afterClass() {
        Reporter.log("afterClass", true);
        driver.close();
    }

    @Test
    public void openGoogleSearch() throws InterruptedException {
        searchKeyword.enterKeywordsSearch();
        searchKeyword.clickUrl();
        searchKeyword.login();
        searchKeyword.setting();

        Thread.sleep(5000);
    }

    @Test(dependsOnMethods = {"openGoogleSearch"}) //can put multiple by ,
    public void modificationsetting() {
        searchKeyword.toggleAllRadioCheck();
        List<String> dropDownlistAllLanguage = searchKeyword.dropDownlist("location_board_drop_id_language");
        int dropDownlistLanguageSize =dropDownlistAllLanguage.size();
        //Reporter.log("this is drop down list option size for Language "+dropDownlistAllLanguage,true);

        List<String> dropDownlistAllTimeZone = searchKeyword.dropDownlist("location_board_drop_id_timezone");
        int dropDownlistAllTimeZoneSize =dropDownlistAllTimeZone.size();
        //Reporter.log("this is drop down list option size for TimeZone "+dropDownlistAllTimeZone,true);

        searchKeyword.radioCheck("location_board_video_id_sum_time_y",
                "location_board_video_id_sum_time_n");

        List<String> dropDownlistAllDateFormat = searchKeyword.dropDownlist("location_board_drop_id_dateformat");
        int dropDownlistAllDataFormatSize =dropDownlistAllDateFormat.size();
        Reporter.log("this is drop down list option size for DataFormat "+dropDownlistAllDataFormatSize,true);

    }


}