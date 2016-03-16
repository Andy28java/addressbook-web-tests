package ru.kurs.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.kurs.addressbook.appmanager.ContactHelper;
import ru.kurs.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

/**
 * Created by yana on 3/2/2016.
 */
public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification() {
        final ContactHelper h = app.getContactHelper();
        if (!h.hasContacts()) {
            h.addNewContact();
            h.fillContDate(new ContactData("Ivan2", "Petrovich", "Surov", "SPI", "Testing", "1234567", "qwe@mail.ru"));
            h.submit();
        }
        List<ContactData> before = h.getContactList();
        int index = before.size() - 1;
        h.editContact(index);
        ContactData contact = new ContactData(before.get(index).getId(),"Ivan", "Petrovich", "Surov",null, null, null, null);
        h.fillContDate(contact);// "SPI", "Testing", "1234567", "qwe@mail.ru"));
        h.update();
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = h.getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    }
}
