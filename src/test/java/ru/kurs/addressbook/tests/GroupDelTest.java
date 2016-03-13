package ru.kurs.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.kurs.addressbook.appmanager.GroupHelper;
import ru.kurs.addressbook.model.GroupData;

public class GroupDelTest extends TestBase {


    @Test
    //.//*[@id='content']/form/span
    // /html/body/div/div[4]/form[2]/table/tbody/tr[2]/td[1]  для контакта
    public void testGroupDel() {
       app.getNavigationHelper().gotoGroupPage();
        GroupHelper h = app.getGroupHelper();
        if (!h.hasGroups()) {
            h.createGroup("test_create_if_does_not_exist", "66", "777");
            app.getNavigationHelper().gotoGroupPage();
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().DeleteSelectedGroups();
        app.getNavigationHelper().gotoGroupPage();
    }

}
