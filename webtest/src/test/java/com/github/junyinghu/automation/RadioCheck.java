package com.github.junyinghu.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RadioCheck {

    protected final WebDriver driver;
    protected final Properties properties;

    public RadioCheck(WebDriver driver, Properties properties)

    {
        this.driver = driver;
        this.properties = properties;

    }

    public void radioCheck(String radioSelected, String radioNoSelected){
        String userEmailEnalbe = properties.getProperty(radioSelected);
        WebElement CurrentUserEmailEnable = driver.findElement(By.id(userEmailEnalbe));
        if (CurrentUserEmailEnable.isSelected()) {
            String userEmailDisable = properties.getProperty(radioNoSelected);
            WebElement CurrentUserEmailDisable = driver.findElement(By.id(userEmailDisable));
            CurrentUserEmailDisable.click();
        }

        else {
            CurrentUserEmailEnable.click();
        }

    }
    public List<String> dropDownlist(String dropDownlist){
        List<String> dropDownlistAll = new ArrayList();

        String dropDownlistFind = properties.getProperty(dropDownlist);
        List<WebElement> currentDropDownlist = driver.findElements(By.id(dropDownlistFind));
        //Reporter.log("This is list for dropdown"+currentDropDownlist,true);

        String label=driver.findElement(By.id(dropDownlistFind)).getText();
        //Reporter.log("This is list for dropdownoptions text "+label,true);

            dropDownlistAll.add(label);
           // Reporter.log("This is list for dropdown option text array list "+dropDownlistAll,true);


        return dropDownlistAll;

    }


}

