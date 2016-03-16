package ru.kurs.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.kurs.addressbook.appmanager.ContactHelper;
import ru.kurs.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class CreateNewContactTest extends TestBase {

    @Test
    public void createNewContact() {
        final ContactHelper h = app.getContactHelper();
        List<ContactData> before = h.getContactList();
        ContactData contact = new ContactData("Ivan", "Petrovich", "Durov", null, null, null, null);
        h.addNewContact();
        h.fillContDate(contact);
        h.submit();
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = h.getContactList();

        Assert.assertEquals(after.size(), before.size() + 1);

        contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));


        }
    }
