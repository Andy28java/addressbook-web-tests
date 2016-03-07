package ru.kurs.addressbook.appmanager;

import org.openqa.selenium.*;

import static org.testng.Assert.assertTrue;

/**
 * Created by yana on 3/1/2016.
 */
public abstract class HelperBase {
    public final WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    protected void click(By locator) {
        WebElement e = wd.findElement(locator);
        if (e != null) {
            e.click();
        } else {
            throw new RuntimeException("Failed to find elemen by " + locator);
        }
    }

    protected void type(By locator, String text) {
        click(locator);
        if (text != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
    public boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    protected void acceptAlert() {
        try {
            Alert a = wd.switchTo().alert();
            assertTrue(a != null, "Expected alert does not occur");

            a.accept();
        } catch (NoAlertPresentException e) {
            assertTrue(false, "Expected alert does not occur");
        }
    }


}
