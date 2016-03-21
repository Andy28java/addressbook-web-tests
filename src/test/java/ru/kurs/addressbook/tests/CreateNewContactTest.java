package ru.kurs.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.kurs.addressbook.appmanager.ContactHelper;
import ru.kurs.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class CreateNewContactTest extends TestBase {

    @Test //(enabled = false)
    public void createNewContact() {
        final ContactHelper h = app.contact();
        app.goTo().homePage();
        List<ContactData> before = h.list();
        ContactData contact = new ContactData().withFirstname("Ivan2").withMiddlename("Petrovich").withLastname("Durov");
        h.addContact();
        h.fillCont(contact);
        h.submit();
        app.goTo().homePage();
        List<ContactData> after = h.list();

        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));


        }
    }
