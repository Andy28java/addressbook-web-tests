package ru.kurs.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.kurs.addressbook.model.ContactData;
import ru.kurs.addressbook.model.Contacts;
import ru.kurs.addressbook.model.GroupData;
import ru.kurs.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by yana on 4/5/2016.
 */
public class AddContactToGroupTest extends TestBase {
    private ContactData findNewContact(final Contacts before) {
        Contacts after = app.db().contacts();

        for (ContactData c : after) {
            if (!before.contains(c)) {
                return c;
            }
        }
        throw new RuntimeException("No new contact found");
    }

    private ContactData theContact;
    private GroupData groupToAdd;

    @BeforeTest
    public void prepareTest() {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        // select contact for the test
        theContact = null;
        groupToAdd = null;
        for (ContactData c : contacts) {
            Groups cg = c.getGroups();

            for (GroupData g : groups) {
                if (!cg.contains(g)) {
                    groupToAdd = g;
                    break;
                }
            }
            if (groupToAdd != null) {
                theContact = c;
                break;
            }
        }
        if (theContact == null) {
            theContact = new ContactData().withFirstname("Vasya");
            groupToAdd = groups.iterator().next();

            final Contacts before = app.db().contacts();
            app.goTo().homePage();
            app.contact().addContact();
            app.contact().fillCont(theContact, true);
            app.contact().submit();
            app.goTo().homePage();

            theContact = findNewContact(before);
        }
        if (groupToAdd == null) {
            groupToAdd = new GroupData().withName("New group for Vasya");
            // create the group if needed;
            app.goTo().groupPage();
            app.group().create(groupToAdd);
        }
    }

    @Test
    public void addContactToGroup() {
        app.goTo().homePage();
        app.contact().selectContactById(theContact.getId());
        app.contact().addToGroup(groupToAdd);
        System.out.printf("Add to group \"%s\": %s\n", groupToAdd.getName(), theContact);

        Contacts after = app.db().contacts();
        Groups newGroupsList = null;
        for (ContactData c : after) {
            if (c.getId() == theContact.getId()) {
                newGroupsList = c.getGroups();
                break;
            }
        }
        Assert.assertTrue(newGroupsList != null, "No groups for contact " + theContact.getId());


        boolean newGroupFound = false;
        for (GroupData g : newGroupsList) {
            if (groupToAdd.getName().equals(g.getName())) {
                newGroupFound = true;
                break;
            }
        }

        GroupData ug = null;
        Groups groups = app.db().groups();
        for (GroupData g : groups) {
            if (g.getId() == groupToAdd.getId()) {
                ug = g;
                break;
            }
        }
        Assert.assertTrue(ug != null);

        Assert.assertTrue(newGroupFound, "Test failed: no new group");
        app.goTo().homePage();
        app.contact().choiceGroup(ug);
        Contacts contactsOnPage = app.contact().all();
        Contacts contactsInDB = ug.getContacts();
        assertThat(contactsOnPage, equalTo(contactsInDB));
    }
}
