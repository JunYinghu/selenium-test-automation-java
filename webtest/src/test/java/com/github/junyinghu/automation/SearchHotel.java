package com.github.junyinghu.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SearchHotel {


    private final WebDriver driver;
    private final Properties properties;
    private final String strChild;
    private final String strAdult;
    private final String strRoom;
    private final String Desination;
    private final String strDesination;
    private final String child;
    private final String adult;
    private final String room;
    private List<String> availableRooms = new ArrayList<>();

    public SearchHotel(WebDriver driver, Properties properties) {
        this.driver = driver;
        this.properties = properties;
        this.strChild = properties.getProperty("childlist");
        this.strAdult = properties.getProperty("adultlist");
        this.strDesination = properties.getProperty("Desinationlist");
        this.strRoom = properties.getProperty("roomlist");

        this.Desination = properties.getProperty("Desination");
        this.room = properties.getProperty("room");
        this.child = properties.getProperty("child");
        this.adult = properties.getProperty("adult");
    }

    public List<String> hotelDesination() {
        List<String> values = new ArrayList<>();

            List<WebElement> options = driver.findElements(By.id(strDesination));
            Reporter.log("this is size ==== "+options.size(),true);
            for (WebElement option : options) {
                String value = option.getAttribute("innerHTML").trim();
                Reporter.log("this is deisjgg"+value,true);
                values.add(value);
        }

        return values;
    }

    public void selectcheckinout() {

        String strCheckin = properties.getProperty("checkin");
        WebElement checkIn = driver.findElement(By.id(strCheckin));
        checkIn.sendKeys("01/10/2017");

        String strCheckout = properties.getProperty("checkout");
        WebElement checkOut = driver.findElement(By.id(strCheckout));
        checkOut.sendKeys("15/10/2017");

    }
    public List<String> room() {
        List<String> roomValues = new ArrayList<>();

        List<WebElement> options = driver.findElements(By.id(strRoom));
        for (WebElement option : options) {
            String value = option.getAttribute("innerHTML").trim();
            roomValues.add(value);
        }

        return roomValues;
    }

    public List<String> adult(){
        List<String> adultValues = new ArrayList<>();

        List<WebElement> options = driver.findElements(By.id(strAdult));
        for(WebElement option: options){
            String value = option.getAttribute("innerHTML").trim();
            adultValues.add(value);
        }
        return adultValues;
    }

    public List<String> child(){
        List<String> childValues = new ArrayList<>();

        List<WebElement> options = driver.findElements(By.id(strChild));
        for (WebElement option: options) {
            String value = option.getAttribute("innerHTML").trim();
            childValues.add(value);
        }
        return childValues;

    }



    public void selectDesignatio (String desination){
        //final List<String> Desinations = hotelDesination();
        WebElement desinationId = driver.findElement(By.id(Desination));
        desinationId.click();
        desinationId.sendKeys(desination);
    }
    /**
     *
     * @param curRoomId index of room, starts with 0
     * @param curAdultId
     * @param curChildId
     */



    public void selectRoom(int curRoomId) {
        final List<String> rooms = room();
        WebElement roomId = driver.findElement(By.id(room));
        roomId.click();
        roomId.sendKeys(rooms.get(curRoomId));
    }
        public void selectAdult(int curAdultId) {


            final List<String> adults = adult();
            WebElement adultId = driver.findElement(By.id(adult));
            adultId.click();
            adultId.sendKeys(adults.get(curAdultId));
        }

        public void selectChild(int curChildId){
        final List<String> childs = child();
        WebElement childId = driver.findElement(By.id(child));
        childId.click();
       childId.sendKeys(childs.get(curChildId));

}
}

