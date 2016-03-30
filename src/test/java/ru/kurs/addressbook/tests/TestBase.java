package ru.kurs.addressbook.tests;


import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import ru.kurs.addressbook.appmanager.ApplicationManager;

import java.lang.reflect.Method;
import java.util.Arrays;

import static org.testng.Assert.assertTrue;

/**
 * Created by yana on 3/1/2016.
 */
public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static final ApplicationManager app;
    static {
        String browser = System.getProperty("browser");

        if (browser == null || browser.isEmpty()) {
            throw new RuntimeException("No browser property");
        }
        //= new ApplicationManager(BrowserType.FIREFOX);
        app =new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
    }
    protected WebDriver wd = null;

    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private void closeAlertAndGetItsText() {
        try {
            Alert a = wd.switchTo().alert();
            assertTrue(a != null, "Expected alert does not occur");

            a.accept();

        } catch (NoAlertPresentException e) {
            assertTrue(false, "Expected alert does not occur");
        }
    }

    @BeforeSuite
    public void setUp() throws Exception {
        System.out.println("app:" + app);
        app.init();

        wd = app.getWebDriver();
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }

    @BeforeMethod(alwaysRun = true) //@Configuration
    public void logTestStart(Method m, Object[] p){
        logger.info("Start test " + m.getName() + " with parameters " + Arrays.asList(p));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m){
        logger.info("Stop test " + m.getName());
    }
}
