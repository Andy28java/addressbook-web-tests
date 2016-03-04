package ru.kurs.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by yana on 3/1/2016.
 */
public class SessionHelper extends HelperBase {

    public SessionHelper(WebDriver wd) {
        super(wd);
    }
    public void login(String username, String userpasswd) {
        type(By.name("user"), username);
        type(By.name("pass"), userpasswd);
        //wd.findElement(By.id("content")).click();
       // wd.findElement(By.id("LoginForm")).click();
               click(By.xpath("//form[@id='LoginForm']/input[3]"));
    }

}

