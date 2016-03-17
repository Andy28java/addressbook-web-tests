package ru.kurs.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.kurs.addressbook.appmanager.ContactHelper;
import ru.kurs.addressbook.appmanager.NavigationHelper;
import ru.kurs.addressbook.model.ContactData;

import java.util.List;

public class ContactDelTest extends TestBase {

    @Test
    public void testContDel() {
        final ContactHelper h = app.getContactHelper();
        final NavigationHelper n = app.getNavigationHelper();
        n.goToHomePage();
        if (!h.hasContacts()) {
           h.addNewContact();
            h.fillContDate(new ContactData("Ivan", "Petrovich", "Surov", "SPI", "Testing", "1234567", "qwe@mail.ru"));
            h.submit();
        }
        List<ContactData> before = h.getContactList();
        h.selectContact(before.size() - 1);
        h.deleteSelectedContact();
        n.goToHomePage();
        List<ContactData> after = h.getContactList();

        Assert.assertEquals(after.size(),before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}

