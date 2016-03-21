package ru.kurs.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import ru.kurs.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class CreateNewGroupJ extends TestBase {

    @Test(enabled = false)
    public void testCreaNewGroupJ() {
        app.goTo().groupPage();
        List<GroupData> before = app.group().list();
        GroupData group = new GroupData().withName("test2");
        app.group().create(group);
        app.goTo().groupPage();
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(),before.size() + 1);

        group.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(group) ;
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }

}
