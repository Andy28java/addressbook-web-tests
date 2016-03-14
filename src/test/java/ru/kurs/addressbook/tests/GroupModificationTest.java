package ru.kurs.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.kurs.addressbook.appmanager.GroupHelper;
import ru.kurs.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

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
        List<GroupData> before = app.getGroupHelper().getGroupList();
        h.selectGroup(before.size() - 1);
        h.initGroupeModification();
        GroupData group = new GroupData(before.get(before.size() - 1).getId(), "test1", "test2", "test3");
        h.fillGroupeForm(group); //"1", "11"));
        h.submitGroupeModification();
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
