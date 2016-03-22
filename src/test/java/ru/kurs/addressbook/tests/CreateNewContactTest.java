package ru.kurs.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.kurs.addressbook.appmanager.ContactHelper;
import ru.kurs.addressbook.model.ContactData;
import ru.kurs.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateNewContactTest extends TestBase {

    @Test //(enabled = false)
    public void createNewContact() {
        final ContactHelper h = app.contact();
        app.goTo().homePage();
        Contacts before = (Contacts) app.contact().all();
        ContactData contact = new ContactData().withFirstname("Ivan2").withMiddlename("Petrovich").withLastname("Durov");
        h.addContact();
        h.fillCont(contact);
        h.submit();
        app.goTo().homePage();
        Contacts after = (Contacts) h.all();

        Assert.assertEquals(after.size(), before.size() + 1);
        contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);

        assertThat(after, equalTo(before.withAdded(
                contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
        }
    }
