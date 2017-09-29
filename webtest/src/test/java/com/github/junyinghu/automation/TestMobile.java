package com.github.junyinghu.automation;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;

import java.net.MalformedURLException;
import java.net.URL;

public class TestMobile {


    private static AndroidDriver<WebElement> driver;

    @BeforeClass
    public static void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformVersion", "4.1.2");
        capabilities.setCapability("deviceName", "GT-I9300");
        //capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.android.vending");
        capabilities.setCapability("appActivity", "com.android.vending.AssetBrowserActivity");
        driver = new AndroidDriver<WebElement>(new URL("http://localhost:4723/wd/hub"), capabilities);
        System.out.print("app is start");
    }
    @AfterClass
    public static void tearDown(){
        driver.quit();
    }

    @Test
    public void testMessage() {
        Reporter.log("This is print message", true);
    }
}
