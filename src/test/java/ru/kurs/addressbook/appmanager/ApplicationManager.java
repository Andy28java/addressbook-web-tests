package ru.kurs.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

/**
 * Created by yana on 3/1/2016.
 */
public class ApplicationManager {
    WebDriver wd;

    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GpoupHelper gpoupHelper ;
    private ContactHelper contactHelper;
    private String browser;

    public ApplicationManager(String browser) {

        this.browser = browser;
    }

    public void init() {
          if (browser == BrowserType.FIREFOX) {
            wd = new FirefoxDriver();
        } else if (browser == BrowserType.CHROME) {
            wd = new ChromeDriver();
        } else if ( browser == BrowserType.IE) {
            wd = new InternetExplorerDriver();
        }

        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        wd.get("http://localhost:8081/addressbook/");
        gpoupHelper = new GpoupHelper(wd);
        contactHelper = new ContactHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login("admin", "secret");
    }

    public void login(String username, String userpasswd) {
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

    public void stop() {
        wd.quit();
    }

    public GpoupHelper getGpoupHelper() {
        return gpoupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public WebDriver getWebDriver() {
        return wd;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }
}
