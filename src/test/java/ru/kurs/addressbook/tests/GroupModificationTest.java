package ru.kurs.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.kurs.addressbook.appmanager.GroupHelper;
import ru.kurs.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

/**
 * Created by yana on 3/2/2016.
 */
public class GroupModificationTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        final GroupHelper h = app.group();
        app.goTo().groupPage();
        if ( app.group().list().size() == 0) {   //!h.hasGroups()) {
            h.create(new GroupData().withName("test_create_if_does_not_exist"));
            app.goTo().groupPage();
        }
    }

    @Test (enabled = false)
    public void testGroupModification() {
        final GroupHelper h = app.group();
        List<GroupData> before = app.group().list();
        int index = before.size() - 1;
        GroupData group = new GroupData()
                .withId(before.get(index).getId()).withName("test1").withHeader("test2").withFooter("test3");
        h.modify(index, group);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }


}
