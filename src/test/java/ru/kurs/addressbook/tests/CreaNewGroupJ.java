package ru.kurs.addressbook.tests;


import org.testng.annotations.Test;

import org.openqa.selenium.*;
import ru.kurs.addressbook.model.GroupData;

public class CreaNewGroupJ extends TestBase {

    @Test
    public void testCreaNewGroupJ() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().wd.findElement(By.name("new")).click();
        app.getGroupHelper().initGoupeCreation();
        app.getGroupHelper().fillGroupeForm(new GroupData("test1", "1", "11"));
        app.getGroupHelper().submitGroupeCreation();
        app.getNavigationHelper().gotoGroupPage();
    }

}
