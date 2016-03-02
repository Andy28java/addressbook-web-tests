package ru.kurs.addressbook.tests;


import org.testng.annotations.Test;

import org.openqa.selenium.*;
import ru.kurs.addressbook.model.GroupData;

public class CreaNewGroupJ extends TestBase {

    @Test
    public void testCreaNewGroupJ() {

        app.getGpoupHelper().gotoGroupPage();
        app.getGpoupHelper().wd.findElement(By.name("new")).click();
        app.getGpoupHelper().initGoupeCreation();
        app.getGpoupHelper().fillGroupeForm(new GroupData("test1", "1", "11"));
        app.getGpoupHelper().submitGroupeCreation();
        app.getGpoupHelper().gotoGroupPage();
    }

}
