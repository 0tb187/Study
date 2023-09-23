import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class Main{
    public static void main(String[] args) {
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\4ept_\\Downloads\\chromedriver-win64117right\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions op = new ChromeOptions();
        op.setBrowserVersion("116");
        WebDriver driver = new ChromeDriver(op);
        String parent = driver.getWindowHandle();

    //1
        driver.get("https://demoqa.com/");
    //2
        WebElement starter = (new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='card-body'][1]"))));
        starter.click();
    //3
        driver.findElement(By.xpath("//span[text()='Text Box']")).click();
    //4
        WebElement UserName = driver.findElement(By.xpath("//input[@id='userName']"));
        UserName.sendKeys(Data.username);

        WebElement Email = driver.findElement(By.xpath("//input[@id='userEmail']"));
        Email.sendKeys(Data.email);

        WebElement Address1 = driver.findElement(By.xpath("//textarea[@id='currentAddress']"));
        Address1.sendKeys(Data.address1);

        WebElement Address2 = driver.findElement(By.xpath("//textarea[@id='permanentAddress']"));
        Address2.sendKeys(Data.address2);
    //5
        driver.findElement(By.cssSelector("#submit")).click();
    //6
        String inName = driver.findElement(By.id("name")).getText();
        String checkName = inName.substring(5);
        if (checkName.equals(Data.username)){
        }else {driver.quit();
        System.out.println("Failed");}

        String inEmail = driver.findElement(By.id("email")).getText();
        String checkEmail = inEmail.substring(6);
        if (checkEmail.equals(Data.email)){
        }else {driver.quit();
        System.out.println("Failed");}

        String inCurrAdrs = driver.findElement(By.xpath("//p[@class='mb-1'][3]")).getText();
        String checkCA = inCurrAdrs.substring(17);
        //       System.out.println(checkCA);
        if (checkCA.equals(Data.address1)){
        }else {driver.quit();
        System.out.println("Failed");}

        String inPermAdrs = driver.findElement(By.xpath("//p[@class='mb-1'][4]")).getText();
        String checkPA = inPermAdrs.substring(20);
        //      System.out.println(checkPA);
        if (checkPA.equals(Data.address2)){
        }else {driver.quit();
        System.out.println("Failed");}
    //7
        driver.findElement(By.xpath("//*[@id=\"item-4\"]/span")).click();
    //8
        driver.findElement(By.xpath("//button[text()='Click Me']")).click();
    //9
        String dynamicMsg1 = driver.findElement(By.cssSelector("#dynamicClickMessage")).getText();
        Assert.assertEquals("You have done a dynamic click", dynamicMsg1);
    //10
        Actions rc = new Actions(driver);
        rc.contextClick(driver.findElement(By.xpath("//button[text()='Right Click Me']"))).perform();
    //11
        String dynamicMsg2 = driver.findElement(By.cssSelector("#rightClickMessage")).getText();
        Assert.assertEquals("You have done a right click", dynamicMsg2);
    //12
        Actions dc = new Actions(driver);
        dc.doubleClick(driver.findElement(By.xpath("//button[text()='Double Click Me']"))).perform();
    //13
        String dynamicMsg3 = driver.findElement(By.cssSelector("#doubleClickMessage")).getText();
        Assert.assertEquals("You have done a double click", dynamicMsg3);
    //14
        WebElement btnElement = driver.findElement(By.xpath("//div[text()='Alerts, Frame & Windows']/../../div[@class='header-wrapper']"));
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click()",btnElement);
    //15
        driver.findElement(By.xpath("//span[text()='Browser Windows']")).click();
    //16
        driver.findElement(By.xpath("//button[text()='New Tab']")).click();
    //17
        for (String tab : driver.getWindowHandles()){                           //driver.switchTo().window(parent);
            driver.switchTo().window(tab);
        }
        driver.close();

        driver.switchTo().window(parent);
    //18
        driver.findElement(By.xpath("//button[text()='New Window']")).click();
    //19
        for (String windowHandle : driver.getWindowHandles()) {
            if(!parent.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                driver.close();
            }
        }
        driver.switchTo().window(parent);
    //20
        driver.findElement(By.xpath("//span[text()='Alerts']")).click();
    //21
        driver.findElement(By.xpath("//button[text()='Click me']")).click();
    //22
        Alert alert = driver.switchTo().alert();
        String alertmsg = alert.getText();
        //  System.out.println(alertmsg);
        alert.accept();
    //23
        driver.findElement(By.xpath("//button[@id='timerAlertButton']")).click();
    //24
        Alert alert1 = (new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent()));
        alert1.accept();
    //25
        driver.findElement(By.xpath("//button[@id='confirmButton']")).click();
    //26
        Alert alert2 = driver.switchTo().alert();
        alert2.accept();
    //27
        String confRes = driver.findElement(By.xpath("//span[@id='confirmResult']")).getText();
        Assert.assertEquals( "You selected Ok", confRes);
    //28
        driver.findElement(By.xpath("//button[@id='promtButton']")).click();
    //29
        Alert alert3 = driver.switchTo().alert();
        alert3.sendKeys(Data.username);
        alert3.accept();
    //30
        String confInput = driver.findElement(By.xpath("//span[@id='promptResult']")).getText();
        Assert.assertEquals( "You entered "+Data.username, confInput);

    }
}