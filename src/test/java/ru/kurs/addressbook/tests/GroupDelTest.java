package ru.kurs.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDelTest extends TestBase {


    @Test
    public void testGroupDel() {
       app.getNavigationHelper().gotoGroupPage();
        app.getGpoupHelper().selectGroup();
        app.getGpoupHelper().DeleteSelectedGroups();
        app.getNavigationHelper().gotoGroupPage();
    }

}
