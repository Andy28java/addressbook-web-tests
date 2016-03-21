package ru.kurs.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.kurs.addressbook.appmanager.GroupHelper;
import ru.kurs.addressbook.model.GroupData;

import java.util.List;

public class GroupDelTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        final GroupHelper h = app.group();
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {   //!h.hasGroups()) {
            h.create(new GroupData().withName("test_create_if_does_not_exist"));  //"test_create_if_does_not_exist", "66", "777");
            app.goTo().groupPage();
        }

    }

    @Test (enabled = false)
    //.//*[@id='content']/form/span
    // /html/body/div/div[4]/form[2]/table/tbody/tr[2]/td[1]  для контакта
    public void testGroupDel() {
        app.goTo().groupPage();
        GroupHelper h = app.group();
        List<GroupData> before = app.group().list();
        int index = before.size() - 1;
        app.group().delete(index);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
        }


}


