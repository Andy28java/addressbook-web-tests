package ru.kurs.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.kurs.addressbook.appmanager.GroupHelper;
import ru.kurs.addressbook.model.GroupData;
import ru.kurs.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupDelTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        final GroupHelper h = app.group();
        app.goTo().groupPage();
        if (app.db().groups().size() == 0) {   //!h.hasGroups()) {
            h.create(new GroupData().withName("test_create_if_does_not_exist"));  //"test_create_if_does_not_exist", "66", "777");
            app.goTo().groupPage();
        }

    }

    @Test //(enabled = false)
    //.//*[@id='content']/form/span
    // /html/body/div/div[4]/form[2]/table/tbody/tr[2]/td[1]  для контакта
    public void testGroupDel() {
        app.goTo().groupPage();
        GroupHelper h = app.group();
        Groups before = app.db().groups();//app.group().all();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        app.goTo().groupPage();
        Groups after = app.db().groups();//app.group().all();
        assertEquals(after.size(), before.size() - 1);
        assertThat(after, equalTo(before.without(deletedGroup)));
        verifyGroupListInUI();
    }


}


