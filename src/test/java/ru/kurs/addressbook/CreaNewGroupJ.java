package ru.kurs.addressbook;


import org.testng.annotations.Test;

import org.openqa.selenium.*;

public class CreaNewGroupJ extends TestBase {

    @Test
    public void testCreaNewGroupJ() {

        gotoGroupPage();
        wd.findElement(By.name("new")).click();
        initGoupeCreation();
        fillGroupeForm(new GroupeData("test1", "1", "11"));
        submitGroupeCreation();
        gotoGroupPage();
    }

}
