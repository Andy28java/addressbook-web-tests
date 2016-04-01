package ru.kurs.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.kurs.addressbook.appmanager.GroupHelper;
import ru.kurs.addressbook.model.GroupData;
import ru.kurs.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by yana on 3/2/2016.
 */
public class GroupModificationTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        final GroupHelper h = app.group();
        if (app.db().groups().size() == 0){
        app.goTo().groupPage();
            h.create(new GroupData().withName("test_create_if_does_not_exist"));
        }
    }

    @Test //(enabled = false)
    public void testGroupModification() {
        final GroupHelper h = app.group();
        Groups before = app.db().groups();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId()).withName("test1").withHeader("test2").withFooter("test3");
        app.goTo().groupPage();
        h.modify(group);
        Groups after = app.db().groups();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    }


}
