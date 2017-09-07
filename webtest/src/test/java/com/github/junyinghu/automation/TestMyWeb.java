package com.github.junyinghu.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

public class TestMyWeb {
    private  WebDriver driver;
    private  Properties properties = new Properties();
    private  File file = new File("src/test/resources/xpath_data.properties");
    private  Search configurate;
    private SearchHotel hotelconfiguate;
    private ReadCsv readCsv = new ReadCsv();

    @BeforeClass
    public void beforeClass() throws IOException {
        Reporter.log("beforeClass", true);

        try (FileInputStream fis = new FileInputStream(file);
             InputStreamReader reader = new InputStreamReader(fis, StandardCharsets.UTF_8)) {
            properties.load(reader);
        }

        driver = new FirefoxDriver();
        driver.get("http://www.singaporeair.com/en_UK/sg/home#flightSearch");
        configurate = new Search(driver, properties);
        configurate.initializeSearch();
        hotelconfiguate = new SearchHotel(driver,properties);

    }

    @AfterClass
    public void afterClass() {
        Reporter.log("afterClass", true);
        driver.close();
    }

    @Test(enabled = false)
    public void testBookTrip() throws InterruptedException {
       int maxOrigin = configurate.getCountOfOrigin();
       int maxDestination = configurate.getCountOfDestination();


       for(int i=0;i<maxOrigin;i++){

           for (int j=0;j<maxDestination;j++){
               configurate.searchPage(i,j);
           }
       }
    }

    @Test
    public void testClick() throws InterruptedException, IOException {
        WebElement clickHotel = driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/aside/div/div/div[1]/ul/li[2]/a/span[2]"));
        clickHotel.click();
        List<String> hotels = hotelconfiguate.hotelDesination();
        int hotelLen =hotels.size();
        List<String> room = hotelconfiguate.room();
        int roomLen = room.size();
        List<String> adult = hotelconfiguate.adult();
        int adultLen = adult.size();
        List<String> child = hotelconfiguate.child();
        int childLen = child.size();
        Reporter.log("this get Hotel00",hotelLen,true);
        Reporter.log("this get roomLen",roomLen,true);

        List<String> destinations = readCsv.getDestinations();
        for(String destination: destinations){
            hotelconfiguate.selectDesignatio(destination);
            hotelconfiguate.selectcheckinout();

            for (int j=0;j<roomLen;j++){
                hotelconfiguate.selectRoom(j);

                for(int a=0;a<adultLen;a++) {
                    hotelconfiguate.selectAdult(a);

                    for (int c = 0; c < childLen; c++) {
                        hotelconfiguate.selectChild(c);
                    }
                }
            }
        }



        Reporter.log("clicking", true);
        //System.out.println("hello");
        Thread.sleep(5000);
    }
}
