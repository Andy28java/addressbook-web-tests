package ru.kurs.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDelTest extends TestBase {


    @Test
    public void testGroupDel() {
       app.getGpoupHelper().gotoGroupPage();
        app.getGpoupHelper().selectGroupe();
        app.getGpoupHelper().DeleteSelectedGroups();
        app.getGpoupHelper().gotoGroupPage();
    }

}
