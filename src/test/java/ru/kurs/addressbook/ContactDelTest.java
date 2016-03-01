package ru.kurs.addressbook;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.io.File;
import java.util.regex.Matcher;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;
import static org.openqa.selenium.OutputType.*;

public class ContactDelTest {
    FirefoxDriver wd;
    
    @BeforeMethod
    public void setUp() throws Exception {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
    
    @Test
    public void ContactDelTest() {
        wd.get("http://localhost:8081/addressbook/group.php");
        wd.findElement(By.name("user")).click();
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys("admin");
        wd.findElement(By.xpath("//form[@id='LoginForm']//label[.='Password:']")).click();
        wd.findElement(By.name("pass")).click();
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys("secret");
        wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
        wd.findElement(By.linkText("home")).click();
        wd.findElement(By.xpath("//table/tbody/tr[4]/td[1]/input")).click();
            //wd.findElement(By.id("8")).click();
        //}
        //"//table/tbody/tr[4]/td[1]/input"
        //wd.findElement(By.name("selected[]")).click();
//        wd.findElement(By.xpath("//div[@id='content']/form[2]/div[2]/input")).click();
        wd.findElement(By.xpath("/html/body/div/div[4]/form[2]/div[2]/input")).click();
       // assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
        wd.findElement(By.linkText("home")).click();


    }

    private Matcher closeAlertAndGetItsText() {
        return null;
    }

    @AfterMethod
    public void tearDown() {
        wd.quit();
    }
    
    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
