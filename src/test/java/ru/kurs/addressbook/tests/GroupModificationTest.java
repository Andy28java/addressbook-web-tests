package ru.kurs.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.kurs.addressbook.appmanager.GroupHelper;
import ru.kurs.addressbook.model.GroupData;

/**
 * Created by yana on 3/2/2016.
 */
public class GroupModificationTest extends TestBase {

    @Test
    public void testGroupModification() {
        final GroupHelper h = app.getGroupHelper();
        app.getNavigationHelper().gotoGroupPage();
        if (!h.hasGroups()) {
            h.createGroup("test_create_if_does_not_exist", "66", "777");
            app.getNavigationHelper().gotoGroupPage();
        }
        h.selectGroup();
        h.initGroupeModification();
        h.fillGroupeForm(new GroupData("test1", null, null)); //"1", "11"));
        h.submitGroupeModification();
        app.getNavigationHelper().gotoGroupPage();
    }
}
