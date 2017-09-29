package com.github.junyinghu.automation.test;

import com.github.junyinghu.automation.RadioCheck;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.util.List;
import java.util.Properties;

public class KeyWords extends RadioCheck {


    //private final WebDriver driver;
    //private final Properties properties;

    public KeyWords(WebDriver driver, Properties properties) {
        super(driver, properties);
        //this.driver = driver;
        //this.properties= properties;
    }


    public void enterKeywordsSearch() {
        String inputBox = properties.getProperty("inputbox_id");
        WebElement currentInputBox = driver.findElement(By.id(inputBox));
        currentInputBox.sendKeys("Ranorex");
        currentInputBox.sendKeys(Keys.ENTER);


        String search = properties.getProperty("searchbutton_name");
        WebElement clickSearch = driver.findElement(By.name(search));
        clickSearch.click();


    }

    public void clickUrl() {
        String url = properties.getProperty("weburl_text");
        WebElement curretUrl = driver.findElement(By.linkText(url));
        curretUrl.click();

        String formUrl = properties.getProperty("fromurl_xpath");
        WebElement curretFormUrl = driver.findElement(By.xpath(formUrl));
        curretFormUrl.click();

        String login = properties.getProperty("login_xpath");
        WebElement currentLogin = driver.findElement(By.xpath(login));
        currentLogin.click();
    }

    public void login() {
        String username = properties.getProperty("username_id");
        WebElement currentUsername = driver.findElement(By.id(username));
        currentUsername.sendKeys("jun");

        String password = properties.getProperty("password_id");
        WebElement currentPassword = driver.findElement(By.id(password));
        currentPassword.sendKeys("hjyhappy");

        String login = properties.getProperty("login_btn_name");
        WebElement currentLogin = driver.findElement(By.name(login));
        currentLogin.click();
    }

    public void setting() {
        String userContralpanel = properties.getProperty("usercontralpanel_xpath");
        WebElement currentUserContralPanel = driver.findElement(By.xpath(userContralpanel));
        currentUserContralPanel.click();

        String boradPreference = properties.getProperty("boradprference_xpath");
        WebElement currentBoradPreference = driver.findElement(By.xpath(boradPreference));
        currentBoradPreference.click();
    }

    public void toggleAllRadioCheck() {
        radioCheck("location_board_video_id_user_email_y",
                "location_board_video_id_user_email_n");

        radioCheck("location_board_video_id_admin_email_y",
                "location_board_video_id_admin_email_n");

        radioCheck("location_board_video_id_private_msg_y",
                "location_board_video_id_private_msg_n");

        radioCheck("location_board_video_id_hide_online_y",
                "location_board_video_id_hide_online_n");

        radioCheck("location_board_video_id_notify_msg_y",
                "location_board_video_id_notify_msg_n");
        radioCheck("location_board_video_id_pop_win_y",
                "location_board_video_id_pop_win_n");

//        String radiogroup = properties.getProperty("localtion_radio_group");
//        List<WebElement> radiogrup2 = driver.findElements(By.xpath(radiogroup));
//        Reporter.log("tjlsjflsajflsd------" +radiogrup2, true);
//        driver.switchTo().defaultContent();

    }




}
