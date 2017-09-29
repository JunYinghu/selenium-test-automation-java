package com.github.junyinghu.automation;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Elearning {

    private FirefoxDriver driver;
    private JavascriptExecutor jse;
    private String secondWindowOpen;
    private String firstWindowOpen;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        jse = (JavascriptExecutor) driver;

    }

//    @AfterClass
//    public void afterClass() {
//        Reporter.log("afterClass", true);
//        driver.close();
//    }

    @Test
    public void testWindowhand() throws Exception {
        firstWindow();
        secondWindow();
        Reporter.log("number of window" + driver.getWindowHandles(), true);
        driver.switchTo().window(secondWindowOpen);
        Reporter.log("page tile"+ driver.getTitle(),true);
        if (!driver.getTitle().equals("Bing"))throw new WrongPageException("incorrect page title");

    }

    private void firstWindow() {
        driver.get("https://www.yahoo.com/");
        firstWindowOpen = driver.getWindowHandle();
    }

    private void secondWindow() {
        jse.executeScript("window.open ('http://www.bing.com/');");
        secondWindowOpen = driver.getWindowHandle();
    }

}

