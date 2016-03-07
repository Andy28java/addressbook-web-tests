package ru.kurs.addressbook.tests;

import org.testng.annotations.Test;
import ru.kurs.addressbook.appmanager.ContactHelper;
import ru.kurs.addressbook.appmanager.NavigationHelper;
import ru.kurs.addressbook.model.ContactData;
import ru.kurs.addressbook.tests.TestBase;

public class ContactDelTest extends TestBase {

    @Test
    public void testContDel() {
        final ContactHelper h = app.getContactHelper();
        final NavigationHelper n = app.getNavigationHelper();
        n.goToHomePage();

        if (!h.hasContacts()) {
            //h.createNewContact();
            h.addNewContact();
            h.fillContDate(new ContactData("Ivan", "Petrovich", "Surov", "SPI", "Testing", "1234567", "qwe@mail.ru"));
            h.submit();
        }
        h.selectContact();
        h.deleteSelectedContact();
        n.goToHomePage();
    }
}
