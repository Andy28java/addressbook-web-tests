package ru.kurs.addressbook;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

public class CreaNewGroupJ {
    FirefoxDriver wd;

    @BeforeTest
    public void setUp() throws Exception {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        wd.get("http://localhost:8081/addressbook/");
        login("admin", "secret");
    }

    private void login(String username, String userpasswd) {
        wd.findElement(By.id("content")).click();
        wd.findElement(By.name("user")).click();
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys(username);
        wd.findElement(By.id("LoginForm")).click();
        wd.findElement(By.name("pass")).click();
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys(userpasswd);
        wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
    }

    @Test
    public void testCreaNewGroupJ() {

        gotoGroupPage();
        wd.findElement(By.name("new")).click();
        initGoupeCreation();
        fillGroupeForm(new GroupeData("test1", "1", "11"));
        submitGroupeCreation();
        gotoGroupPage();
    }

    private void submitGroupeCreation() {
        wd.findElement(By.name("submit")).click();
    }

    private void fillGroupeForm(GroupeData groupeData) {
        wd.findElement(By.name("group_name")).clear();
        wd.findElement(By.name("group_name")).sendKeys(groupeData.getName());
        wd.findElement(By.name("group_header")).click();
        wd.findElement(By.name("group_header")).clear();
        wd.findElement(By.name("group_header")).sendKeys(groupeData.getHeader());
        wd.findElement(By.name("group_footer")).click();
        wd.findElement(By.name("group_footer")).clear();
        wd.findElement(By.name("group_footer")).sendKeys(groupeData.getFooter());
    }

    private void initGoupeCreation() {
        wd.findElement(By.name("group_name")).click();
    }

    private void gotoGroupPage() {
        wd.findElement(By.linkText("groups")).click();
    }

    @AfterClass
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
