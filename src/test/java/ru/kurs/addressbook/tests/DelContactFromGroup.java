package ru.kurs.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.kurs.addressbook.model.ContactData;
import ru.kurs.addressbook.model.Contacts;
import ru.kurs.addressbook.model.GroupData;
import ru.kurs.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

/**
 * Created by yana on 4/5/2016.
 */
public class DelContactFromGroup extends TestBase {
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
    private GroupData groupFromDel;
    @BeforeTest
    public void prepareTest() {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();

        theContact = null;
        groupFromDel = null;
        for (ContactData c : contacts) {
            Groups cg = c.getGroups();

            if (cg.size() > 0) {
                theContact = c;
                groupFromDel = cg.iterator().next();
                break;
            }
        }

        if (theContact != null && groupFromDel != null) {
            return;
        }

        if (theContact == null) {

            if (contacts.size() > 0) {
                theContact = contacts.iterator().next();
            } else {
                theContact = new ContactData().withFirstname("Vasya");

                final Contacts before = app.db().contacts();
                app.goTo().homePage();
                app.contact().addContact();
                app.contact().fillCont(theContact, true);
                app.contact().submit();
                app.goTo().homePage();

                theContact = findNewContact(before);
            }
        }
        if (groupFromDel == null) {
            if (groups.size() > 0) {
                groupFromDel = groups.iterator().next();
            } else {
                groupFromDel = new GroupData().withName("New group for Vasya");
                app.goTo().groupPage();
                app.group().create(groupFromDel);
            }
        }

        app.goTo().homePage();
        app.contact().selectContactById(theContact.getId());
        app.contact().addToGroup(groupFromDel);
        System.out.printf("Add to group \"%s\": %s\n", groupFromDel.getName(), theContact);

    }

    @Test
    public void delContactFromGroup() {
        app.goTo().homePage();

        app.contact().choiceGroup(groupFromDel);

        app.contact().selectContactById(theContact.getId());
        app.contact().remove();

        System.out.printf("Del from group \"%s\": %s\n", groupFromDel.getName(), theContact);

        Contacts after = app.db().contacts();

        ContactData modified = null;
        for (ContactData c : after) {
            if (theContact.getId() == c.getId()) {
                modified = c;
                return;
            }
        }

        Assert.assertTrue(modified != null, "No modified contact with ID " + theContact.getId());

        Assert.assertFalse(modified.getGroups().contains(groupFromDel), "Still a member of " + groupFromDel.getName());
        Assert.assertFalse(groupFromDel.getContacts().contains(modified));

        app.goTo().homePage();
        app.contact().choiceGroup(groupFromDel);
        Contacts contactsOnPage = app.contact().all();

        assertThat(contactsOnPage, equalTo(groupFromDel.getContacts()));
    }
}
