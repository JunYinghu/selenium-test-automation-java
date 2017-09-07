package com.github.junyinghu.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.util.*;

public class Search {

    private final WebDriver driver;
    private final Properties properties;
    private final List<String> availableOrigin = new ArrayList<>();
    private final List<String> availableDestination = new ArrayList<>();
    private boolean initialized = false;

    public Search(WebDriver driver, Properties properties) {
        this.driver = driver;
        this.properties = properties;
    }

    public void initializeSearch() {
        if (initialized) {
            return;
        }

        List<WebElement> options = driver.findElements(By.xpath("/html/body/div[3]/div[3]/div/aside/div/div/div[1]/div/div[1]/form[1]/fieldset/div[1]/div/div[2]/div[1]/div/div/label[2]/select/option"));
        for (WebElement option : options) {
            String value = option.getAttribute("innerHTML").trim();
            availableOrigin.add(value);
        }

        List<WebElement> toList = driver.findElements(By.xpath("/html/body/div[3]/div[3]/div/aside/div/div/div[1]/div/div[1]/form[1]/fieldset/div[1]/div/div[2]/div[2]/div/div/label[2]/select/option"));
        for (WebElement option : toList) {
            String toListCityvalue = option.getAttribute("innerHTML").trim();
            availableDestination.add(toListCityvalue);
        }

        initialized = true;
    }

    public int getCountOfOrigin() {
        if (!initialized) {
            throw new IllegalStateException("please call initialize");
        }
        return availableOrigin.size();

    }

    public int getCountOfDestination() {
        if (!initialized) {
            throw new IllegalStateException("please call initialize");
        }
        return availableDestination.size();
    }

    public void searchPage(int fromCityId, int toCityId) throws InterruptedException {
        if (!initialized) {
            throw new IllegalStateException("please call initialize");
        }
//
//        List<String> availableOrigins = new ArrayList<>();
//        List<WebElement> options = driver.findElements(By.xpath("/html/body/div[3]/div[3]/div/aside/div/div/div[1]/div/div[1]/form[1]/fieldset/div[1]/div/div[2]/div[1]/div/div/label[2]/select/option"));
//        for (WebElement option : options) {
//            String value = option.getAttribute("innerHTML").trim();
//            availableOrigins.add(value);
//        }
        String origin = availableOrigin.get(fromCityId);
        String destination = availableDestination.get(toCityId);

        Reporter.log("selecting origin      : " + origin, true);
        Reporter.log("selecting destination : " + destination, true);

        if(origin.equals(destination)){
            Reporter.log("skipping " + origin + " to " + destination);
            return;
        }

        String strFrom = properties.getProperty("Fromcity");
        WebElement fromCity = driver.findElement(By.xpath(strFrom));
        fromCity.click();
        fromCity.sendKeys(origin);
//        fromCity.sendKeys(Keys.ENTER);
//        Thread.sleep(3000);


//        List<WebElement> toList = driver.findElements(By.xpath("/html/body/div[3]/div[3]/div/aside/div/div/div[1]/div/div[1]/form[1]/fieldset/div[1]/div/div[2]/div[2]/div/div/label[2]/select/option"));
//        List<String> toListCity = new ArrayList<>();
//        for (WebElement option : toList) {
//            String toListCityvalue = option.getAttribute("innerHTML").trim();
//            toListCity.add(toListCityvalue);
//        }
        String strTo = properties.getProperty("Tocity");
        WebElement toCity = driver.findElement(By.xpath(strTo));
        toCity.click();
        toCity.sendKeys(destination);
        toCity.sendKeys(Keys.ENTER);

//        driver.findElement(By.tagName("html")).sendKeys(Keys.ESCAPE);

//        String strButtonXpath = properties.getProperty("Searchbut");
//        WebElement buttonElement = driver.findElement(By.xpath(strButtonXpath));
//        buttonElement.click();

        Thread.sleep(2000);
    }

}
