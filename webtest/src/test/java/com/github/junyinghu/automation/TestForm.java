package com.github.junyinghu.automation;

import com.github.junyinghu.automation.test.KeyWords;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class TestForm {
    private WebDriver driver;
    private Properties properties = new Properties();
    private File file = new File("src/test/resources/xpath_data.properties");
    private KeyWords searchKeyword;

    private String secondWindowOpen;
    private String firstWindowOpen;
    private JavascriptExecutor jse;


    @BeforeClass
    public void beforeClass() {
        try (FileInputStream fis = new FileInputStream(file);
             InputStreamReader reader = new InputStreamReader(fis, StandardCharsets.UTF_8)) {
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver = new FirefoxDriver();
        driver.get("http://google.com.sg");
        searchKeyword = new KeyWords(driver, properties);
        jse = (JavascriptExecutor) driver;

    }

    @AfterClass
    public void afterClass() {
        Reporter.log("afterClass", true);
        driver.close();
    }

    @Test
    public void openGoogleSearch() throws InterruptedException {
        searchKeyword.enterKeywordsSearch();
        Thread.sleep(5000);
        searchKeyword.clickUrl();
        searchKeyword.login();
        Thread.sleep(5000);
        searchKeyword.setting();

    }
//
//    @Test(dependsOnMethods = {"openGoogleSearch"}) //can put multiple by ,
//    public void modificationsetting() {
//        searchKeyword.toggleAllRadioCheck();
//        List<String> dropDownlistAllLanguage = searchKeyword.dropDownlist("location_board_drop_id_language");
//
//        int dropDownlistLanguageSize =dropDownlistAllLanguage.size();
//
//        List<String> dropDownlistAllTimeZone = searchKeyword.dropDownlist("location_board_drop_id_timezone");
//        int dropDownlistAllTimeZoneSize =dropDownlistAllTimeZone.size();
//
//        searchKeyword.radioCheck("location_board_video_id_sum_time_y",
//                "location_board_video_id_sum_time_n");
//        List<String> dropDownlistAllDateFormat = searchKeyword.dropDownlist("location_board_drop_id_dateformat");
//        int dropDownlistAllDataFormatSize =dropDownlistAllDateFormat.size();
//        Reporter.log("this is drop down list option size for DataFormat "+dropDownlistAllDataFormatSize,true);
//
//    }

//    public void test_select () throws Exception {
//        navigatetoselect();
//        for (WebElement option: options) {
//            Reporter.log(option.getAttribute());
//        driver.switchTo().defaultContent();
//        }
//    }
//    @Test (dependsOnMethods = {"modificationsetting"})
//    public void navigatetoselect (){
//        driver.get ("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_input_radio");
//        driver.switchTo().frame("iframeResult");
//        List<WebElement> radioGroup = driver.findElements(By.name("gender"));
//        Reporter.log("sdfasfda" + radioGroup.size(),true);
//        for (WebElement element: radioGroup){
//            boolean isRadioSelected = element.isSelected();
//            if (isRadioSelected == true) {
//
//                Reporter.log("asjdflasjdflds"+element.getText(),true);
//            }
//        }
//        driver.switchTo().defaultContent();
//    }

}