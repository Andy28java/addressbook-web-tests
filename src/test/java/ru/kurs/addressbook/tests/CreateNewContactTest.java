package ru.kurs.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.kurs.addressbook.appmanager.ContactHelper;
import ru.kurs.addressbook.model.ContactData;
import ru.kurs.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

       // contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);

        MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.withAdded(
                contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
        }
    }
