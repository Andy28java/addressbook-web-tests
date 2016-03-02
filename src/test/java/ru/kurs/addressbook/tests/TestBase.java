package ru.kurs.addressbook.tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import ru.kurs.addressbook.appmanager.ApplicationManager;

import static org.testng.Assert.assertTrue;

/**
 * Created by yana on 3/1/2016.
 */
public class TestBase {

    protected final ApplicationManager app = new ApplicationManager();
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

       @BeforeTest
    public void setUp() throws Exception {
        app.init();

        wd = app.getWebDriver();
    }

    @AfterClass
    public void tearDown() {
        app.stop();
    }


}
